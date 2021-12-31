package com.example.mp_project

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mp_project.model.Advertisement
import com.example.mp_project.model.Datasource
import com.example.mp_project.serverModel.advertisements.AdvertisementListItemAdapter
import com.example.mp_project.serverModel.advertisements.AdvertisementsListItem
import com.example.mp_project.serverModel.advertisements.ApiAdvertisementsListInterface
import kotlinx.android.synthetic.main.advertisements_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable


const val BASE_URL = "https://agile-reaches-72185.herokuapp.com/api/"

class AdvertisementsFragment : Fragment() {


    companion object {
        private const val ARG_NAME = "ad"


        fun newInstance(adInfo: Serializable?): AdvertisementsFragment {
            val fragment = AdvertisementsFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_NAME, adInfo)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var viewModel: AdvertisementsViewModel
    private lateinit var mAdapter: AdvertisementListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.advertisements_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AdvertisementsViewModel::class.java)

        val myAnnouncements = Datasource().loadAnnouncements()
        val showingAnnouncements: List<Advertisement>

        val recyclerView = advertisements_recycler_view

        val adInfo = arguments?.getSerializable("ad") as? Advertisement
        showingAnnouncements = if (adInfo == null) {
            myAnnouncements
        } else {
            filterAds(myAnnouncements, adInfo.book.title)
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // adapter for local dataset (set by Kamyar)
        recyclerView.adapter = AdvertisementItemAdapter(requireContext(), showingAnnouncements)



        getAdvertisementFromServer()
    }

    private fun getAdvertisementFromServer() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiAdvertisementsListInterface::class.java)

        val retrofitData = retrofitBuilder.getAdvertisementList()

        retrofitData.enqueue(object : Callback<List<AdvertisementsListItem>?> {
            override fun onResponse(
                call: Call<List<AdvertisementsListItem>?>,
                response: Response<List<AdvertisementsListItem>?>
            ) {
                val responseBody = response.body()!!

                mAdapter = AdvertisementListItemAdapter(requireContext(), responseBody)
                mAdapter.notifyDataSetChanged()
                advertisements_recycler_view.adapter = mAdapter


//                Log.v("Program", responseBody.toString())
            }

            override fun onFailure(call: Call<List<AdvertisementsListItem>?>, t: Throwable) {
                Log.d("Program", "Failure: " + t.message)
            }
        })
    }

}


fun filterAds(
    src: List<Advertisement>,
    name: String,
//    minPrice: Int,
//    maxPrice: Int,
//    city: String,
): List<Advertisement> {
    return src.filter { it.book.title.contains(name, ignoreCase = true) }
}
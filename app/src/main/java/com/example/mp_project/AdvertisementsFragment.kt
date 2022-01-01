package com.example.mp_project

import android.content.Context
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.google.android.material.card.MaterialCardView
import android.widget.EditText

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
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


        initFilterBox()
        getFilterBoxValues()
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
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d("ad", "create")
        inflater.inflate(R.menu.menu_toolbar, menu)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter_toggle_btn) {
            val filterBox = activity?.findViewById<MaterialCardView>(R.id.filter_box)!!
            if (filterBox.visibility == View.GONE) {
                filterBox.visibility = View.VISIBLE
            } else if (filterBox.visibility == View.VISIBLE) {
                filterBox.visibility = View.GONE
            }
        } else if (item.itemId == R.id.search_toggle_btn) {
            val searchBar =  activity?.findViewById<LinearLayout>(R.id.advertisements_search_bar)!!
            val editText = activity?.findViewById(R.id.advertisements_search_edit) as EditText

            if (searchBar.visibility == View.GONE) {
                searchBar.visibility = View.VISIBLE
                editText.requestFocus()
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
            } else if (searchBar.visibility == View.VISIBLE) {
                searchBar.visibility = View.GONE
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun initFilterBox() {
        // Create an ArrayAdapter
        val adapter1 = ArrayAdapter.createFromResource(requireContext(),
            R.array.city_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        val adapter2 = ArrayAdapter.createFromResource(requireContext(),
            R.array.category_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner


        val adapter3 = ArrayAdapter.createFromResource(requireContext(),
            R.array.sort_kind_list, android.R.layout.simple_spinner_item)
        // Specify the layout to use when the list of choices appears
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val spinner: Spinner = activity?.findViewById(R.id.spinner)!!
        val spinner2: Spinner = activity?.findViewById(R.id.spinner2)!!
        val spinner3: Spinner = activity?.findViewById(R.id.spinner3)!!

        spinner.adapter = adapter1
        spinner2.adapter = adapter2
        spinner3.adapter = adapter3
    }
    private fun getFilterBoxValues(): List<String> {
        val spinner: Spinner = activity?.findViewById(R.id.spinner)!!
        val spinner2: Spinner = activity?.findViewById(R.id.spinner2)!!
        Log.d("filter", spinner.selectedItem.toString())
        Log.d("filter", spinner2.selectedItem.toString())
        return listOf<String>(
            spinner.selectedItem.toString(),
            spinner2.selectedItem.toString()
        )
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
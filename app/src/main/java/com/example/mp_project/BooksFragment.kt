package com.example.mp_project

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mp_project.model.Book
import com.example.mp_project.model.Datasource
import com.example.mp_project.serverModel.books.ApiBooksListInterface
import com.example.mp_project.serverModel.books.BooksListItem
import com.example.mp_project.serverModel.books.BooksListItemAdapter
import kotlinx.android.synthetic.main.advertisements_fragment.*
import kotlinx.android.synthetic.main.books_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class BooksFragment : Fragment() {
    companion object {
        private const val ARG_NAME = "book"


        fun newInstance(adInfo: Serializable?): BooksFragment {
            val fragment = BooksFragment()

            val bundle = Bundle().apply {
                putSerializable(ARG_NAME, adInfo)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    private lateinit var viewModel: BooksViewModel
    private lateinit var mAdapter: BooksListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.books_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BooksViewModel::class.java)

        val myBooks = Datasource().loadBooks()
        val showingBooks: List<Book>
        val recyclerView = books_recycler_view

        val bookInfo = arguments?.getSerializable("book") as? Book
        showingBooks = if (bookInfo == null) {
            myBooks
        } else {
            filterBooks(myBooks, bookInfo.title)
        }
        recyclerView.adapter = BookItemAdapter(requireContext(), showingBooks)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // adapter for local dataset (set by Kamyar)
        recyclerView.adapter = BookItemAdapter(requireContext(), showingBooks)

        getBooksFromServer()
    }

    private fun getBooksFromServer() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiBooksListInterface::class.java)

        val retrofitData = retrofitBuilder.getBooksList()

        retrofitData.enqueue(object : Callback<List<BooksListItem>?> {
            override fun onResponse(
                call: Call<List<BooksListItem>?>,
                response: Response<List<BooksListItem>?>
            ) {
                val responseBody = response.body()!!

                mAdapter = BooksListItemAdapter(requireContext(), responseBody)
                mAdapter.notifyDataSetChanged()
                books_recycler_view.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<BooksListItem>?>, t: Throwable) {
                Log.d("Program", "Failure: " + t.message)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        Log.d("book", "create")
        inflater.inflate(R.menu.menu_toolbar, menu)
        menu.findItem(R.id.filter_toggle_btn).isVisible = false
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search_toggle_btn) {
            val searchBar = activity?.findViewById<LinearLayout>(R.id.books_search_bar)!!
            val editText = activity?.findViewById(R.id.books_search_edit) as EditText

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

}

fun filterBooks(
    src: List<Book>,
    name: String,
//    minPrice: Int,
//    maxPrice: Int,
//    city: String,
): List<Book> {
    return src.filter { it.title.contains(name, ignoreCase = true) }
}
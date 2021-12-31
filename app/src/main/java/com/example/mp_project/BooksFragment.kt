package com.example.mp_project

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mp_project.model.Book
import com.example.mp_project.model.Datasource
import kotlinx.android.synthetic.main.books_fragment.*
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
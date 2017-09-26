package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import com.example.developer.books.MainActivity
import com.example.developer.books.R
import com.example.developer.books.model.Book

class DescriptionFragment : Fragment() {
    var book: Book? = null

    companion object {
        internal const val KEY_BOOK = "key_book"
        const val TAG = "DescriptionFragment"

        fun newInstance(book: Book): DescriptionFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_BOOK, book)
            val fragment = DescriptionFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        book = arguments[KEY_BOOK] as Book
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val text = view.findViewById<TextView>(R.id.textView_description)
        text.text = book?.description
    }


    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        val twoPane = (activity as MainActivity).twoPane
        if (!twoPane) {
            val appCompatActivity = activity as AppCompatActivity
            appCompatActivity.supportActionBar?.title = book?.title
            appCompatActivity.supportActionBar?.subtitle = book?.author
            menu?.findItem(R.id.menu_add_book)?.isVisible = false
            menu?.findItem(R.id.save_button)?.isVisible = false
        }
        else{
            val appCompatActivity = activity as AppCompatActivity
            appCompatActivity.supportActionBar?.setTitle(R.string.title_books_list_fragment)
            appCompatActivity.supportActionBar?.subtitle = null
            menu?.findItem(R.id.menu_add_book)?.isVisible = true
            menu?.findItem(R.id.save_button)?.isVisible = false
        }
//
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        android.R.id.home -> {
            fragmentManager.popBackStack()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}
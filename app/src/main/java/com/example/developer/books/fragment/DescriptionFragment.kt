package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import com.example.developer.books.R
import com.example.developer.books.model.Book

class DescriptionFragment : Fragment() {
    companion object {
        private const val KEY_BOOK = "key_book"
        const val TAG = "DescriptionFragment"

        fun newInstance(book: Book): DescriptionFragment {
            val arguments = Bundle()
            arguments.putParcelable(KEY_BOOK, book)
            val fragment = DescriptionFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_description, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        view.findViewById<TextView>(R.id.textView_description).text = (arguments[KEY_BOOK] as Book).description
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        if (this.activity.findViewById<View>(R.id.main_frame_layout) == null) {
            (activity as AppCompatActivity).supportActionBar?.title = (arguments[KEY_BOOK] as Book).title
            (activity as AppCompatActivity).supportActionBar?.subtitle = (arguments[KEY_BOOK] as Book).author
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {

        android.R.id.home -> {
            activity.supportFragmentManager.popBackStack()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
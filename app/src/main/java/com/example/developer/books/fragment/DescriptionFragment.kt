package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        view.findViewById<TextView>(R.id.textView_description).text = (arguments[KEY_BOOK] as Book).description
    }
}
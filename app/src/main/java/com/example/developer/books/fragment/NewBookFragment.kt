package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.developer.books.R
import com.example.developer.books.model.Book

/**
 * Created by developer on 15.09.17.
 */
class NewBookFragment: Fragment(){

    private var buttonSave:Button?=null
    private var buttonCancel:Button?=null
    companion object {
        fun newInstance( ): NewBookFragment {
            //val arguments = Bundle()
            //arguments.putParcelable(KEY_FEED_ENTRY, feedEntry)

            val fragment = NewBookFragment()
            //fragment.arguments = arguments
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView= inflater.inflate(R.layout.fragment_new_book,container,false)
        buttonSave=rootView.findViewById(R.id.save_button)
        buttonCancel=rootView.findViewById(R.id.cancel_button)

        buttonSave!!.setOnClickListener {  }
        return rootView
    }
    private fun setResult(book:Book) {

    }
}
package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.books.R

/**
 * Created by developer on 15.09.17.
 */
class NewBookFragment: Fragment(){
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
        return inflater.inflate(R.layout.fragment_new_book,container,false)
    }
}
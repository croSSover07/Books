package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.developer.books.MainActivity
import com.example.developer.books.R

/**
 * Created by developer on 15.09.17.
 */
class BookListFragment:Fragment(){
    private var buttonAdd:Button?=null
    private val mainActivity: MainActivity? get() = activity as? MainActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_book_list, container, false)

        buttonAdd=rootView.findViewById(R.id.button_add)
        buttonAdd?.setOnClickListener {
            val fragment=NewBookFragment.newInstance()
            mainActivity?.replaceMainFragment(fragment,true)
        }
        return rootView
    }


}
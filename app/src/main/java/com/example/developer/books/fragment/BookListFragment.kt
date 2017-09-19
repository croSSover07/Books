package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.developer.books.MainActivity
import com.example.developer.books.R
import com.example.developer.books.adapter.BookAdapter
import com.example.developer.books.model.BooksListJava

class BookListFragment:Fragment(){
    private var buttonAdd:Button?=null
    private lateinit var recyclerView:RecyclerView

    private val mainActivity: MainActivity? get() = activity as? MainActivity



    private lateinit var bookAdapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_book_list, container, false)

        buttonAdd=rootView.findViewById(R.id.button_add)
        buttonAdd?.setOnClickListener {
            val fragment=NewBookFragment.newInstance()
            mainActivity?.replaceMainFragment(fragment,true)
        }
        val booksList=BooksListJava.get()

        bookAdapter= BookAdapter(booksList.getmList(),context)

        recyclerView=rootView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=bookAdapter




        return rootView
    }


}




















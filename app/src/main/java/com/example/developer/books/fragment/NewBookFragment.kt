package com.example.developer.books.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.developer.books.R
import com.example.developer.books.model.Book
import com.example.developer.books.model.BooksListJava
import java.util.*

/**
 * Created by developer on 15.09.17.
 */
class NewBookFragment: Fragment(){

    private var buttonSave:Button?=null
    private var buttonCancel:Button?=null

    companion object {
        fun newInstance( ): NewBookFragment {
            val fragment = NewBookFragment()
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

        buttonSave!!.setOnClickListener {
            val a=BooksListJava.get(context)
            val title=rootView.findViewById<EditText>(R.id.editText_title_book).text
            val author=rootView.findViewById<EditText>(R.id.editText_author_book).text
            // val date=rootView.findViewById<EditText>(R.id.editText_title_book).text
            val pub=rootView.findViewById<EditText>(R.id.editText_publication_book).text
            a.addBook(Book(title.toString(),author.toString(), Date(),pub.toString()))
            activity.supportFragmentManager.popBackStack()
        }
        buttonCancel!!.setOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }
        return rootView
    }


}
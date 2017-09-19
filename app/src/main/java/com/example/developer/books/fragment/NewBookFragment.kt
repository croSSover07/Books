package com.example.developer.books.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.developer.books.R
import com.example.developer.books.model.Book
import com.example.developer.books.model.BooksListJava
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewBookFragment: Fragment(){
    private lateinit var buttonSave:Button
    private lateinit var buttonCancel:Button
    private lateinit var textViewDate:TextView
    companion object {
        private val DIALOG_DATE = "DialogDate"
        private val  REQUEST_DATE = 0
        fun newInstance( ): NewBookFragment = NewBookFragment()

        fun stringToDate(dateString:String):Date? {
            val format = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH) as DateFormat
            // val df = SimpleDateFormat("EEE MMM dd HH:mm:ss z YYYY")
            var startDate:Date?=null
            try {
                startDate = format.parse(dateString)
            } finally {
                return startDate
            }
        }
        fun dateToString(date:Date):String{
            val format = SimpleDateFormat("MMM dd YYYY", Locale.ENGLISH)
            return format.format(date)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) = super.onCreate(savedInstanceState)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView= inflater.inflate(R.layout.fragment_new_book,container,false)
        buttonSave=rootView.findViewById(R.id.save_button)
        buttonCancel=rootView.findViewById(R.id.cancel_button)
        textViewDate=rootView.findViewById(R.id.textView4)
        buttonSave.setOnClickListener {
            val title=rootView.findViewById<EditText>(R.id.editText_title_book).text.toString()
            val author=rootView.findViewById<EditText>(R.id.editText_author_book).text.toString()
            val date=stringToDate( textViewDate.text.toString())
            val pub=rootView.findViewById<EditText>(R.id.editText_publication_book).text.toString()

            if (date!=null
                    && title.trim().isNotEmpty()
                    && author.trim().isNotEmpty()
                    && pub.trim().isNotEmpty()){
                val a = BooksListJava.get()
                a.addBook(Book(title, author, date, pub))
                activity.supportFragmentManager.popBackStack()
            }
        }
        buttonCancel.setOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }

        val buttonDate=rootView.findViewById<Button>(R.id.button_date)
        buttonDate.setOnClickListener {
            val fm=fragmentManager
            val dialog=DatePickerFragment.newInstance()
            dialog.setTargetFragment(this@NewBookFragment, REQUEST_DATE)
            dialog.show(fm, DIALOG_DATE)
        }
        return rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode !== Activity.RESULT_OK) {
            return
        }
        if (requestCode === REQUEST_DATE) {
            val date = data!!.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            textViewDate.text=dateToString(date)
        }
    }


}
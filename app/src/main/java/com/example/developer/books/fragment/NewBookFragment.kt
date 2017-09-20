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
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toDate
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import java.util.*

class NewBookFragment : Fragment() {
//    TODO: нет необходимости хранить ссылки на них.
//    private lateinit var buttonSave: Button
//    private lateinit var buttonCancel: Button
    private lateinit var textViewDate: TextView

    companion object {
        private const val DIALOG_DATE = "DialogDate"
        private const val REQUEST_DATE = 0
        val EXTRA_BOOK = "book"
        fun newInstance(): NewBookFragment = NewBookFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_new_book, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        buttonSave = view.findViewById(R.id.save_button)
//        buttonCancel = view.findViewById(R.id.cancel_button)
        textViewDate = view.findViewById(R.id.textView4)

        view.findViewById<View>(R.id.save_button)?.setOnClickListener {
            val title = view.findViewById<EditText>(R.id.editText_title_book).text.toString()
            val author = view.findViewById<EditText>(R.id.editText_author_book).text.toString()
            val date = textViewDate.text.toString().toDate(MMM_D_YYYY)
            val pub = view.findViewById<EditText>(R.id.editText_publication_book).text.toString()
            if (date != null
                    && title.trim().isNotEmpty()
                    && author.trim().isNotEmpty()
                    && pub.trim().isNotEmpty()) {
                val book = Book(title, author, date, pub)
                sendResult(Activity.RESULT_OK, book)
                activity.supportFragmentManager.popBackStack()
            }
        }
        view.findViewById<View>(R.id.cancel_button)?.setOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }
        view.findViewById<Button>(R.id.button_date).setOnClickListener {
            val fm = fragmentManager
            val dialog = DatePickerFragment.newInstance()
            dialog.setTargetFragment(this@NewBookFragment, REQUEST_DATE)
            dialog.show(fm, DIALOG_DATE)
        }
    }

    private fun sendResult(resultCode: Int, book: Book) {
        targetFragment ?: return
        val intent = Intent()
        intent.putExtra(NewBookFragment.EXTRA_BOOK, book)
        targetFragment.onActivityResult(targetRequestCode, resultCode, intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            REQUEST_DATE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val date = data.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as? Date ?: return
                        textViewDate.text = date.toString(MMM_D_YYYY)
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
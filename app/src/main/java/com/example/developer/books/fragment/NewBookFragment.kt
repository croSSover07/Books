package com.example.developer.books.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.EditText
import com.example.developer.books.R
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toDate
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import java.util.*


class NewBookFragment : Fragment() {

    private lateinit var textViewDate: EditText

    companion object {
        private const val DIALOG_DATE = "DialogDate"
        const val TAG = "NewBookFragment"
        private const val REQUEST_DATE = 0
        val EXTRA_BOOK = "book"
        fun newInstance(): NewBookFragment = NewBookFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_new_book, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewDate = view.findViewById(R.id.editText_date)
        textViewDate.setOnClickListener {
            val fm = fragmentManager
            val dialog = DatePickerFragment.newInstance()
            dialog.setTargetFragment(this@NewBookFragment, REQUEST_DATE)
            dialog.show(fm, DIALOG_DATE)
        }
        setHasOptionsMenu(true)
    }

    private fun sendResult(resultCode: Int, book: Book?) {
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
                        textViewDate.setText(date.toString(MMM_D_YYYY))
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.clear()
        inflater.inflate(R.menu.menu_add_book_fragment, menu)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.title_new_book_fragment)
        (activity as AppCompatActivity).supportActionBar?.subtitle = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
            if (isAnyChange() != null) {
                sendResult(Activity.RESULT_OK, isAnyChange())
                activity.supportFragmentManager.popBackStack()
            }
            true
        }
        android.R.id.home -> {
            AlertDialog.Builder(activity)
                    .setMessage("Discard all changes?")
                    .setPositiveButton(R.string.erase, { _, _ -> activity.supportFragmentManager.popBackStack() })
                    .setNegativeButton(R.string.cancel, { _, _ -> })
                    .show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun isAnyChange(): Book? {
        val title = view!!.findViewById<EditText>(R.id.editText_title_book).text.toString()
        val author = view!!.findViewById<EditText>(R.id.editText_author_book).text.toString()
        val date = textViewDate.text.toString().toDate(MMM_D_YYYY)
        val pub = view!!.findViewById<EditText>(R.id.editText_publication_book).text.toString()
        val des = view!!.findViewById<EditText>(R.id.editText_description_book).text.toString()
        return when {
            title.isEmpty() -> null
            author.isEmpty() -> null
            date == null -> null
            pub.isEmpty() -> null
            des.isEmpty() -> null
            else -> Book(title, author, date, pub, des)
        }
    }
}
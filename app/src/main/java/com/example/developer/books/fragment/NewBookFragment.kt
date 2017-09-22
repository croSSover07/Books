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
    private lateinit var textViewTitle: EditText
    private lateinit var textViewAuthor: EditText
    private lateinit var textViewPublication: EditText
    private lateinit var textViewDescription: EditText

    companion object {
        private const val DIALOG_DATE = "DialogDate"
        const val TAG = "NewBookFragment"
        private const val REQUEST_DATE = 0
        val EXTRA_BOOK = "book"
        fun newInstance(): NewBookFragment = NewBookFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
        textViewTitle = view.findViewById(R.id.editText_title_book)
        textViewAuthor = view.findViewById(R.id.editText_author_book)
        textViewPublication = view.findViewById(R.id.editText_publication_book)
        textViewDescription = view.findViewById(R.id.editText_description_book)
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
        inflater.inflate(R.menu.menu_options, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        menu?.findItem(R.id.menu_add_book)?.isVisible = false
        menu?.findItem(R.id.save_button)?.isVisible = true

        val appCompatActivity = activity as AppCompatActivity
        appCompatActivity.supportActionBar?.setTitle(R.string.title_new_book_fragment)
        appCompatActivity.supportActionBar?.subtitle = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
            val book = isAnyChange()
            if (book != null) {
                sendResult(Activity.RESULT_OK, book)
                activity.supportFragmentManager.popBackStack()
            }
            true
        }
        android.R.id.home -> {
            AlertDialog.Builder(activity)
                    .setMessage(getString(R.string.alert_question))
                    .setPositiveButton(R.string.erase, { _, _ -> activity.supportFragmentManager.popBackStack() })
                    .setNegativeButton(R.string.cancel, { _, _ -> })
                    .show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun isAnyChange(): Book? {
        val title = textViewTitle.text.toString()
        val author = textViewAuthor.text.toString()
        val date = textViewDate.text.toString().toDate(MMM_D_YYYY)
        val publication = textViewPublication.text.toString()
        val description = textViewDescription.text.toString()
        return when {
            title.isEmpty() -> null
            author.isEmpty() -> null
            date == null -> null
            publication.isEmpty() -> null
            description.isEmpty() -> null
            else -> Book(title, author, date, publication, description)
        }
    }
}
package com.example.developer.books.activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import android.widget.EditText
import com.example.developer.books.R
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toDate
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import java.util.*


class NewBookActivity : AppCompatActivity() {

    private lateinit var textViewDate: EditText
    private lateinit var textViewTitle: EditText
    private lateinit var textViewAuthor: EditText
    private lateinit var textViewPublication: EditText
    private lateinit var textViewDescription: EditText

    private lateinit var datePicker: DatePicker

    companion object {
        val EXTRA_BOOK = "book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_book)
        configView()
    }

    private fun configView() {
        textViewDate = findViewById(R.id.editText_date) as EditText
        textViewDate.setOnClickListener {
            val dialog = AlertDialog.Builder(this@NewBookActivity)
                    .setView(R.layout.dialog_date)
                    .setTitle(R.string.date_picker_title)
                    .setNegativeButton(android.R.string.cancel, { _, _ -> })
                    .setPositiveButton(android.R.string.ok, { _, _ ->
                        val year = datePicker.year
                        val month = datePicker.month
                        val day = datePicker.dayOfMonth
                        val date = GregorianCalendar(year, month, day).time
                        textViewDate.setText(date.toString(MMM_D_YYYY))
                    }).show()
            datePicker = dialog.findViewById(R.id.dialog_datepicker)
        }
        textViewTitle = findViewById(R.id.editText_title_book) as EditText
        textViewAuthor = findViewById(R.id.editText_author_book) as EditText
        textViewPublication = findViewById(R.id.editText_publication_book) as EditText
        textViewDescription = findViewById(R.id.editText_description_book) as EditText
    }

//    TODO: menu не будет optional.
//    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        super.onPrepareOptionsMenu(menu)
//        menu?.findItem(R.id.menu_add_book)?.isVisible = false
//        menu?.findItem(R.id.save_button)?.isVisible = true
//    TODO: Дважды выполняется проверка
//        supportActionBar?.setTitle(R.string.title_new_book_fragment)
//        supportActionBar?.subtitle = null
//        return true
//    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_add_book)?.isVisible = false
        menu.findItem(R.id.save_button)?.isVisible = true

//        TODO: Двойную проверку можно обойти так:
//        val supportActionBar = supportActionBar
//        if (supportActionBar != null) {
//            supportActionBar.setTitle(R.string.title_new_book_fragment)
//            supportActionBar.subtitle = null
//        }

        // TODO: Но в kotlin есть такие вот удобные вещи как apply, let, etc.
        supportActionBar?.apply {
            setTitle(R.string.title_new_book_fragment)
            subtitle = null
        }

        return true
    }

//    TODO: Желательно не перемешивать методы и группировать их.
//    private fun setBookResult(book: Book) {
//        setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_BOOK, book))
//    }

/*
      TODO: Не перемешивать имеется ввиду что бы группа методов с Menu не смешивалась с другмим методами, а так же выдерживать хронологи вызовов:
      onCreateOptionsMenu -
      -- setBookResult -- Этот метод лучше перенести после методов связанных с Menu
      onCreateOptionsMenu
      onOptionsItemSelected

      TODO: Хронология вызовов такая:
      onCreateOptionsMenu
      onCreateOptionsMenu
      onOptionsItemSelected
*/

    //    TODO: menu не будет optional.
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
            val book = isAnyChange()
            if (book != null) {
                setBookResult(book)
//                TODO: лучше будет вызывать finish()
//                super.onBackPressed()
                finish()
            }
            true
        }
        android.R.id.home -> {
            // TODO: За диалог ++.
            AlertDialog.Builder(this@NewBookActivity)
                    .setMessage(getString(R.string.alert_question))
                    .setPositiveButton(R.string.erase, { _, _ ->
                        setResult(Activity.RESULT_CANCELED)
                        super.onBackPressed()
                    })
                    .setNegativeButton(R.string.cancel, { _, _ -> })
                    .show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun setBookResult(book: Book) {
        setResult(Activity.RESULT_OK, Intent().putExtra(EXTRA_BOOK, book))
    }

    // TODO: именвоание методов, думаю его лучше назвать "newBook", префикс `is` обычно используется для методов и переменных возвращающих булевый результат true/false
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

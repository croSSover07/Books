package com.example.developer.books.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.developer.books.MainActivity
import com.example.developer.books.R
import com.example.developer.books.activity.NewBookActivity
import com.example.developer.books.adapter.BaseAdapter
import com.example.developer.books.adapter.BookAdapter
import com.example.developer.books.model.Book
import java.util.*


class BookListFragment : Fragment(), BaseAdapter.ItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var bookAdapter: BookAdapter
    private val mainActivity: MainActivity? get() = activity as? MainActivity

    companion object {
        const val REQUEST_BOOK = 0
        const val KEY_ITEMS = "key_items"
        val TAG = "BookListFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookAdapter = BookAdapter(this, savedInstanceState?.getParcelableArrayList(KEY_ITEMS))
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.book_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        TODO: Для чего тут вызов super?
//        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            BookListFragment.REQUEST_BOOK -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
//                        TODO: Поскольку data Optional, нельзя написать в конце as Book, потому как елси там будет null вылетит NPE, так же используем метод getParcelable, так как наш объект Book реализует Parcelable
//                        val book = data?.extras?.get(NewBookActivity.EXTRA_BOOK) as Book
//                        bookAdapter.addBook(book, true)
                        val book: Book = data?.extras?.getParcelable(NewBookActivity.EXTRA_BOOK) ?: return
                        bookAdapter.addBook(book, true)
                    }
                    Activity.RESULT_CANCELED -> {
                        bookAdapter.notifyDataSetChanged()
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.menu_add_book).isVisible = true
        menu.findItem(R.id.save_button).isVisible = false

//      TODO: Двойная проверка supportActionBar?
//        val appCompatActivity = mainActivity as AppCompatActivity
//        appCompatActivity.supportActionBar?.setTitle(R.string.title_books_list_fragment)
//        appCompatActivity.supportActionBar?.subtitle = null

        val appCompatActivity = mainActivity as? AppCompatActivity
        appCompatActivity?.supportActionBar?.apply {
            setTitle(R.string.title_books_list_fragment)
            subtitle = null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_add_book -> {
            startActivityForResult(Intent(mainActivity, NewBookActivity::class.java), BookListFragment.REQUEST_BOOK)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_ITEMS, ArrayList(bookAdapter.items))
    }

    override fun onItemClick(position: Int) {
        val book = bookAdapter.items[position]
        mainActivity?.replaceMainFragment(DescriptionFragment.newInstance(book), true, DescriptionFragment.TAG)
    }
}
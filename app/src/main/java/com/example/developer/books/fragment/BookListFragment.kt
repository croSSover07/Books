package com.example.developer.books.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.example.developer.books.MainActivity
import com.example.developer.books.R
import com.example.developer.books.adapter.BookAdapter
import com.example.developer.books.model.Book
import java.util.*

class BookListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var bookAdapter: BookAdapter

    private val mainActivity: MainActivity? get() = activity as? MainActivity

    companion object {
        private const val REQUEST_BOOK = 0
        const val KEY_ITEMS = "key_items"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookAdapter = BookAdapter(savedInstanceState?.getParcelableArrayList(KEY_ITEMS))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookAdapter
        setHasOptionsMenu(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        when (requestCode) {
            BookListFragment.REQUEST_BOOK -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        val book = data.extras[NewBookFragment.EXTRA_BOOK] as? Book ?: return
                        bookAdapter.addBook(book)
                        bookAdapter.notifyDataSetChanged()
                    }
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_add_book -> {
            val a = NewBookFragment.newInstance()
            a.setTargetFragment(this@BookListFragment, REQUEST_BOOK)
            mainActivity?.replaceMainFragment(a, true)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_ITEMS, ArrayList(bookAdapter.listBook))
    }
}
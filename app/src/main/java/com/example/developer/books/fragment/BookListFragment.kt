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
import com.example.developer.books.adapter.BaseAdapter
import com.example.developer.books.adapter.BookAdapter
import com.example.developer.books.model.Book
import java.util.*


class BookListFragment : Fragment(), BaseAdapter.ItemClickListener {

    private lateinit var recyclerView: RecyclerView

    private lateinit var bookAdapter: BookAdapter

    private val mainActivity: MainActivity? get() = activity as? MainActivity

    companion object {
        private const val REQUEST_BOOK = 0
        const val TAG = "BookListFragment"
        const val KEY_ITEMS = "key_items"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookAdapter = BookAdapter(this, savedInstanceState?.getParcelableArrayList(KEY_ITEMS))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookAdapter
        // TODO: LinearLayoutManager(context).orientation) - зачем это тут??
        // TODO: Посмотри документацию конструктора DividerItemDecoration!
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, LinearLayoutManager(context).orientation))
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
        inflater.inflate(R.menu.menu_options, menu)
        // TODO: Для чего данный вызов notifyDataSetChanged?
        bookAdapter.notifyDataSetChanged()
        (mainActivity as AppCompatActivity).supportActionBar?.setTitle(R.string.title_books_list_fragment)
        (mainActivity as AppCompatActivity).supportActionBar?.subtitle = null

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_add_book -> {
            val fragment = NewBookFragment.newInstance()
            fragment.setTargetFragment(this@BookListFragment, REQUEST_BOOK)
            mainActivity?.replaceMainFragment(fragment, true, NewBookFragment.TAG)
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
        val fragment = DescriptionFragment.newInstance(book)
        mainActivity?.replaceMainFragment(fragment, true, DescriptionFragment.TAG)
    }
}
package com.example.developer.books.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.developer.books.MainActivity
import com.example.developer.books.R
import com.example.developer.books.adapter.BookAdapter
import com.example.developer.books.model.Book
import java.util.*

class BookListFragment : Fragment() {
//    private lateinit var recyclerView: RecyclerView
//    private val mainActivity: MainActivity? get() = activity as? MainActivity
//    private lateinit var bookAdapter: BookAdapter

    /*
        Переменные лучше всего тоже кидать не в кучу а по смылсу, что бы они разделялись на групы по типам или же по use case`ам.

        Например:

        Отдельно View сгруппированные вместе
        private lateinit var userNameTextView: TitleView
        private lateinit var userSurnameTextView: TextView
        private lateinit var recyclerView: RecyclerView

        Отдельно адаптер
        private lateinit var adapter: RecyclerViewAdapter

        Отдельно модель
        private lateinit var user: User

        Computed property тоже отдельно
        private val mainActivity: MainActivity? get() = activity as? MainActivity

     */

    private lateinit var recyclerView: RecyclerView

    private lateinit var bookAdapter: BookAdapter

    private val mainActivity: MainActivity? get() = activity as? MainActivity

    companion object {
        private const val REQUEST_BOOK = 0
        const val KEY_ITEMS = "key_items"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*
        if (savedInstanceState != null) bookAdapter = BookAdapter(savedInstanceState.getParcelableArrayList(KEY_ITEMS))
        else {
            bookAdapter = BookAdapter(null)
        }

        По if {} else {}: если пишешь в одну строку то все должно быть в одну строку например:
        bookAdapter = if (savedInstanceState == null) BookAdapter(null) else BookAdapter(savedInstanceState.getParcelableArrayList(KEY_ITEMS))

        Если есть возможность упростить код, упрощаем его, чем меньше кода и чем он чище проще в нем ориентироваться и он лучше работает.
*/
        bookAdapter = BookAdapter(savedInstanceState?.getParcelableArrayList(KEY_ITEMS))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_book_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.button_add)?.setOnClickListener {
            val a = NewBookFragment.newInstance()
            a.setTargetFragment(this@BookListFragment, REQUEST_BOOK)
            mainActivity?.replaceMainFragment(a, true)
        }
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = bookAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        Тут super не вызывается, мы вызываем его когда сами не можем захендлить данный request в else блоки when.
//        super.onActivityResult(requestCode, resultCode, data)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_ITEMS, ArrayList(bookAdapter.listBook))
    }
}
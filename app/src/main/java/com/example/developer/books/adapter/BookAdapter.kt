package com.example.developer.books.adapter

import android.view.ViewGroup
import com.example.developer.books.extension.FORMAT_DATE_AND_PUBLICATION
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import com.example.developer.books.view.BookItemViewHolder
import java.lang.ref.WeakReference
import java.util.*

class BookAdapter(listener: ItemClickListener?, items: List<Book>?) : BaseAdapter<BookItemViewHolder, Book>(listener, items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, listener: WeakReference<ItemClickListener>?): BookItemViewHolder = BookItemViewHolder(parent, listener)

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int, item: Book) {
        holder.txtTitle.text = item.title
        // TODO: Исправь с использованием строки с форматом.
        // для получения Context можешь взять holder.itemView.context
        holder.txtDate.text = Formatter().format(FORMAT_DATE_AND_PUBLICATION, item.date.toString(MMM_D_YYYY), item.publication).toString()
        holder.txtAuthor.text = item.author
    }

    fun addBook(book: Book, notify: Boolean = true) {
        items.add(book)
        if (notify) {
            notifyDataSetChanged()
        }
    }
}


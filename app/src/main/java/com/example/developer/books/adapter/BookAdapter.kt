package com.example.developer.books.adapter

import android.view.ViewGroup
import com.example.developer.books.R
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import com.example.developer.books.view.BookItemViewHolder
import java.lang.ref.WeakReference

class BookAdapter(listener: ItemClickListener?, items: List<Book>?) : BaseAdapter<BookItemViewHolder, Book>(listener, items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, listener: WeakReference<ItemClickListener>?): BookItemViewHolder = BookItemViewHolder(parent, listener)

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int, item: Book) {
        holder.txtTitle.text = item.title
        holder.txtDate.text = holder.itemView.context.getString(R.string.format_date_publication, item.date.toString(MMM_D_YYYY), item.publication)
        holder.txtAuthor.text = item.author
    }

    fun addBook(book: Book, notify: Boolean = true) {
        items.add(book)
        if (notify) {
            notifyDataSetChanged()
        }
    }
}


package com.example.developer.books.adapter

import android.view.ViewGroup
import com.example.developer.books.View.BookItemViewHolder
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book
import java.lang.ref.WeakReference

class BookAdapter(listener: ItemClickListener?, items: List<Book>?) : BaseAdapter<BookItemViewHolder, Book>(listener, items) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int, listener: WeakReference<ItemClickListener>?): BookItemViewHolder {
        return BookItemViewHolder(parent, listener)
    }

    override fun onBindViewHolder(holder: BookItemViewHolder, position: Int, item: Book) {
        holder.txtTitle.text = item.title
        holder.txtDate.text = item.date.toString(MMM_D_YYYY)
        holder.txtAuthor.text = item.author
    }

    fun addBook(book: Book) {
        items.add(book)
        notifyDataSetChanged()
    }
}


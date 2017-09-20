package com.example.developer.books.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.developer.books.R
import com.example.developer.books.extension.MMM_D_YYYY
import com.example.developer.books.extension.toString
import com.example.developer.books.model.Book

class BookAdapter(items: List<Book>? = null) : RecyclerView.Adapter<BookViewHolder>() {

    val listBook: MutableList<Book> = items?.toMutableList() ?: mutableListOf()
    override fun getItemCount(): Int = listBook.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder =
            BookViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_row, parent, false))

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = listBook[position]
        holder.txtTitle.text = book.title
        holder.txtAuthor.text = book.author
        holder.txtDate.text = book.date.toString(MMM_D_YYYY)
    }

    fun updateItems(listBook: List<Book>?) {
        this.listBook.clear()
        if (listBook != null && listBook.isNotEmpty()) {
            this.listBook.addAll(listBook)
        }
        notifyDataSetChanged()
    }

    fun addBook(book: Book) {
        listBook.add(book)
        notifyDataSetChanged()
    }
}


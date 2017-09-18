package com.example.developer.books.model

/**
 * Created by developer on 18.09.17.
 */
object BooksList {
    var listBooks: ArrayList<Book>? = null
    init {
        println("Initializing object: $this")
    }
    fun addBook(book: Book) {
        listBooks!!.add(book)
    }

}
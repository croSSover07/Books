package com.example.developer.books.model

object BooksList {
    var listBooks: ArrayList<Book>? = null
    init {
        println("Initializing object: $this")
    }
    fun addBook(book: Book) {
        listBooks!!.add(book)
    }

}
package com.example.developer.books.model;

import java.util.ArrayList;
import java.util.List;

public class BooksListJava {
    private static BooksListJava sBooksListJava;
    private final List<Book> mList;
    public List<Book> getmList() {
        return mList;
    }




    public static BooksListJava get() {
        if (sBooksListJava == null) {
            sBooksListJava = new BooksListJava();
        }
        return sBooksListJava;
    }
    private BooksListJava() {
        mList=new ArrayList<>();
    }
    public void addBook(Book book){
        mList.add(book);
    }
}

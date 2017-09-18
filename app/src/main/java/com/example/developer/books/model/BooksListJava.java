package com.example.developer.books.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 18.09.17.
 */

public class BooksListJava {
    private static BooksListJava sBooksListJava;
    private List<Book> mList;
    public List<Book> getmList() {
        return mList;
    }




    public static BooksListJava get(Context context) {
        if (sBooksListJava == null) {
            sBooksListJava = new BooksListJava(context);
        }
        return sBooksListJava;
    }
    private BooksListJava(Context context) {
        mList=new ArrayList<>();
    }
    public void addBook(Book book){
        mList.add(book);
    }
}

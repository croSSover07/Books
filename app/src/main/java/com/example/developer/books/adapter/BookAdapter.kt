package com.example.developer.books.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.books.R
import com.example.developer.books.model.Book

/**
 * Created by developer on 18.09.17.
 */
class BookAdapter(private  val listBook:List<Book>, private val mContext:Context):RecyclerView.Adapter<BookViewHolder>() {

    private val inflater: LayoutInflater


    init{
        inflater= LayoutInflater.from(mContext)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookViewHolder {
        val itemView=inflater.inflate(R.layout.layout_row,parent,false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder?, position: Int) {
        holder!!.txtTitle.text=listBook[position].title
    }
}

class BookViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var txtTitle: TextView
    init{
        txtTitle=itemView.findViewById(R.id.textView_title_row)
    }

}

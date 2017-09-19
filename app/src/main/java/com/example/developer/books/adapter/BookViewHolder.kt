package com.example.developer.books.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.developer.books.R

class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var txtTitle: TextView = itemView.findViewById(R.id.textView_title_row)
    var txtAuthor: TextView = itemView.findViewById(R.id.textView_author_row)
    var txtDate: TextView = itemView.findViewById(R.id.textView_date_row)
    var txtPub: TextView = itemView.findViewById(R.id.textView_pub_row)
}
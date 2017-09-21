package com.example.developer.books.View

import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.books.R
import com.example.developer.books.adapter.BaseAdapter
import java.lang.ref.WeakReference


class BookItemViewHolder(
        parent: ViewGroup,
        listener: WeakReference<BaseAdapter.ItemClickListener>?
) : BaseAdapter.ViewHolder(parent, R.layout.layout_row, listener) {
    //TODO
    var txtTitle: TextView = itemView.findViewById(R.id.textView_title_row)
    var txtAuthor: TextView = itemView.findViewById(R.id.textView_author_row)
    var txtDate: TextView = itemView.findViewById(R.id.textView_date_row)
}
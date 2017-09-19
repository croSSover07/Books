package com.example.developer.books.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.developer.books.R
import com.example.developer.books.fragment.NewBookFragment
import com.example.developer.books.model.Book

class BookAdapter(private  val listBook:List<Book>, mContext:Context):RecyclerView.Adapter<BookViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(mContext)


    override fun getItemCount(): Int = listBook.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BookViewHolder {
        val itemView=inflater.inflate(R.layout.layout_row,parent,false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder?, position: Int) {
        holder!!.txtTitle.text=listBook[position].title
        holder.txtAuthor.text=listBook[position].author
        holder.txtDate.text=NewBookFragment.dateToString( listBook[position].date )
        holder.txtPub.text=listBook[position].publication
    }
}

class BookViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    var txtTitle: TextView = itemView.findViewById(R.id.textView_title_row)
    var txtAuthor: TextView = itemView.findViewById(R.id.textView_author_row)
    var txtDate: TextView = itemView.findViewById(R.id.textView_date_row)
    var txtPub: TextView = itemView.findViewById(R.id.textView_pub_row)

}

package com.example.developer.books.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Book(
        val title: String?,
        val author: String?,
        val date: Date,
        val publication: String?,
        val description: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            Date(parcel.readLong()),
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeLong(date.time)
        parcel.writeString(publication)
        parcel.writeString(description)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book = Book(parcel)
        override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
    }
}
package com.example.developer.books.extension

import java.text.SimpleDateFormat
import java.util.*

val MMM_D_YYYY = "MMM dd yyyy"
val FORMAT_DATE_AND_PUBLICATION="%s && %s"

fun String.toDate(format: String) = try {
    SimpleDateFormat(format, Locale.ENGLISH).parse(this)
} catch (ex: Exception) {
    null
}

fun Date.toString(format: String) = try {
    SimpleDateFormat(format, Locale.ENGLISH).format(this)
} catch (ex: Exception) {
    null
}


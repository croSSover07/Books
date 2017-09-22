package com.example.developer.books.extension

import java.lang.ref.WeakReference

fun <T> T.weak() = WeakReference(this)


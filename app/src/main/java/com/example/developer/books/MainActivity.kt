package com.example.developer.books


import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.developer.books.activity.BaseActivity
import com.example.developer.books.fragment.BookListFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (fragmentInContainer(R.id.frame_layout) == null) {
            replaceFragment(R.id.frame_layout, BookListFragment())
        }
    }

    fun replaceMainFragment(fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) =
            replaceFragment(R.id.frame_layout, fragment, addToBackStack, backStackName)
}

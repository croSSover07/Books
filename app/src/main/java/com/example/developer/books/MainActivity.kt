package com.example.developer.books


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.example.developer.books.activity.BaseActivity
import com.example.developer.books.fragment.BookListFragment
import com.example.developer.books.fragment.DescriptionFragment
import com.example.developer.books.model.Book

class MainActivity : BaseActivity() {
    var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isTwoPane = findViewById(R.id.book_description_container) != null

        val descriptionFragment = supportFragmentManager.findFragmentByTag(DescriptionFragment.TAG)
        val bookListFragment = supportFragmentManager.findFragmentByTag(BookListFragment.TAG)

        when {
            isTwoPane -> when {
                descriptionFragment != null -> {
                    supportFragmentManager.beginTransaction().remove(descriptionFragment).remove(bookListFragment).commit()
                    supportFragmentManager.executePendingTransactions()
                    replaceFragment(R.id.frame_layout, bookListFragment, false, BookListFragment.TAG)
                    replaceFragment(R.id.book_description_container,
                            DescriptionFragment.newInstance(descriptionFragment.arguments[DescriptionFragment.KEY_BOOK] as Book),
                            false, DescriptionFragment.TAG)
                }
                else -> when {
                    bookListFragment != null -> {
                        supportFragmentManager.beginTransaction().remove(bookListFragment).commit()
                        supportFragmentManager.executePendingTransactions()
                        replaceFragment(R.id.frame_layout, bookListFragment, false, BookListFragment.TAG)
                    }
                    else -> replaceFragment(R.id.frame_layout, BookListFragment(), false, BookListFragment.TAG)
                }
            }
            else -> when {
                descriptionFragment != null -> {
                    supportFragmentManager.beginTransaction().remove(descriptionFragment).remove(bookListFragment).commit()
                    supportFragmentManager.executePendingTransactions()
                    replaceFragment(R.id.frame_layout, bookListFragment, false, BookListFragment.TAG)
                    replaceFragment(R.id.frame_layout, descriptionFragment, true, DescriptionFragment.TAG)
                }
                else -> when {
                    bookListFragment != null -> {
                        supportFragmentManager.beginTransaction().remove(bookListFragment).commit()
                        supportFragmentManager.executePendingTransactions()
                        replaceFragment(R.id.frame_layout, bookListFragment, false, BookListFragment.TAG)
                    }
                    else -> replaceFragment(R.id.frame_layout, BookListFragment(), false, BookListFragment.TAG)
                }
            }
        }
    }

    fun replaceMainFragment(fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {
        if (isTwoPane) {
            if (backStackName == DescriptionFragment.TAG) {
                replaceFragment(R.id.book_description_container, fragment, false, backStackName)
            } else {
                replaceFragment(R.id.frame_layout, fragment, true, backStackName)
            }
        } else {
            replaceFragment(R.id.frame_layout, fragment, addToBackStack, backStackName)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return super.onCreateOptionsMenu(menu)
    }
}

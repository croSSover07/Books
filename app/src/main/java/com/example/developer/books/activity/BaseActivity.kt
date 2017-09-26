package com.example.developer.books.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount != 0)
        }
    }

    fun replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {
        val transaction = supportFragmentManager.beginTransaction().replace(containerId, fragment, backStackName)
        if (addToBackStack) {
            transaction.addToBackStack(backStackName)
        }
        transaction.commit()
    }
}
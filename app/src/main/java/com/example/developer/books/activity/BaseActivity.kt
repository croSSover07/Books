package com.example.developer.books.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.developer.books.R

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        supportFragmentManager.addOnBackStackChangedListener {
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount != 0)
        }
    }

    fun fragmentInContainer(containerId: Int): Fragment? = supportFragmentManager.findFragmentById(containerId)

    fun replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {
        val transaction = supportFragmentManager.beginTransaction().replace(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(backStackName)
        }
        transaction.commit()
    }

    @LayoutRes
    private fun getLayoutResId(): Int = R.layout.activity_masterdetail
}
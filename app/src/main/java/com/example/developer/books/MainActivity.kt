package com.example.developer.books


import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import com.example.developer.books.activity.BaseActivity
import com.example.developer.books.fragment.BookListFragment
import com.example.developer.books.fragment.DescriptionFragment
import com.example.developer.books.fragment.NewBookFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        if (fragmentInContainer(R.id.frame_layout) == null) {
            replaceFragment(R.id.frame_layout, BookListFragment())
        }
    }

    fun replaceMainFragment(fragment: Fragment, addToBackStack: Boolean = false, backStackName: String? = null) {
        if (findViewById(R.id.main_frame_layout) != null) {
            if (backStackName == NewBookFragment.TAG) {
                replaceFragment(R.id.main_frame_layout, fragment, addToBackStack, backStackName)
            }
            if (backStackName == DescriptionFragment.TAG) {
                replaceFragment(R.id.detail_frame_layout, fragment, false, backStackName)
            }
            if (backStackName == BookListFragment.TAG) {
                replaceFragment(R.id.frame_layout, fragment, addToBackStack, backStackName)
            }
        } else {
            replaceFragment(R.id.frame_layout, fragment, addToBackStack, backStackName)
        }
    }

    // TODO: Неплохое решение, имеет смысл вынести его в BaseActivity и выполнять setContentView(getLayoutResId()) там.
    @LayoutRes
    private fun getLayoutResId(): Int = R.layout.activity_masterdetail
}

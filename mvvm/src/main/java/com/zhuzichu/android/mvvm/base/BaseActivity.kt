package com.zhuzichu.android.mvvm.base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.zhuzichu.android.mvvm.R
import com.zhuzichu.android.mvvm.base.BaseFragment.Companion.KEY_ARGUMENT
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract fun setNavGraph(): Int

    val navController by lazy { findNavController(R.id.delegate_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = R.id.delegate_container
        setContentView(container)
        if (savedInstanceState == null) {
            val fragment = NavHostFragment.create(setNavGraph())
            initArgument(fragment)
            supportFragmentManager.beginTransaction()
                .replace(R.id.delegate_container, fragment)
                .setPrimaryNavigationFragment(fragment)
                .commit()
        }
    }

    private fun initArgument(fragment: NavHostFragment) {
        var bundle = fragment.arguments
        if (bundle != null) {
            bundle.putParcelable(KEY_ARGUMENT, intent.getParcelableExtra(KEY_ARGUMENT))
        } else {
            bundle = bundleOf(KEY_ARGUMENT to intent.getParcelableExtra(KEY_ARGUMENT))
        }
        fragment.arguments = bundle
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
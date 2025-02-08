package com.kankinara.firebasetutorial.view

import androidx.annotation.ContentView
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

open class BaseActivity:AppCompatActivity() {

    fun setFragment(fragment: Fragment){
       setFragment(android.R.id.content,fragment)
    }
    private fun setFragment(@IdRes containerViewId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(containerViewId, fragment, fragment.javaClass.simpleName)
            .commitNowAllowingStateLoss()
    }
}
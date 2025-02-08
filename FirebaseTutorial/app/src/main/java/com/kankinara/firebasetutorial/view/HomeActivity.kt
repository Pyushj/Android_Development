package com.kankinara.firebasetutorial.view

import android.os.Bundle

class HomeActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(HomeFragment())
    }
}
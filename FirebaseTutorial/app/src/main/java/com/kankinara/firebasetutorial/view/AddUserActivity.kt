package com.kankinara.firebasetutorial.view

import android.os.Bundle

class AddUserActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(AddUserFragment())
    }
}
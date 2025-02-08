package com.kankinara.systeminteraction

import android.os.Bundle

class EmailActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(EmailFragment())
    }
}
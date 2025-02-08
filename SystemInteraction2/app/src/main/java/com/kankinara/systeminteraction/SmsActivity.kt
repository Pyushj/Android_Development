package com.kankinara.systeminteraction

import android.os.Bundle

class SmsActivity:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(SmsFragment())
    }

}
package com.kankinara.systeminteraction

import android.os.Bundle

class NotificationActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(NotificationFragment())
    }
}
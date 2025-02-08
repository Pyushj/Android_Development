package com.kankinara.systeminteraction

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

class CallActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("CallActivity", "onCreate()")
        setFragment(CallFragment())
    }
}
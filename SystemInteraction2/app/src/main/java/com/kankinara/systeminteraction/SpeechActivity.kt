package com.kankinara.systeminteraction

import android.os.Bundle

class SpeechActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(SpeechFragment())
    }
}
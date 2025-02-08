package com.kankinara.systeminteraction.push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.kankinara.systeminteraction.NotificationFragment

class ReceiverAction : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let {
            val messageText = intent.getStringExtra(NotificationFragment.TOAST_KEY_EXTRA)
            Toast.makeText(context, messageText, Toast.LENGTH_LONG).show()
        }
    }
}
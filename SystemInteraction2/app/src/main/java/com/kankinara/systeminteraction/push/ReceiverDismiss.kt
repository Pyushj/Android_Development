package com.kankinara.systeminteraction.push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationManagerCompat
import com.kankinara.systeminteraction.NotificationFragment

class ReceiverDismiss:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val notificationManagerCompat: NotificationManagerCompat =
                NotificationManagerCompat.from(context)
            notificationManagerCompat.cancel(NotificationFragment.NOTIFICATION_ID)
        }

    }
}
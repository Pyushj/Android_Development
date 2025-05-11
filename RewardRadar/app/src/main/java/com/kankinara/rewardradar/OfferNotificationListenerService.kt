package com.kankinara.rewardradar

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat

class OfferNotificationListenerService : NotificationListenerService() {
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val packageName = sbn?.packageName ?: return
        val notification = sbn.notification
        val extras = notification.extras
        val title = extras.getString(Notification.EXTRA_TITLE) ?: ""
        val text = extras.getCharSequence(Notification.EXTRA_TEXT)?.toString() ?: ""

        // Filter known UPI/payment apps
        if (packageName in listOf("com.phonepe.app", "net.one97.paytm", "com.google.android.apps.nbu.paisa.user")) {
            if (text.contains("cashback", ignoreCase = true) || text.contains("reward", ignoreCase = true) || title.contains("Offer", ignoreCase = true)) {
                Log.d("OfferTracker", "Offer detected from $packageName: $title - $text")

                // TODO: Save to Room DB or trigger a local notification
                sendLocalNotification(title, text)
            }
        }
    }

    private fun sendLocalNotification(title: String, message: String) {
        val channelId = "offer_channel"
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Offer Alerts", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("💰 New Cashback Offer")
            .setContentText("$title: $message")
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(), notification)
    }
}

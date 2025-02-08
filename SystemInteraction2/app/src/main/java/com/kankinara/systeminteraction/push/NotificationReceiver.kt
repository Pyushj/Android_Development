package com.kankinara.systeminteraction.push

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.kankinara.systeminteraction.NotificationFragment
import com.kankinara.systeminteraction.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null) {
            val builder = NotificationCompat.Builder(context, NotificationFragment.CHANNEL_ID)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel =
                    NotificationChannel(
                        NotificationFragment.CHANNEL_ID,
                        "ONE",
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                val manager: NotificationManager = ContextCompat.getSystemService(
                    context,
                    NotificationManager::class.java
                ) as NotificationManager
                Log.i(NotificationFragment.TAG, "Notification manager $manager")

                manager.createNotificationChannel(channel)

                builder.setSmallIcon(R.drawable.baseline_notifications_24)
                    .setContentTitle("Systems App")
                    .setContentText("This is to notify you that this app is fucked up")
            } else {
                builder.setSmallIcon(R.drawable.baseline_notifications_24)
                    .setContentTitle("Systems App Old")
                    .setContentText("This is to notify you that this app is fucked up")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            }

            val notificationManagerCompat: NotificationManagerCompat =
                NotificationManagerCompat.from(context)


            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notificationManagerCompat.notify(1, builder.build())
            }
        }
    }
}
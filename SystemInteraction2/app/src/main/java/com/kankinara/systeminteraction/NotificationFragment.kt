package com.kankinara.systeminteraction

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.kankinara.systeminteraction.databinding.FrgamentNotificationBinding
import com.kankinara.systeminteraction.push.ReceiverAction
import com.kankinara.systeminteraction.push.ReceiverDismiss

class NotificationFragment : Fragment() {
    private lateinit var binding: FrgamentNotificationBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FrgamentNotificationBinding.inflate(inflater, container, false)
        binding.fragment = this@NotificationFragment
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )
        }
    }

    fun onClickSend() {
        //Click Action
        val intent = Intent(activity?.applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            activity?.applicationContext,
            500,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //Action Button
        val actionIntent = Intent(activity?.applicationContext, ReceiverAction::class.java)
        actionIntent.putExtra(TOAST_KEY_EXTRA, "You clicked OK")
        val actionPendingIntent = PendingIntent.getBroadcast(
            activity?.applicationContext,
            501,
            actionIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //Dismiss Button
        val dismissIntent = Intent(activity?.applicationContext, ReceiverDismiss::class.java)
        val dismissPendingIntent = PendingIntent.getBroadcast(
            activity?.applicationContext,
            502,
            dismissIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //Icon
        val icon: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.android_pic)

        val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, "ONE", NotificationManager.IMPORTANCE_DEFAULT)
            val manager: NotificationManager = getSystemService(
                requireContext(),
                NotificationManager::class.java
            ) as NotificationManager
            Log.i(TAG, "Notification manager $manager")

            manager.createNotificationChannel(channel)

            builder.setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Systems App")
                .setContentText("This is to notify you that this app is fucked up")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.baseline_notifications_24, "Ok", actionPendingIntent)
                .addAction(R.drawable.baseline_notifications_24, "Dismiss", dismissPendingIntent)
                .setLargeIcon(icon)
        } else {
            builder.setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle("Systems App Old")
                .setContentText("This is to notify you that this app is fucked up")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.baseline_notifications_24, "Ok", actionPendingIntent)
                .addAction(R.drawable.baseline_notifications_24, "Dismiss", dismissPendingIntent)
                .setLargeIcon(icon)
        }

        val notificationManagerCompat: NotificationManagerCompat =
            NotificationManagerCompat.from(requireContext())

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                200
            )
        } else {
            notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "1"
        const val NOTIFICATION_ID = 1
        const val TOAST_KEY_EXTRA = "toast_key"
        const val TAG = "NotificationFragment"
    }
}
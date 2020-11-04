package com.example.uidesign.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import com.example.uidesign.MainActivity
import com.example.uidesign.R

class HomeFragment : Fragment() {
    private lateinit var subscribe: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe = view.findViewById(R.id.notificationButton)

        subscribe.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        val notificationID = 234
        val context = requireContext()
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channelID = "my_channel_01"
            val name: CharSequence = "my_channel"
            val description = "This is my channel"
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(channelID, name, importance)
            mChannel.description = description
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(mChannel)

            val builer = NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Firebase Notification")
                .setContentText("My first FCM Notification in my App")
                .setSound(soundUri)

            val resultIntent = Intent(context, MainActivity::class.java)
            val stackBuilder = TaskStackBuilder.create(context)
            stackBuilder.addParentStack(MainActivity::class.java)
            stackBuilder.addNextIntent(resultIntent)
            val pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

            builer.setContentIntent(pendingIntent)
            notificationManager.notify(notificationID, builer.build())
        }
    }
}

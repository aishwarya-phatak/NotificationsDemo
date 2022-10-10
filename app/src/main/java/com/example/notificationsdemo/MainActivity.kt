package com.example.notificationsdemo

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ContentInfoCompat
import com.example.notificationsdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var notificationManager: NotificationManagerCompat
    val notificationChannelId = "bitcode_entertainment_channel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationManager = NotificationManagerCompat.from(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        simpleNotification()

       // createNotificationChannels()

    }

    fun simpleNotification(){
        binding.btnSimpleNotification.setOnClickListener {
            var notificationBuilder = NotificationCompat.Builder(this,notificationChannelId)
            notificationBuilder.setContentTitle("Bitcode Technologies")
            notificationBuilder.setContentText("Welcome To Bitcode!")

            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)
            var largeIcon = BitmapFactory.decodeResource(resources,R.mipmap.ic_launcher)
            notificationBuilder.setLargeIcon(largeIcon)
            notificationBuilder.color = Color.RED
            notificationBuilder.setNumber(3)
            notificationBuilder.setVibrate(
                LongArray(10,{index -> 400})
            )

            notificationBuilder.setLights(Color.RED,500,400)
            notificationBuilder.setAutoCancel(true)
            notificationBuilder.setOngoing(true)
            notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET)

            var actionIntent = Intent(this,SecondActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(
                this,
                1,
                actionIntent,
                PendingIntent.FLAG_MUTABLE
            )

            notificationBuilder.setContentIntent(actionPendingIntent)

            var notification = notificationBuilder.build()
            notificationManager.notify(101,notification)
        }
    }

    fun createNotificationChannels(){
        var channelEntertainmentBuilder = NotificationChannelCompat.Builder(
            "bitcode_entertainment_channel",NotificationManager.IMPORTANCE_DEFAULT
        )

        channelEntertainmentBuilder.setName("Bitcode Channel")
        channelEntertainmentBuilder.setDescription("Bitcode demos")
        channelEntertainmentBuilder.setShowBadge(true)

        notificationManager.createNotificationChannel(channelEntertainmentBuilder.build())
    }
}
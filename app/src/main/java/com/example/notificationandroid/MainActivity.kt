package com.example.notificationandroid

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import com.example.notificationandroid.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    var _binding : ActivityMainBinding?= null
    val binding get() = _binding!!

    private var notificationChannel = "channelID"
    private val notification_ID = 0
    private lateinit var notificationManager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        binding.toastTV.setOnClickListener {
            Toast.makeText(this , "Toast notification" , Toast.LENGTH_SHORT).show()
        }

        binding.SnackBarTV.setOnClickListener {
            showSnackBar("SnackBar notification")
        }

        binding.TextTv.setOnClickListener {
            showTextNotification()
        }
        binding.ActionNotificationTV.setOnClickListener {
            CounterNotificationService(this).showNotification(Counter.value)
        }



    }

    private fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
        snackBar.setAction("OKay") {
            Toast.makeText(this, "okay clicked", Toast.LENGTH_SHORT).show()
        }
        snackBar.setBackgroundTint(resources.getColor(R.color.purple_200))
        snackBar.setActionTextColor(resources.getColor(R.color.teal_200))
        snackBar.show()
    }

    private fun showTextNotification() {

        val intent = Intent(this , MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this , 1  , intent , PendingIntent.FLAG_IMMUTABLE)
        val notification = NotificationCompat.Builder(this , CounterNotificationService.notificationChannel)
            .setContentTitle("Notification Title")
            .setContentText("This is the content text")
            .setSmallIcon(R.drawable.ic_notifications)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true) // to cancel the notification on press event
            .setOngoing(true) // stopping your to swipe notification away
            .build()
        notificationManager.notify(notification_ID , notification)

    }

}
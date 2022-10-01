package com.example.notificationandroid

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class CounterNotificationService(
    private val context: Context
) {

    fun showNotification(counter:Int){
        val activityIntent = Intent(context , MainActivity::class.java)
        //Pending intent to open the MainActivity when the user taps on the notification .
        val pendingIntent = PendingIntent.getActivity(context , 1 , activityIntent,PendingIntent.FLAG_IMMUTABLE)
        // increment pendingIntent to create a broadcast action regarding the increment action .
        val incrementPendingIntent = PendingIntent.getBroadcast(
            context ,
            2,
            Intent(context , CounterNotificationReceiver::class.java).apply {
                this.action = "Increment"
                this.putExtra("increment" , "increment")
            },
            PendingIntent.FLAG_IMMUTABLE
        )
        // decrementPendingIntent to create a broadcast action regarding the decrement action
        val decrementPendingIntent = PendingIntent.getBroadcast(
            context ,
            3,
            Intent(context , CounterNotificationReceiver::class.java).apply {
                this.action = "Decrement"
                this.putExtra("decrement" , "decrement")
            },
            PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context , notificationChannel)
            .setSmallIcon(R.drawable.icpending_actions)
            .setContentTitle("Action Notification")
            .setContentText("The count is $counter")
            .setContentIntent(pendingIntent)
            .addAction(
                R.drawable.icpending_actions ,
                "Increment" ,
                incrementPendingIntent
            )
            .addAction(
                R.drawable.icpending_actions ,
                "decrement" ,
                decrementPendingIntent
            )
            .build()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1 , notification)
    }
    companion object{
        const val  notificationChannel = "channelID"
    }
}
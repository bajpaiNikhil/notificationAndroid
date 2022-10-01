package com.example.notificationandroid

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class CounterNotificationReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val incrementValue = intent.getStringExtra("increment")
        val decrementValue = intent.getStringExtra("decrement")

        val service = CounterNotificationService(context)
        Log.d("CounterNotificationReceiver" , "${service},${intent.action} , $incrementValue , $decrementValue")
        //service.showNotification(++Counter.value)
        if (intent.action == "Increment"){
            service.showNotification(++Counter.value)
        }
        else if (intent.action == "Decrement")
            if (Counter.value!=0)
                service.showNotification(--Counter.value)
    }
}
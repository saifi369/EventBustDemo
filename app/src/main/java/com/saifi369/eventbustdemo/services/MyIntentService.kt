package com.saifi369.eventbustdemo.services

import android.app.IntentService
import android.content.Intent
import com.saifi369.eventbustdemo.events.MessageEvent
import org.greenrobot.eventbus.EventBus

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {

        if (intent != null) {

            val message1=intent.getStringExtra("key1")
            val message2=intent.getStringExtra("key2")

            //do some work here

            var messageEvent=MessageEvent("$message1 from Service")

            EventBus.getDefault().post(messageEvent)

            Thread.sleep(3000)

            messageEvent=MessageEvent("$message2 from Service")

            EventBus.getDefault().post(messageEvent)

        }

    }
}

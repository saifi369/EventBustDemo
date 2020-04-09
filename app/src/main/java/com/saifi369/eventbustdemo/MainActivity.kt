package com.saifi369.eventbustdemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.saifi369.eventbustdemo.events.MessageEvent
import com.saifi369.eventbustdemo.services.MyIntentService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {

    private lateinit var txt_output:TextView
    private lateinit var btn_runcode: Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        btn_runcode = findViewById(R.id.btn_runcode)
        txt_output=findViewById(R.id.txt_output)

        EventBus.getDefault().register(this)

        btn_runcode.setOnClickListener {

            val serviceIntent = Intent(this@MainActivity, MyIntentService::class.java)

            serviceIntent.putExtra("key1", "This is message 1")
            serviceIntent.putExtra("key2", "This is message 2")

            startService(serviceIntent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onEventReceived(event:MessageEvent){

        if(txt_output.text.equals("Ready to Learn!"))
            txt_output.text=""

        txt_output.append("${event.message} \n")
        txt_output.append(Thread.currentThread().name)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


}
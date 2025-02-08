package com.kankinara.systeminteraction

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var smsButton: Button
    private lateinit var emailButton: Button
    private lateinit var callButton: Button
    private lateinit var speechButton: Button
    private lateinit var notificationButton: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //SMS button
        smsButton = findViewById(R.id.smsButton)
        smsButton.setOnClickListener {
            val intent = Intent(this, SmsActivity::class.java)
            startActivity(intent)
        }
        //Email Button
        emailButton =findViewById(R.id.emailButton)
        emailButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,EmailActivity::class.java))
        }
        //Call Button
        callButton =findViewById(R.id.phoneButton)
        callButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,CallActivity::class.java))
        }
        //Speech Button
        speechButton =findViewById(R.id.speechButton)
        speechButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,SpeechActivity::class.java))
        }

        //Notification Button
        notificationButton =findViewById(R.id.notificationButton)
        notificationButton.setOnClickListener {
            startActivity(Intent(this@MainActivity,NotificationActivity::class.java))
        }
    }
}
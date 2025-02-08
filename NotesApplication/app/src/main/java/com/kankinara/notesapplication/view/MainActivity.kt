package com.kankinara.notesapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.kankinara.notesapplication.R

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)

        textView.setOnClickListener {
            startActivity(Intent(this,NoteListActivity::class.java))
        }
        button = findViewById(R.id.startNotesActivity)
        button.setOnClickListener {
            startActivity(Intent(this,NoteListActivity::class.java))
        }
    }
}
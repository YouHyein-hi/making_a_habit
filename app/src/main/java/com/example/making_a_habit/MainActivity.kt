package com.example.making_a_habit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val creating_btn = findViewById<ImageButton>(R.id.creating_btn) as ImageButton

        creating_btn.setOnClickListener {
            var intent = Intent(this, CreatingHabitActivity::class.java)
            startActivity(intent)
        }
    }
}
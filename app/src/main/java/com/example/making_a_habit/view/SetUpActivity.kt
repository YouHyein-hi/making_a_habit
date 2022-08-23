package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.R

class SetUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.setup_page)

        val back_btn = findViewById<ImageView>(R.id.back_btn_setuppage)

        back_btn.setOnClickListener{
            finish()
        }
    }

}
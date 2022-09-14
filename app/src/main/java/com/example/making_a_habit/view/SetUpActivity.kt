package com.example.making_a_habit.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.making_a_habit.R
import com.example.making_a_habit.databinding.ListDonehabitPageBinding
import com.example.making_a_habit.databinding.SetupPageBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity.setActivityTitle

class SetUpActivity : AppCompatActivity() {

    /***** veiwBinding *****/
    private lateinit var binding: SetupPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /***** veiwBinding *****/
        binding = SetupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtnSetuppage.setOnClickListener{
            finish()
        }

        binding.opensourceLayout.setOnClickListener { it ->
            Intent(applicationContext, OssLicensesMenuActivity::class.java).also {
                startActivity(it)
            }
            true
        }
    }

}
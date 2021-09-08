package com.example.stock_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val btn_test = findViewById<Button>(R.id.btn_Test)

        btn_test.setOnClickListener(){
            val intent = Intent(this, Detail_Activity::class.java).apply {
                val message :String = "Pega"
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
            Toast.makeText(this,"go to next",Toast.LENGTH_SHORT).show()
        }
    }
}
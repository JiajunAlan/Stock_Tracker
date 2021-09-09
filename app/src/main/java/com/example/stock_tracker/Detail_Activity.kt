package com.example.stock_tracker

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Detail_Activity : AppCompatActivity() {
    var name :String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide title bar
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)

        name = intent.getStringExtra(EXTRA_MESSAGE).toString()
        Toast.makeText(this,name, Toast.LENGTH_SHORT).show()

        val tv_DetaiName = findViewById<TextView>(R.id.tv_Detail_name)
        tv_DetaiName.apply { text = name }

        val btn_back = findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            //Toast.makeText(this,"go back", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onPause() {
        finish()
        super.onPause()
    }

}
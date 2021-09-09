package com.example.stock_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE

import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide the titlebar.
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val btn_test = findViewById<Button>(R.id.btn_Test)
        val btn_test2 = findViewById<Button>(R.id.btn_test2)

        val url = "https://71iztxw7wh.execute-api.us-east-1.amazonaws.com/interview/favorite-stocks"


        btn_test.setOnClickListener(){
            val intent = Intent(this, Detail_Activity::class.java).apply {
                val message :String = "Pega"
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
            Toast.makeText(this,"go to next",Toast.LENGTH_SHORT).show()
        }

        btn_test2.setOnClickListener(){

            val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                { response ->
                    val pega :JSONObject = response.getJSONObject("PEGA")
                    val name :String = pega.getString("name")

                    Toast.makeText(this,name,Toast.LENGTH_LONG).show()
                },
                { error ->
                    // Handle error
                    Toast.makeText(this,"Network Error!",Toast.LENGTH_LONG).show()
                }
            )
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
        }
    }




}
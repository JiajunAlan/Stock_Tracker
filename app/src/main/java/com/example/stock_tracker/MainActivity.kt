package com.example.stock_tracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE

import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_tracker.CompanyData.VolleyResponseListener
import com.example.stock_tracker.model.CompanyModel


class MainActivity : AppCompatActivity() {
    val url = "https://71iztxw7wh.execute-api.us-east-1.amazonaws.com/interview/favorite-stocks"
    lateinit var datalist : MutableList<CompanyModel>
    lateinit var recyclerviewAdapter: RecyclerViewAdapter
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide the titlebar.
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val btn_test = findViewById<Button>(R.id.btn_Test)
        val btn_test2 = findViewById<Button>(R.id.btn_test2)
        val rv = findViewById<RecyclerView>(R.id.rv_list)

        btn_test.setOnClickListener(){
            val intent = Intent(this, Detail_Activity::class.java).apply {
                val message :String = "Pega"
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
            Toast.makeText(this,"go to next",Toast.LENGTH_SHORT).show()
        }

        btn_test2.setOnClickListener(){
            val data = CompanyData(this)
            data.getall(object : VolleyResponseListener {
                override fun onSuccess(list: MutableList<CompanyModel>) {
                    datalist = list

                    rv.apply {
                        layoutManager = LinearLayoutManager(context)
                        recyclerviewAdapter = RecyclerViewAdapter(datalist)
                        adapter = recyclerviewAdapter
                    }



                    Toast.makeText(context, list[0].toString(), Toast.LENGTH_SHORT ).show()
                }

                override fun onError(message: String) {
                    Toast.makeText(context, "Error!", Toast.LENGTH_SHORT ).show()
                }
            })
        }





    }




}
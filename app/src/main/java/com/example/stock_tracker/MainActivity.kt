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
    lateinit var datalist : MutableList<CompanyModel>
    lateinit var recyclerviewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide the titlebar.
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val rv = findViewById<RecyclerView>(R.id.rv_list)
        val data = CompanyData(this)

        updateList(data,rv)

    }

    fun updateList(data:CompanyData,rv: RecyclerView){
        data.getall(object : VolleyResponseListener {
            override fun onSuccess(list: MutableList<CompanyModel>) {
                datalist = list
                rv.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerviewAdapter = RecyclerViewAdapter(datalist)
                    adapter = recyclerviewAdapter
                }
                //Toast.makeText(context, list[0].toString(), Toast.LENGTH_SHORT ).show()
            }
            override fun onError(message: String) {
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT ).show()
            }
        })
    }


}
//val intent = Intent(this, Detail_Activity::class.java).apply {
//                val message :String = "Pega"
//                putExtra(EXTRA_MESSAGE, message)
//            }
//            startActivity(intent)
//            Toast.makeText(this,"go to next",Toast.LENGTH_SHORT).show()
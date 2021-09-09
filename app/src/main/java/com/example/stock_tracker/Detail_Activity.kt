package com.example.stock_tracker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_tracker.model.CompanyModel
import org.w3c.dom.Text
import java.util.*

class Detail_Activity : AppCompatActivity() {
    var name :String= ""
    var timer: Timer = Timer()
    val context :Context = this
    lateinit var info : CompanyModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide title bar
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)

        name = intent.getStringExtra(EXTRA_MESSAGE).toString()

        val tv_DetaiName = findViewById<TextView>(R.id.tv_Detail_name)
        tv_DetaiName.apply { text = name }

        val btn_back = findViewById<Button>(R.id.btn_back)
        btn_back.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    /**do refresh price number every 10 seconds
     * */
    fun schedule (){
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                updateInfo()
                Log.e("refresh detail","get specific up to date price")
            }
        },100,10000)
    }

    /**do get json data and put into TextView
     * */
    private fun updateInfo() {
        val dataService = CompanyDataService(this)
        dataService.getone(name, object : CompanyDataService.VolleyResponseListener {
            override fun onSuccess(list: MutableList<CompanyModel>) {
                info = list[0]
                val tv_name_detail :TextView= findViewById(R.id.tv_name_Detail)
                val tv_fullname_detail : TextView = findViewById(R.id.tv_fullname_Detail)
                val tv_current :TextView= findViewById(R.id.tv_current_Price_Detail)
                val tv_low :TextView= findViewById(R.id.tv_daily_low)
                val tv_high :TextView= findViewById(R.id.tv_daily_high)
                //assign value
                tv_name_detail.text = info.name
                tv_fullname_detail.text = info.fullname
                tv_current.text = "$" + info.price
                tv_low.text = "$" + info.low
                tv_high.text = "$" + info.high

            }
            override fun onError(message: String) {
                Toast.makeText(context, "Error!", Toast.LENGTH_SHORT ).show()
            }
        })
    }

    override fun onResume() {
        this.timer = Timer()
        schedule()
        super.onResume()
    }
    fun pauseTimer(){
        this.timer.cancel()
    }

    //on pause stop timer
    override fun onPause() {
        pauseTimer()
        finish()
        super.onPause()
    }



    //kill timer when quit
    override fun onStop() {
        finish()
        super.onStop()
    }

}
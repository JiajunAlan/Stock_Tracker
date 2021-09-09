package com.example.stock_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log

import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_tracker.CompanyDataService.VolleyResponseListener
import com.example.stock_tracker.model.CompanyModel
import java.text.FieldPosition
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var datalist : MutableList<CompanyModel>
    lateinit var recyclerviewAdapter: RecyclerViewAdapter
    var timer: Timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hide the titlebar.
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        //onresume will start the schedule refresh job
    }

    /**retrieve data from API and update the recyclerview
     * @param dataService: the CompanyDataService object
     * @param rv: the RecyclerView which need update
     * */
    fun updateList(dataService:CompanyDataService, rv: RecyclerView){
        dataService.getall(object : VolleyResponseListener {
            override fun onSuccess(list: MutableList<CompanyModel>) {
                datalist = list
                rv.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    recyclerviewAdapter = RecyclerViewAdapter(datalist)
                    recyclerviewAdapter.onItemClick = {v,pos ->
                        jumptoDetail(pos)
                    }
                    adapter = recyclerviewAdapter

                }
                //Toast.makeText(context, list[0].toString(), Toast.LENGTH_SHORT ).show()
            }
            override fun onError(message: String) {
                Toast.makeText(this@MainActivity, "Error!", Toast.LENGTH_SHORT ).show()
            }
        })
    }

    private fun jumptoDetail(pos: String) {
        val intent = Intent(this, Detail_Activity::class.java).apply {
            putExtra(EXTRA_MESSAGE, pos)
        }
        startActivity(intent)
        Toast.makeText(this,pos,Toast.LENGTH_SHORT).show()
    }

    /**stop timer
     * */
    fun pauseTimer(){
        this.timer.cancel()
    }
    /**resume timer
     * */
    fun resume(){
        val dataService:CompanyDataService = CompanyDataService(this)
        val rv: RecyclerView = findViewById(R.id.rv_list)
        this.timer = Timer()
        schedule(dataService,rv)
    }

    /**do refresh price number every 10 seconds
     * @param dataService: the CompanyDataService object
     * @param rv: the RecyclerView which need update
     * */
    fun schedule (dataService:CompanyDataService, rv: RecyclerView){
        timer.scheduleAtFixedRate(object :TimerTask(){
            override fun run() {
                updateList(dataService,rv)
                Log.e("refresh","get up to date price")
            }
        },100,10000)
    }

    //on pause stop timer
    override fun onPause() {
        pauseTimer()
        super.onPause()
    }

    //onresume the timer
    override fun onResume() {
        resume()
        super.onResume()
    }

    //kill timer when quit
    override fun onStop() {
        pauseTimer()
        super.onStop()
    }

}

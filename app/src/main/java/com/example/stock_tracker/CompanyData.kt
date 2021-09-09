package com.example.stock_tracker

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.stock_tracker.model.*
import java.util.*

class CompanyData(val context: Context) {
    private var ans :MutableList<CompanyModel> = ArrayList()
    val url = "https://71iztxw7wh.execute-api.us-east-1.amazonaws.com/interview/favorite-stocks"
    lateinit var tsla :CompanyModel
    lateinit var aapl :CompanyModel
    lateinit var crm  :CompanyModel
    lateinit var msft :CompanyModel
    lateinit var pega :CompanyModel

    interface VolleyResponseListener{
        fun onSuccess(list:MutableList<CompanyModel>)
        fun onError(message: String)
    }

    fun getall(listener: VolleyResponseListener) {

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val pegaData = response.getJSONObject("PEGA")
                pega = CompanyModel("PEGA",pegaData.getString("name"),pegaData.getString("price"), pegaData.getString("low"), pegaData.getString("high"))

                val msftData = response.getJSONObject("MSFT")
                msft = CompanyModel("MSFT",msftData.getString("name"),msftData.getString("price"), msftData.getString("low"), msftData.getString("high"))

                val crmData = response.getJSONObject("CRM")
                crm = CompanyModel("CRM",crmData.getString("name"),crmData.getString("price"), crmData.getString("low"), crmData.getString("high") )

                val aaplData = response.getJSONObject("AAPL")
                aapl = CompanyModel("AAPL",aaplData.getString("name"),aaplData.getString("price"), aaplData.getString("low"), aaplData.getString("high"))

                val tslaData = response.getJSONObject("TSLA")
                tsla = CompanyModel("TSLA",tslaData.getString("name"),tslaData.getString("price"), tslaData.getString("low"), tslaData.getString("high"))

                ans = mutableListOf(pega,msft,crm,aapl,tsla)

                //Toast.makeText(context,"Success", Toast.LENGTH_LONG).show()
                listener.onSuccess(ans)

            },
            { error ->
                // Handle error
                //Toast.makeText(context,"Network Error!", Toast.LENGTH_LONG).show()
                listener.onError("Network Error")

            }
        )
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)


    }


}
package com.example.stock_tracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stock_tracker.model.CompanyModel

class RecyclerViewAdapter(private val dataSet: MutableList<CompanyModel>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    lateinit var onItemClick:(v:View, pos:String) -> Unit
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder constructor (view: View) : RecyclerView.ViewHolder(view) {
        val tv_Name: TextView = view.findViewById(R.id.tv_name)
        val tv_FullName: TextView = view.findViewById(R.id.tv_FullName)
        val tv_Price: TextView = view.findViewById(R.id.tv_Price)
        val back:ImageView = view.findViewById(R.id.back)


        fun bind(companyModel: CompanyModel){
            tv_Name.setText(companyModel.name)
            tv_FullName.setText(companyModel.fullname)
            tv_Price.setText("$"+ companyModel.price)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_layout, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        when(viewHolder) {
            is ViewHolder ->{
                viewHolder.bind(dataSet[position])
            }
        }
        viewHolder.itemView.setOnClickListener{
            onItemClick.invoke(it,dataSet[position].name)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
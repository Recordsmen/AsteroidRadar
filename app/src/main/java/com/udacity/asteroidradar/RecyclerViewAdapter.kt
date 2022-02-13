package com.udacity.asteroidradar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter() :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var dataSet = listOf<Asteroid>()
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val tv_asteroid_date: TextView = view.findViewById(R.id.tv_asteroid_date)
        val tv_codename: TextView = view.findViewById(R.id.tv_asteroid_name)
        val iv_Hazardous: ImageView = view.findViewById(R.id.iv_potential_hazardous)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.tv_asteroid_date.text = dataSet[position].closeApproachDate
        viewHolder.tv_codename.text = dataSet[position].codename
        viewHolder.iv_Hazardous.setImageResource(
        when (dataSet[position].isPotentiallyHazardous){
            true -> R.drawable.ic_status_potentially_hazardous

            else -> R.drawable.ic_status_normal

        }
        )
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    fun setData(newList:List<Asteroid>){
        dataSet = newList
        notifyDataSetChanged()
    }

}
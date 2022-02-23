package com.udacity.asteroidradar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.RecyclerviewItemBinding

class RecyclerViewAdapter(val clickListener: AsteroidClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private var dataSet = listOf<Asteroid>()

    class ViewHolder private constructor(val binding: RecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: AsteroidClickListener, item: Asteroid) {
            binding.asteroid = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerviewItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.binding.tvAsteroidDate.text = dataSet[position].closeApproachDate
        viewHolder.binding.tvAsteroidName.text = dataSet[position].codename
        viewHolder.binding.ivPotentialHazardous.setImageResource(
        when (dataSet[position].isPotentiallyHazardous){
            true -> R.drawable.ic_status_potentially_hazardous
            else -> R.drawable.ic_status_normal
            }
        )
        viewHolder.bind(clickListener, dataSet[position])
    }

    override fun getItemCount() = dataSet.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newList:List<Asteroid>){
        dataSet = newList
        notifyDataSetChanged()
    }
}
class AsteroidClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid){
        clickListener(asteroid)
    }
}
package com.example.islamy_project.radio

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.islamy_project.api.model.Radio

import com.example.islamy_project.databinding.ItemRadioBinding

class RadioAdapter : RecyclerView.Adapter<RadioAdapter.ViewHolder>() {
    var channels = listOf<Radio>()
    lateinit var viewBinding: ItemRadioBinding
    var onItemClickPlay: OnItemClickListener? = null
    var onItemClickStop: OnItemClickListener? = null

    class ViewHolder(var viewBinding: ItemRadioBinding) : RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        viewBinding = ItemRadioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = channels?.size ?: 0
    fun changeDate(data: List<Radio>) {
        this.channels = data
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var items = channels?.get(position)
        holder.viewBinding.radioNameTv.text = items?.name
        onItemClickPlay.let {
            holder.viewBinding.icPlay.setOnClickListener {
                onItemClickPlay?.onItemClick(position, channels.get(position))
            }
        }
        onItemClickStop.let {
            holder.viewBinding.icStop.setOnClickListener {
                onItemClickStop?.onItemClick(position, channels.get(position))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: Radio)
    }
}
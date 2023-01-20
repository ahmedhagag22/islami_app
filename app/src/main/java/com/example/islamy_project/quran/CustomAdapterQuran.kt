package com.example.islamy_project.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.islamy_project.R

class CustomAdapterQuran(private var suras: List<String>, private var count: List<Int>) :
    RecyclerView.Adapter<CustomAdapterQuran.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.txt_view_item_quran_name)
        val numberSura: TextView = itemView.findViewById(R.id.txt_view_item_quran_num)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_recylerview_name_sura, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val itemViewModel = suras[position]
        holder.title.text = suras.get(position)
        holder.numberSura.text = count.get(position).toString()
//        holder.title.setText(itemViewModel)
//        holder.numberSura.text="$count"

        if (OnSuraClickListner != null) {
            holder.title.setOnClickListener {
                OnSuraClickListner?.OnItemClick(position, suras.get(position))
            }
        }

    }


    override fun getItemCount(): Int {
        return suras.size
    }

    var OnSuraClickListner: OnItemClickListner? = null

    interface OnItemClickListner {
        fun OnItemClick(pos: Int, SuraName: String)

    }

}



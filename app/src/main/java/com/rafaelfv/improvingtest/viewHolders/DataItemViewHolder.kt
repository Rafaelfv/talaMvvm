package com.rafaelfv.improvingtest.viewHolders

import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.improvingtest.R

class DataItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val userName = itemView.findViewById<TextView>(R.id.userName)
    val date = itemView.findViewById<TextView>(R.id.date)
    val container = itemView.findViewById<RelativeLayout>(R.id.container_item_data)

}
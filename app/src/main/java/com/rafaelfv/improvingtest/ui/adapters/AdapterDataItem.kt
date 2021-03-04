package com.rafaelfv.improvingtest.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafaelfv.improvingtest.R
import com.rafaelfv.improvingtest.service.model.DataItem
import com.rafaelfv.improvingtest.utils.getColorBackGround
import com.rafaelfv.improvingtest.utils.toDate
import com.rafaelfv.improvingtest.viewHolders.DataItemViewHolder

class AdapterDataItem(var list: ArrayList<DataItem>, val listener: AdapterDataItemEvent) :
    RecyclerView.Adapter<DataItemViewHolder>() {

    private lateinit var context: Context

    interface AdapterDataItemEvent {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        context = parent.context
        return DataItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataItemViewHolder, position: Int) {
        holder.userName.text = list[position].username
        holder.date.text = list[position].timestamp.toDate()
        holder.container.setBackgroundColor(list[position].getColorBackGround(context))

        holder.container.setOnClickListener { listener.onItemClick(position) }
    }

    override fun getItemCount() = list.size


}
package com.example.w3d4_coroutinesretrofitexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w3d4_coroutinesretrofitexample.network.DataModel
import kotlinx.android.synthetic.main.list_item_home.view.*

class DataAdapter(var dataList: MutableList<DataModel>, private val clickListener: (DataModel) -> Unit) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_home, parent, false))
    }

    override fun getItemCount(): Int {
       return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener)
    }

    fun updateWords(list: MutableList<DataModel>) {
        dataList.clear()
        dataList = list
        // to refresh the ui
        notifyDataSetChanged()
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        fun bind(dataModel: DataModel, clickListener: (DataModel) -> Unit) {
            itemView.title.text = dataModel.title
            itemView.setOnClickListener { clickListener(dataModel) }
        }
    }

}
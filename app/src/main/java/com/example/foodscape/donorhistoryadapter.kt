package com.example.foodscape

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class donorhistoryadapter(private val userList : ArrayList<user>) : RecyclerView.Adapter<donorhistoryadapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val fooditem = itemView.findViewById<TextView>(R.id.dishname)
        val foodcount = itemView.findViewById<TextView>(R.id.foodserves)
        val donoraddres = itemView.findViewById<TextView>(R.id.foodlocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.donor_history_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        val size = userList.size
        return size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fooditem.text = userList[position].fooditem
        holder.foodcount.text = userList[position].foodquantity
        holder.donoraddres.text = userList[position].donoraddress
    }
}
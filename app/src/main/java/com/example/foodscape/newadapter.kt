package com.example.foodscape

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class newadapter (private val userList : ArrayList<newdataclass>, private val context: android.content.Context) : RecyclerView.Adapter<newadapter.MyViewHolder>(){

    private var items = mutableListOf<newadapter>()
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val fooditem = itemView.findViewById<TextView>(R.id.dishname)
        val foodcount = itemView.findViewById<TextView>(R.id.foodserves)
        val donoraddres = itemView.findViewById<TextView>(R.id.foodlocation)
        val recCard = itemView.findViewById<CardView>(R.id.recCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ngo_new_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fooditem.text = userList[position].fooditem
        holder.foodcount.text = userList[position].foodquantity
        holder.donoraddres.text = userList[position].donoraddress

        holder.recCard.setOnClickListener {
            val intent = Intent(context, newdetailedactivity::class.java)
            intent.putExtra("fooditem", userList[holder.adapterPosition].fooditem)
            intent.putExtra("foodcount", userList[holder.adapterPosition].foodquantity)
            intent.putExtra("donoraddress", userList[holder.adapterPosition].donoraddress)
            context.startActivity(intent)
        }
    }
}
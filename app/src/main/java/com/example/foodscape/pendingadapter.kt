package com.example.foodscape

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class pendingadapter (private val userList : ArrayList<pendingdataclass>, private val context: android.content.Context) : RecyclerView.Adapter<pendingadapter.MyViewHolder>(){
    private var items = mutableListOf<pendingadapter>()
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val fooditem = itemView.findViewById<TextView>(R.id.dishname)
        val foodcount = itemView.findViewById<TextView>(R.id.foodserves)
        val donoraddres = itemView.findViewById<TextView>(R.id.foodlocation)
        val recCard = itemView.findViewById<CardView>(R.id.recCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ngo_pending_item, parent, false)
        return MyViewHolder(itemView)
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

    override fun getItemCount(): Int {
        return userList.size
    }
}
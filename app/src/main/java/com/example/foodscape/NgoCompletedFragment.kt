package com.example.foodscape

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class NgoCompletedFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<newdataclass>
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ngo_completed, container, false)

        recyclerView = view.findViewById(R.id.completeddatarecyclerview)
        context?.let {
            recyclerView.layoutManager = LinearLayoutManager(it)
        }

        val new_count = view.findViewById<TextView>(R.id.new_count)
        val pending_count = view.findViewById<TextView>(R.id.pending_count)
        val completed_count = view.findViewById<TextView>(R.id.completed_count)

        userList = arrayListOf()

        database = FirebaseFirestore.getInstance()
        database.collection("confirmeddonation").get().addOnSuccessListener {
            if(!it.isEmpty){
                for (data in it.documents){
                    val user : newdataclass? = data.toObject(newdataclass::class.java)
                    if (user != null){
                        userList.add(user)
                    }
                }
                recyclerView.adapter = context?.let { it1 -> newadapter(userList, it1) }
            }
        }.addOnFailureListener {
            context?.let {
                Toast.makeText(it, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        database.collection("donation").get().addOnSuccessListener { querySnapshot ->
            new_count.text = querySnapshot.size().toString()
        }.addOnFailureListener { e ->
            context?.let {
                Toast.makeText(it, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        database.collection("pendingdonation").get().addOnSuccessListener { querySnapshot ->
            pending_count.text = querySnapshot.size().toString()
        }.addOnFailureListener { e ->
            context?.let {
                Toast.makeText(it, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        database.collection("confirmeddonation").get().addOnSuccessListener { querySnapshot ->
            completed_count.text = querySnapshot.size().toString()
        }.addOnFailureListener { e ->
            context?.let {
                Toast.makeText(it, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
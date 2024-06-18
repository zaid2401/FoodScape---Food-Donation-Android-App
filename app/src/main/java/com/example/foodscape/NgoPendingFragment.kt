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

class NgoPendingFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<newdataclass>
    private lateinit var database: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ngo_pending, container, false)

        recyclerView = view.findViewById(R.id.pendingdatarecyclerview)
        context?.let {
            recyclerView.layoutManager = LinearLayoutManager(it)
        }

        val complete = view.findViewById<Button>(R.id.completebtn)
        val new_count = view.findViewById<TextView>(R.id.new_count)
        val pending_count = view.findViewById<TextView>(R.id.pending_count)
        val completed_count = view.findViewById<TextView>(R.id.completed_count)

        userList = arrayListOf()

        database = FirebaseFirestore.getInstance()
        database.collection("pendingdonation").get().addOnSuccessListener {
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

        complete.setOnClickListener {

            database.collection("pendingdonation").get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val data = document.data
                    database.collection("confirmeddonation").add(data).addOnSuccessListener { documentReference ->
                        context?.let {
                            Toast.makeText(it, "Succesful", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener { e ->
                        context?.let {
                            Toast.makeText(it, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                    document.reference.delete().addOnSuccessListener {
                        context?.let {
                            Toast.makeText(it, "Deleted Succesful", Toast.LENGTH_SHORT).show()
                        }
                    }.addOnFailureListener { e ->
                        context?.let {
                            Toast.makeText(it, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }.addOnFailureListener { e ->
                context?.let {
                    Toast.makeText(it, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }
}
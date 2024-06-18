package com.example.foodscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore

class donorhistory : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userList: ArrayList<user>
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donorhistory)

        recyclerView = findViewById(R.id.historyrecyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        userList = arrayListOf()

        database = FirebaseFirestore.getInstance()
        database.collection("donation").get().addOnSuccessListener {
            if (!it.isEmpty){
                for (data in it.documents){
                    val user : user? = data.toObject(user::class.java)
                    if (user != null){
                        userList.add(user)
                    }
                }
                recyclerView.adapter = donorhistoryadapter(userList)
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class donorprofile : AppCompatActivity() {

    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donorprofile)

        database = FirebaseFirestore.getInstance()

        val pname = findViewById<TextView>(R.id.profileName)
        val pemail = findViewById<TextView>(R.id.profileEmail)
        val pphone = findViewById<TextView>(R.id.profilePhone)
        val paddress = findViewById<TextView>(R.id.profileAddress)
        val tname = findViewById<TextView>(R.id.titleName)
        val temail = findViewById<TextView>(R.id.titleUsername)
        val editprofile = findViewById<Button>(R.id.editButton)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = database.collection("user").document(userId)
        ref.get().addOnSuccessListener {
            if (it != null){
                val name = it.data?.get("username").toString()
                val email = it.data?.get("email").toString()
                val phone = it.data?.get("phone").toString()
                val address = it.data?.get("address").toString()

                pname.text = name
                pemail.text = email
                pphone.text = phone
                paddress.text = address
                tname.text = name
                temail.text = email
            }
        }

        editprofile.setOnClickListener {
            val intent = Intent(this, donoreditprofile::class.java)
            startActivity(intent)
            finish()
        }
    }
}
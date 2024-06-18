package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class donorhomepage : AppCompatActivity() {

    private lateinit var authenticator : FirebaseAuth
    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donorhomepage)

        authenticator = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        val puser = findViewById<TextView>(R.id.user)
        val logout = findViewById<CardView>(R.id.logout)
        val contactus = findViewById<CardView>(R.id.contactus)
        val profile = findViewById<CardView>(R.id.profile)
        val feedback = findViewById<CardView>(R.id.feedback)
        val donatepage = findViewById<CardView>(R.id.donate)
        val history = findViewById<CardView>(R.id.history)

        logout.setOnClickListener {
            authenticator.signOut()
            val intent = Intent(this, Secondpage::class.java)
            startActivity(intent)
            finish()
        }

        contactus.setOnClickListener {
            val intent = Intent(this, ContactUs::class.java)
            startActivity(intent)
        }

        feedback.setOnClickListener {
            val intent = Intent(this, Feedback::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            val intent = Intent(this, donorprofile::class.java)
            startActivity(intent)
        }

        donatepage.setOnClickListener {
            val intent = Intent(this, donate::class.java)
            startActivity(intent)
        }

        history.setOnClickListener {
            val intent = Intent(this, donorhistory::class.java)
            startActivity(intent)
        }

        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = database.collection("user").document(userId)
        ref.get().addOnSuccessListener {
            if (it != null){
                val username = it.data?.get("username").toString()


                puser.text = "Hello, " + username
            }
        }
    }
}
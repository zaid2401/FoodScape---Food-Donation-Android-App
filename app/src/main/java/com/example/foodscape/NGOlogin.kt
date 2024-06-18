package com.example.foodscape

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class NGOlogin : AppCompatActivity() {

    private val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngo_log_in)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("ngo-user")

        val nsigninemail = findViewById<EditText>(R.id.ngosignin_email)
        val nsigninpassword = findViewById<EditText>(R.id.ngosignin_password)
        val nsigninbtn = findViewById<Button>(R.id.ngosignin_button)

        val email = "foodscape18@gmail.com"
        val password = "foodscape1234"

        nsigninbtn.setOnClickListener {
            val siemail = nsigninemail.text.toString()
            val sipassword = nsigninpassword.text.toString()
            if(siemail.isEmpty() || sipassword.isEmpty()){
                if(siemail.isEmpty()){
                    nsigninemail.error = "Enter email address"
                }
                if(sipassword.isEmpty()){
                    nsigninpassword.error = "Enter password"
                }
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }
            else if(!siemail.matches(emailpattern.toRegex())){
                nsigninemail.error = "Enter valid email address"
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show()
            }
            else if(sipassword.length < 8){
                nsigninpassword.error = "Enter password more than 8 characters"
                Toast.makeText(this, "Enter password more than 8 characters", Toast.LENGTH_SHORT).show()
            }
            else{
                if(siemail == email && sipassword == password){
                    val intent = Intent(this, NGOhomepage::class.java)
                    startActivity(intent)
                    finish()
                }
                }
            }
        }
    }
package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class donoreditprofile : AppCompatActivity() {

    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donoreditprofile)

        database = FirebaseFirestore.getInstance()

        val eemail = findViewById<EditText>(R.id.editEmail)
        val ename = findViewById<EditText>(R.id.editName)
        val ephone = findViewById<EditText>(R.id.editPhone)
        val eaddress = findViewById<EditText>(R.id.editAddress)
        val savebtn = findViewById<Button>(R.id.saveButton)

        savebtn.setOnClickListener {
            val Name = ename.text.toString().trim()
            val Email = eemail.text.toString().trim()
            val Phone = ephone.text.toString().trim()
            val Address = eaddress.text.toString().trim()

            val updateMap = mapOf(
                "username" to Name,
                "email" to Email,
                "phone" to Phone,
                "address" to Address
            )

            val userId = FirebaseAuth.getInstance().currentUser!!.uid
            database.collection("user").document(userId).update(updateMap).addOnSuccessListener {
                Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, donorprofile::class.java)
                startActivity(intent)
                finish()
            }

        }
    }
}
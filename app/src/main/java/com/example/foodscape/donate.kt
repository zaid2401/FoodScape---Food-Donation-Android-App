package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class donate : AppCompatActivity() {

    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        database = FirebaseFirestore.getInstance()

        val ddonorname = findViewById<EditText>(R.id.donorname)
        val dfooditem = findViewById<EditText>(R.id.fooditem)
        val dfoodquantity = findViewById<EditText>(R.id.foodquantity)
        val ddonoraddress = findViewById<EditText>(R.id.donoraddress)
        val ddonatebtn = findViewById<Button>(R.id.donate_button)

        ddonatebtn.setOnClickListener {

            val donorname = ddonorname.text.toString().trim()
            val fooditem = dfooditem.text.toString().trim()
            val foodquantity = dfoodquantity.text.toString().trim()
            val donoraddress = ddonoraddress.text.toString().trim()

            val usermap = hashMapOf(
                "donorname" to donorname,
                "fooditem" to fooditem,
                "foodquantity" to foodquantity,
                "donoraddress" to donoraddress
            )



            database.collection("donation").document().set(usermap).addOnSuccessListener {
                Toast.makeText(this, "Succesful", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, donorhomepage::class.java)
            startActivity(intent)
            finish()
        }
    }
}
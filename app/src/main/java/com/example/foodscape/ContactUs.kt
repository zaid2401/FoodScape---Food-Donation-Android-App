package com.example.foodscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class ContactUs : AppCompatActivity() {

    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)

        database = FirebaseFirestore.getInstance()

        val pphone = findViewById<TextView>(R.id.phoneno)
        val pemail = findViewById<TextView>(R.id.email)
        val paddress = findViewById<TextView>(R.id.address)

        val ref = database.collection("ngo-user").document("ngouser")
        ref.get().addOnSuccessListener {
            if (it != null){
                val email = it.data?.get("emaill").toString()
                val phone = it.data?.get("phone").toString()
                val address = it.data?.get("address").toString()

                pemail.text = email
                pphone.text = phone
                paddress.text = address
            }
        }

    }
}
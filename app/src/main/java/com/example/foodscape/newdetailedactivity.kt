package com.example.foodscape

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class newdetailedactivity : AppCompatActivity() {

    private lateinit var database : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newdetailedactivity)

        database = FirebaseFirestore.getInstance()

        val itemname = findViewById<TextView>(R.id.detailitemname)
        val itemcount = findViewById<TextView>(R.id.detailitemcount)
        val adddress = findViewById<TextView>(R.id.detailaddress)

        val bundle = intent.extras
        if (bundle != null){
            itemname.text = bundle.getString("fooditem")
            itemcount.text = bundle.getString("foodcount")
            adddress.text = bundle.getString("donoraddress")
        }
    }
}
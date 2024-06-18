package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Secondpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_page)

        val signinngo = findViewById<Button>(R.id.ngo)
        signinngo.setOnClickListener {
            intent = Intent(this, NGOlogin::class.java)
            startActivity(intent)
        }

        val signindonor = findViewById<Button>(R.id.donor)
        signindonor.setOnClickListener{
            intent = Intent(this, donorlogin::class.java)
            startActivity(intent)
        }
    }
}
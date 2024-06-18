package com.example.foodscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

class Feedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)

        val rating = findViewById<RatingBar>(R.id.fbstars)
        val submit = findViewById<Button>(R.id.sendfeedback_button)
        val ratingtxt = findViewById<TextView>(R.id.feedback)

        rating.setOnRatingBarChangeListener { ratingBar, fl, b ->
            ratingtxt.text = fl.toString()
            ratingtxt.visibility = View.VISIBLE
            when (ratingBar.rating.toInt()){
                1 -> ratingtxt.text = "Very Bad"
                2 -> ratingtxt.text = "Bad"
                3 -> ratingtxt.text = "Good"
                4 -> ratingtxt.text = "Great"
                5 -> ratingtxt.text = "Awesome"
                else -> ratingtxt.text = " "
            }
        }

        submit.setOnClickListener {
            Toast.makeText(this, "Feedback Send Succesful", Toast.LENGTH_SHORT).show()
        }
    }
}
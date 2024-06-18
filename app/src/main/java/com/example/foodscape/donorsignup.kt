package com.example.foodscape

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class donorsignup : AppCompatActivity() {

    private lateinit var authenticator : FirebaseAuth
    private lateinit var database : FirebaseFirestore
    private val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_sign_up)

        authenticator = FirebaseAuth.getInstance()
        database = FirebaseFirestore.getInstance()

        val dsignupemail = findViewById<EditText>(R.id.donorsignup_email)
        val dsignupphone = findViewById<EditText>(R.id.donorsignup_phone)
        val dsignuppassword = findViewById<EditText>(R.id.donorsignup_password)
        val dsignupcpassword = findViewById<EditText>(R.id.donorsignup_cpassword)
        val dsignupusername = findViewById<EditText>(R.id.donorsignup_username)
        val dsignupbtn = findViewById<Button>(R.id.donorsignup_button)

        val signintext = findViewById<TextView>(R.id.donorloginRedirectText)
        signintext.setOnClickListener {
            val intent = Intent(this, donorlogin::class.java)
            startActivity(intent)
            finish()
        }

        dsignupbtn.setOnClickListener{
            val suemail = dsignupemail.text.toString().trim()
            val suphone = dsignupphone.text.toString().trim()
            val supassword = dsignuppassword.text.toString().trim()
            val sucpassword = dsignupcpassword.text.toString().trim()
            val susername = dsignupusername.text.toString().trim()

            if(suemail.isEmpty() || suphone.isEmpty() || supassword.isEmpty() || sucpassword.isEmpty()){
                if(suemail.isEmpty()){
                    dsignupemail.error = "Enter email address"
                }
                if(susername.isEmpty()){
                    dsignupemail.error = "Enter Username"
                }
                if(suphone.isEmpty()){
                    dsignupphone.error = "Enter phone number"
                }
                if(supassword.isEmpty()){
                    dsignuppassword.error = "Enter password"
                }
                if(sucpassword.isEmpty()){
                    dsignupcpassword.error = "Re enter password"
                }
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
            }
            else if(!suemail.matches(emailpattern.toRegex())){
                dsignupemail.error = "Enter valid email address"
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show()
            }
            else if(suphone.length != 10){
                dsignupphone.error = "Enter valid phone number"
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show()
            }
            else if(supassword.length < 8){
                dsignuppassword.error = "Enter password more than 8 characters"
                Toast.makeText(this, "Enter password more than 8 characters", Toast.LENGTH_SHORT).show()
            }
            else if(supassword != sucpassword){
                dsignupcpassword.error = "Password not matched"
                Toast.makeText(this, "Password not matched", Toast.LENGTH_SHORT).show()
            }
            else{
                authenticator.createUserWithEmailAndPassword(suemail, supassword).addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        val usermap = hashMapOf(
                            "email" to suemail,
                            "phone" to suphone,
                            "password" to supassword,
                            "username" to susername
                        )
                        val userId = FirebaseAuth.getInstance().currentUser!!.uid

                        database.collection("user").document(userId).set(usermap).addOnSuccessListener {
                            Toast.makeText(this, "Signup Succesful", Toast.LENGTH_SHORT).show()
                        }.addOnFailureListener {
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }
                        val intent = Intent(this, donorlogin::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
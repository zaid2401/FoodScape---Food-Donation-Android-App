package com.example.foodscape

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class donorlogin : AppCompatActivity() {

    private lateinit var authenticator : FirebaseAuth
    private val emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donor_log_in)

        authenticator = FirebaseAuth.getInstance()

        val dsigninemail = findViewById<EditText>(R.id.donorsignin_email)
        val dsigninpassword = findViewById<EditText>(R.id.donorsignin_password)
        val dsigninbtn = findViewById<Button>(R.id.donorsignin_button)
        val forgotpw = findViewById<TextView>(R.id.forgotpassword)
        val progressbar = findViewById<ProgressBar>(R.id.donorloginprogressbar)

        val signuptext = findViewById<TextView>(R.id.donorloginRedirectText)
        signuptext.setOnClickListener {
            val intent = Intent(this, donorsignup::class.java)
            startActivity(intent)
            finish()
        }

        dsigninbtn.setOnClickListener {
            val siemail = dsigninemail.text.toString()
            val sipassword = dsigninpassword.text.toString()
            progressbar.visibility = View.VISIBLE
            if(siemail.isEmpty() || sipassword.isEmpty()){
                if(siemail.isEmpty()){
                    dsigninemail.error = "Enter email address"
                }
                if(sipassword.isEmpty()){
                    dsigninpassword.error = "Enter password"
                }
                Toast.makeText(this, "Enter valid details", Toast.LENGTH_SHORT).show()
                progressbar.visibility = View.GONE
            }
            else if(!siemail.matches(emailpattern.toRegex())){
                dsigninemail.error = "Enter valid email address"
                progressbar.visibility = View.GONE
                Toast.makeText(this, "Enter valid email address", Toast.LENGTH_SHORT).show()
            }
            else if(sipassword.length < 8){
                dsigninpassword.error = "Enter password more than 8 characters"
                progressbar.visibility = View.GONE
                Toast.makeText(this, "Enter password more than 8 characters", Toast.LENGTH_SHORT).show()
            }
            else{
                authenticator.signInWithEmailAndPassword(siemail, sipassword).addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, donorhomepage::class.java)
                        startActivity(intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        forgotpw.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.forgot_password, null)
            val userEmail = view.findViewById<EditText>(R.id.frgtemail)
            builder.setView(view)
            val dialog = builder.create()

            view.findViewById<Button>(R.id.btnReset).setOnClickListener {
                compareEmail(userEmail)
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.btnCancel).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }
    }

    private fun compareEmail(email: EditText?) {
        if (email?.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email?.text.toString()).matches()){
            return
        }
        authenticator.sendPasswordResetEmail(email?.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
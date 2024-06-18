package com.example.foodscape

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NgoProfileFragment : Fragment() {

    private lateinit var database : FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_ngo_profile, container, false)

        database = FirebaseFirestore.getInstance()

        val pname = view.findViewById<TextView>(R.id.profileName)
        val pemail = view.findViewById<TextView>(R.id.profileEmail)
        val pphone = view.findViewById<TextView>(R.id.profilePhone)
        val paddress = view.findViewById<TextView>(R.id.profileAddress)
        val tname = view.findViewById<TextView>(R.id.titleName)
        val temail = view.findViewById<TextView>(R.id.titleUsername)
        val editprofile = view.findViewById<Button>(R.id.editButton)
        val logout = view.findViewById<Button>(R.id.logoutButton)

        logout.setOnClickListener {
            activity?.let {
                val intent = Intent(it, Secondpage::class.java)
                startActivity(intent)
            }
        }

        val ref = database.collection("ngo-user").document("ngouser")
        ref.get().addOnSuccessListener {
            if (it != null){
                val name = it.data?.get("NGOName").toString()
                val email = it.data?.get("emaill").toString()
                val phone = it.data?.get("phone").toString()
                val address = it.data?.get("address").toString()

                pname.text = name
                pemail.text = email
                pphone.text = phone
                paddress.text = address
                tname.text = name
                temail.text = email
            }
        }

        editprofile.setOnClickListener {
            activity?.let {
                val intent = Intent(it, NGOeditprofile::class.java)
                startActivity(intent)
            }
        }

        return view
    }
}
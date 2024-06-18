package com.example.foodscape

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NGOhomepage : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ngohomepage)

        bottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.bottom_home -> {
                    replaceFragment(NgoNewFragment())
                    true
                }
                R.id.bottom_pending -> {
                    replaceFragment(NgoPendingFragment())
                    true
                }
                R.id.bottom_completed -> {
                    replaceFragment(NgoCompletedFragment())
                    true
                }
                R.id.bottom_profile -> {
                    replaceFragment(NgoProfileFragment())
                    true
                }
                else -> false
            }
        }
        replaceFragment(NgoNewFragment())

        supportFragmentManager.beginTransaction().replace(R.id.frame_container, NgoNewFragment()).commit()
    }
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit()
    }
}
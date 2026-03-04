package com.example.onlinevotingsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Default screen
        loadFragment(ProfileFragment())

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> loadFragment(ProfileFragment())
                R.id.nav_vote -> loadFragment(VoteFragment())
                R.id.nav_history -> loadFragment(HistoryFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

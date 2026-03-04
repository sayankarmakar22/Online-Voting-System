package com.example.onlinevotingsystem

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 1️⃣ Get reference of TextView
        val tvTitle = findViewById<TextView>(R.id.tvTitle)

        // 2️⃣ Apply fade-in animation
        tvTitle.alpha = 0f
        tvTitle.animate()
            .alpha(1f)
            .setDuration(1500)
            .start()

        // 3️⃣ Delay and move to Login screen
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 2000)
    }
}
package com.example.onlinevotingsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val epic = findViewById<EditText>(R.id.etEpic)
        val dob = findViewById<EditText>(R.id.etDob)
        val phone = findViewById<EditText>(R.id.etPhone)
        val loginBtn = findViewById<Button>(R.id.btnLogin)

        loginBtn.setOnClickListener {

            val epicText = epic.text.toString().trim()
            val dobText = dob.text.toString().trim()
            val phoneText = phone.text.toString().trim()

            if (epicText.isEmpty() || dobText.isEmpty() || phoneText.length != 10) {
                Toast.makeText(this, "Enter all valid details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, OtpActivity::class.java)
            intent.putExtra("phoneNumber", phoneText)
            startActivity(intent)
        }
    }
}

package com.example.onlinevotingsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class OtpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var verificationId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        auth = FirebaseAuth.getInstance()

        val phoneNumber = intent.getStringExtra("phoneNumber") ?: ""

        val otpInput = findViewById<EditText>(R.id.etOtp)
        val verifyBtn = findViewById<Button>(R.id.btnSubmitOtp)

        if(phoneNumber.isNotEmpty()){
            sendOtp(phoneNumber)
        }

        verifyBtn.setOnClickListener {
            val otp = otpInput.text.toString()
            if(otp.isNotEmpty()){
                verifyOtp(otp)
            }
        }
    }

    private fun sendOtp(phoneNumber: String) {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+91$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    signInWithCredential(credential)
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@OtpActivity,
                        "Verification Failed: ${e.message}",
                        Toast.LENGTH_LONG).show()
                }

                override fun onCodeSent(
                    verificationIdParam: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    verificationId = verificationIdParam
                    Toast.makeText(this@OtpActivity,
                        "OTP Sent",
                        Toast.LENGTH_SHORT).show()
                }
            })
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyOtp(code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithCredential(credential)
    }

    private fun signInWithCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this,
                        "Invalid OTP",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}

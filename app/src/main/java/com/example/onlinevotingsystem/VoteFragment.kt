package com.example.onlinevotingsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class VoteFragment : Fragment() {

    private var faceVerified = false
    private var fingerprintVerified = false
    private var simVerified = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_vote, container, false)

        val btnFace = view.findViewById<Button>(R.id.btnFace)
        val btnFingerprint = view.findViewById<Button>(R.id.btnFingerprint)
        val btnSim = view.findViewById<Button>(R.id.btnSim)

        val tvFaceStatus = view.findViewById<TextView>(R.id.tvFaceStatus)
        val tvFingerprintStatus = view.findViewById<TextView>(R.id.tvFingerprintStatus)
        val tvSimStatus = view.findViewById<TextView>(R.id.tvSimStatus)

        // FACE VERIFICATION
        btnFace.setOnClickListener {
            faceVerified = true
            tvFaceStatus.text = "✅ Face Verified"
            checkAllVerified()
        }

        // FINGERPRINT VERIFICATION
        btnFingerprint.setOnClickListener {
            showBiometricPrompt {
                fingerprintVerified = true
                tvFingerprintStatus.text = "✅ Fingerprint Verified"
                checkAllVerified()
            }
        }

        // SIM VERIFICATION
        btnSim.setOnClickListener {
            simVerified = true
            tvSimStatus.text = "✅ SIM Bound Successfully"
            checkAllVerified()
        }

        return view
    }

    private fun checkAllVerified() {
        if (faceVerified && fingerprintVerified && simVerified) {

            Toast.makeText(
                requireContext(),
                "All Verifications Completed",
                Toast.LENGTH_SHORT
            ).show()

            // Navigate to Candidate Fragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CandidateFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun showBiometricPrompt(onSuccess: () -> Unit) {

        val executor = ContextCompat.getMainExecutor(requireContext())

        val biometricPrompt = BiometricPrompt(
            this,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        requireContext(),
                        "Fingerprint Authentication Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Verification")
            .setSubtitle("Use registered fingerprint")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}

package com.example.onlinevotingsystem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_history, container, false)

        val statusText = view.findViewById<TextView>(R.id.tvStatus)

        val prefs = requireActivity()
            .getSharedPreferences("VotingApp", Context.MODE_PRIVATE)

        val hasVoted = prefs.getBoolean("hasVoted", false)

        if (hasVoted) {
            val candidate = prefs.getString("votedCandidate", "")
            statusText.text = "✅ You voted for:\n\n$candidate"
        } else {
            statusText.text = "❌ You have not voted yet."
        }

        return view
    }
}

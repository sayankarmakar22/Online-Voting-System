package com.example.onlinevotingsystem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class CandidateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_candidate, container, false)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)
        val submitVote = view.findViewById<Button>(R.id.btnSubmitVote)

        submitVote.setOnClickListener {

            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId != -1) {

                val selectedRadio = view.findViewById<RadioButton>(selectedId)
                val selectedCandidate = selectedRadio.text.toString()

                val prefs = requireActivity()
                    .getSharedPreferences("VotingApp", Context.MODE_PRIVATE)

                prefs.edit()
                    .putBoolean("hasVoted", true)
                    .putString("votedCandidate", selectedCandidate)
                    .apply()

                Toast.makeText(requireContext(),
                    "Voted Successfully",
                    Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(requireContext(),
                    "Select a candidate",
                    Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}

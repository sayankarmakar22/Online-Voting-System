package com.example.onlinevotingsystem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val prefs = requireActivity().getSharedPreferences("VotingApp", Context.MODE_PRIVATE)

        val epic = prefs.getString("epic", "Not Available")
        val dob = prefs.getString("dob", "Not Available")
        val phone = prefs.getString("phone", "9804121212")



        view.findViewById<TextView>(R.id.tvEpic).text = "EPIC No: $epic"
        view.findViewById<TextView>(R.id.tvVoterId).text = "Voter No: VTR123456"
        view.findViewById<TextView>(R.id.tvName).text = "Name: Raka Ghosh"
        view.findViewById<TextView>(R.id.tvDob).text = "DOB: $dob"
        view.findViewById<TextView>(R.id.tvPhone).text = "Phone: $phone"
        view.findViewById<TextView>(R.id.tvAddress).text = "Address: Kolkata, India"

        return view
    }
}

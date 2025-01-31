package com.example.homerentalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homerentalapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Setup search view
//        setupSearchView()

        // Set click listeners
        binding.hosImg.setOnClickListener {
            startActivity(Intent(requireActivity(), user_hospital::class.java))
        }
        binding.docImage.setOnClickListener {
            startActivity(Intent(requireActivity(), DoctorSpeciliatiyActivity::class.java))
        }
        binding.pharmacyImage.setOnClickListener {
            startActivity(Intent(requireActivity(), pharmacy::class.java))
        }
        binding.ambulance.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:1122")
            }
            startActivity(intent)
        }

        binding.vendorImage.setOnClickListener {
            startActivity(Intent(requireContext(), NurseActivity::class.java))
        }

        return binding.root
    }

//    private fun setupSearchView() {
//        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // Handle search query submission
//                performSearch(query)
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // Handle search query change
//                performSearch(newText)
//                return true
//            }
//        })
//    }

    private fun performSearch(query: String?) {
        // Perform search operation based on the query
        if (!query.isNullOrBlank()) {
            // Example: Display search query in a toast
            Toast.makeText(requireContext(), "Search query: $query", Toast.LENGTH_SHORT).show()
        } else {
            // Clear existing search results
            // Example: Clear search results
            // clearSearchResults()
        }
    }
}

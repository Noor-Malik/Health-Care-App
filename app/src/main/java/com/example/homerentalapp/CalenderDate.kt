package com.example.homerentalapp

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.appcontant.AppConstant
import com.example.homerentalapp.databinding.ActivityCalenderDateBinding
import com.google.firebase.firestore.FirebaseFirestore

class CalenderDate : AppCompatActivity() {
    lateinit var binding: ActivityCalenderDateBinding
    private var currentPosition: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalenderDateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city = arrayOf(
            "4:00 PM",
            "5:00 PM",
            "9:00 PM",
            "10:00 PM",
        )

        val arrayAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, city)
        binding.time.adapter = arrayAdapter
        binding.time.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {
                        currentPosition = "4:00 PM"
                    }

                    1 -> {
                        currentPosition = "5:00 PM"
                    }

                    2 -> {
                        currentPosition = "9:00 PM"
                    }

                    3 -> {
                        currentPosition = "10:00 PM "
                    }

                    4 -> {
                        currentPosition = "10:00 PM"
                    }

                    else -> {
                        currentPosition = "4:00 PM"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Not implemented
            }
        }

        binding.calender.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val formattedDate = "$dayOfMonth/${month + 1}/$year"
            AppConstant.date = formattedDate
            Toast.makeText(this, "Selected Date: ${AppConstant.date}", Toast.LENGTH_SHORT).show()
        }

        binding.cardBtnC.setOnClickListener {

            if (AppConstant.date != null) {
                FirebaseFirestore.getInstance().collection("Appointment")
                    .document(AppConstant.id!!).update("date", AppConstant.date, "time", currentPosition)
                    .addOnCompleteListener {
                        Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {

                    }
            }
        }
    }
}
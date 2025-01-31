package com.example.homerentalapp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.Dataclass.DoctorDetailsModel
import com.example.homerentalapp.databinding.ActivityProfileDocBinding
import com.google.firebase.firestore.FirebaseFirestore

class profileDoc : AppCompatActivity() {

    lateinit var binding: ActivityProfileDocBinding
    private lateinit var fireStore: FirebaseFirestore
    private var currentPosition: String? = null
    private var currentPosition1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDocBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.subscribe?.setOnClickListener {
            showCustomAlertDialog()
        }
        fireStore = FirebaseFirestore.getInstance()
        val city = arrayOf(
            "Psychiatrist",
            "Nutritionist",
            "Plastic Surgeon",
            "Nephrologist",
            "Dentist",
            "Dermatologist",
            "Hand Surgeon"
        )
        val fees = arrayOf(
            "1000",
            "1500",
            "2000",
            "3000"
        )

        val feesAdapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,fees)
        binding.spinnerPkr?.adapter = feesAdapter
        binding.spinnerPkr?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when(p2){
                    0 -> {
                        currentPosition1 = "1000"
                    }

                    1 -> {
                        currentPosition1 = "1500"
                    }

                    2 -> {
                        currentPosition1 = "2000"
                    }

                    3 -> {
                        currentPosition1 = "3000"
                    }
                    else -> {
                        currentPosition1 = "1000"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, city)
        binding.spin?.adapter = arrayAdapter
        binding.spin?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> {
                        currentPosition = "Psychiatrist"
                    }

                    1 -> {
                        currentPosition = "Nutritionist"
                    }

                    2 -> {
                        currentPosition = "Plastic Surgeon"
                    }

                    3 -> {
                        currentPosition = "Nephrologist"
                    }

                    4 -> {
                        currentPosition = "Dentist"
                    }

                    5 -> {
                        currentPosition = "Dermatologist"
                    }

                    6 -> {
                        currentPosition = "Hand Surgeon"
                    }

                    else -> {
                        currentPosition = "Psychiatrist"
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Not implemented
            }
        }
        binding.subBtn.setOnClickListener {
            addData()
        }
        binding.seeYourAppointment.setOnClickListener {
            startActivity(Intent(this, DocMyAppointment::class.java))
            finish()
        }
    }

    private fun addData() {
        val name = binding.name.text.toString()
        val hospital = binding.hospital.text.toString()
        val city = binding.cetCity.text.toString()
        val years = binding.years.text.toString()
        val limit = binding.limit.text.toString()
        val model = DoctorDetailsModel(
            name = name,
            hospital = hospital,
            city = city,
            speciality = currentPosition,
            pkr = currentPosition1,
            years = years,
            limit = limit
        )
        if (TextUtils.isEmpty(name)) {
            showToast("This field is required")
        } else if (TextUtils.isEmpty(hospital)) {
            showToast("This field is required")
        } else if (TextUtils.isEmpty(years)) {
            showToast("This field is required")
        } else if (TextUtils.isEmpty(limit)) {
            showToast("This field is required")
        } else if (TextUtils.isEmpty(name) && TextUtils.isEmpty(limit) && TextUtils.isEmpty(years) && TextUtils.isEmpty(hospital)
        ) {
            showToast("All fields is empty")
        } else {
            fireStore.collection("DOCTORS")
                .document()
                .set(model)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        binding.name.setText("")
                        binding.cetCity.setText("")
                        binding.hospital.setText("")
                        binding.years.setText("")
                        binding.limit.setText("")
                        Toast.makeText(this, "data successfully transfer", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, HomePage::class.java))
                      this.finish()
                    }
                }.addOnFailureListener { error ->
                    error.message.toString()
                }
        }
    }
    private fun showCustomAlertDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.subscription_dailogue, null)
        builder.setView(view)

        val alertDialog = builder.create()

        // Set click listener for btn_subscribe
        val btnSubscribe = view.findViewById<Button>(R.id.btn_subscribe)
        btnSubscribe.setOnClickListener {
            alertDialog.dismiss()
        }

        builder.setPositiveButton("Yes") { dialogInterface, _ ->
            Toast.makeText(this, "Clicked on Yes", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        builder.setNegativeButton("No") { dialogInterface, _ ->
            Toast.makeText(this, "Clicked on No", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        builder.setNeutralButton("Cancel") { dialogInterface, _ ->
            Toast.makeText(this, "Clicked on Cancel", Toast.LENGTH_SHORT).show()
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
package com.example.homerentalapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homerentalapp.Dataclass.AppointmentModelClass
import com.example.homerentalapp.Fragmentsadopter.AppointmentAdopter
import com.example.homerentalapp.databinding.ActivityAppointmentBinding
import com.google.firebase.firestore.FirebaseFirestore

class Appointment : AppCompatActivity() {
    lateinit var binding: ActivityAppointmentBinding
    private val sandwich = mutableListOf<AppointmentModelClass>()
    val firstore: FirebaseFirestore? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backpress.setOnClickListener {
            this.finish()
        }

        binding.appointment.setHasFixedSize(true)
        binding.appointment.layoutManager = LinearLayoutManager(this)

        // Setting up the adapter
        val rv = AppointmentAdopter(this, sandwich) { position, dial ->
            if (dial.equals("dial", true)) {

                sandwich[position].id?.let {
                    FirebaseFirestore.getInstance().collection("Appointment").document(it).delete()
                        .addOnCompleteListener { }
                        .addOnFailureListener { }
                    finish()
                }
            } else {
                // itemClick
            }
        }
        binding.appointment.adapter = rv

        FirebaseFirestore.getInstance().collection("Appointment").get().addOnCompleteListener {
            if (it.isSuccessful) {
                it.result.toObjects(AppointmentModelClass::class.java).forEach {
                    sandwich.add(it)
                }.also {
                    rv.notifyDataSetChanged()
                }
                Toast.makeText(this, sandwich.size.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
package com.example.homerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homerentalapp.Dataclass.AppointmentModelClass
import com.example.homerentalapp.Fragmentsadopter.AppointmentAdopter
import com.example.homerentalapp.databinding.ActivityDocMyAppointmentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DocMyAppointment : AppCompatActivity() {
    lateinit var binding:ActivityDocMyAppointmentBinding
    private val sandwich = mutableListOf<AppointmentModelClass>()
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocMyAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.backpress.setOnClickListener {
            this.finish()
        }
        binding.logoutBtn.setOnClickListener {
            val sessionManager = SessionManager(this)
            sessionManager.setTheme(false)
            sessionManager.setPatient("")
            startActivity(Intent(this, Login::class.java))
            firebaseAuth.signOut()
           finish()
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
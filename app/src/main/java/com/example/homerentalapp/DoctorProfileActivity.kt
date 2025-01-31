package com.example.homerentalapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.databinding.ActivityDoctorProfileBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

class DoctorProfileActivity : AppCompatActivity() {
    private lateinit var fireStore: FirebaseFirestore
    lateinit var binding: ActivityDoctorProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireStore = FirebaseFirestore.getInstance()
        binding.sentMessage.setOnClickListener {
            val message = binding.message.text.toString()

            if (message.isNotEmpty()) {
                val hashMap = HashMap<String, String>()
                hashMap["message"] = message
                fireStore.collection("message").document().set(hashMap).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.message.setText("")
                        Toast.makeText(this, "Message transfer successfully", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Message not sent", Toast.LENGTH_SHORT).show()
                }
            } else {
                binding.message.error = getString(R.string.field_req)
                Toast.makeText(this@DoctorProfileActivity, "must enter some text On text box", Toast.LENGTH_SHORT).show()
            }
        }
        binding.backArrow.setOnClickListener {
            this.finish()
        }
        val name = intent.extras?.getString("name", "")
        findViewById<TextView>(R.id.name).text = name
        val speciality = intent.extras?.getString("special", "")
        findViewById<TextView>(R.id.speciality).text = speciality

    }
}
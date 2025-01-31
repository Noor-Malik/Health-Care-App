package com.example.homerentalapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.DoctorDetailsModel
import com.example.homerentalapp.Fragmentsadopter.Doctor_adopter
import com.example.homerentalapp.appcontant.AppConstant
import com.example.homerentalapp.databinding.ActivityHospitalDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class hospital_detail : AppCompatActivity() {
    lateinit var binding: ActivityHospitalDetailBinding
    private lateinit var fireStore: FirebaseFirestore

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        // Set the activity to immersive mode
        fireStore = FirebaseFirestore.getInstance()
        binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text = intent.extras?.getString("text", "")
        findViewById<TextView>(R.id.text).text = text
        val data = intent.extras?.getInt("data", -1)
        if (data == 0) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital1)
        } else if (data == 1) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital2)
        } else if (data == 2) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital3)
        } else if (data == 3) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital4)
        } else if (data == 4) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospial5)
        } else if (data == 5) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital6)
        } else if (data == 6) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital7)
        } else if (data == 7) {
            findViewById<ImageView>(R.id.tvTitle).setImageResource(R.drawable.hospital8)
        }
        findViewById<ImageView>(R.id.backpress).setOnClickListener {
            this.finish()
        }
        binding.backpress.setOnClickListener {
            this.finish()
        }
        //recyclerView
        binding.carlist.setHasFixedSize(true)
        val sandwich = mutableListOf<DoctorDetailsModel>()
        binding.carlist.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = Doctor_adopter(this, sandwich) { position, card ->

            if (card.equals("card", true)) {
                val d = fireStore.collection("Appointment").document()
                d.set(
                    DoctorDetailsModel(
                        id = d.id,
                        name = sandwich.get(position).name,
                        hospital = sandwich.get(position).hospital,
                    )
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
//                        Toast.makeText(this, "Set Appointment", Toast.LENGTH_SHORT).show()
                        AppConstant.id = d.id
                        startActivity(Intent(this@hospital_detail, CalenderDate::class.java))
                    }
                }
            }
        }
        binding.carlist.adapter = adapter
        fireStore.collection("DOCTORS")
            .get()
            .addOnCompleteListener {
                it.result.forEach {
                    val model = it.toObject(DoctorDetailsModel::class.java)
                    sandwich.add(model)
                }.also {
                    adapter.notifyDataSetChanged()
                }
            }
    }
}

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
import com.example.homerentalapp.databinding.ActivityDoctorDetailBinding
import com.google.android.play.core.integrity.d
import com.google.firebase.firestore.FirebaseFirestore

class DoctorDetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDoctorDetailBinding
    private lateinit var fireStore: FirebaseFirestore
    val sandwich = mutableListOf<DoctorDetailsModel>()
    private var adapter: Doctor_adopter? = null
    private var data: String? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fireStore = FirebaseFirestore.getInstance()
        data = intent.extras?.getString("data", "")
        findViewById<TextView>(R.id.tvTitle).text = data
        findViewById<ImageView>(R.id.backpress).setOnClickListener {
            this.finish()
        }

        binding.docDetail.setHasFixedSize(true)
        binding.docDetail.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = Doctor_adopter(this, sandwich) { position, card ->

            if (card.equals("card", true)) {
                val fire = fireStore.collection("Appointment").document()
                fire.set(DoctorDetailsModel(id = fire.id,name = sandwich[position].name, hospital = sandwich[position].hospital)).addOnCompleteListener {
                    if (it.isSuccessful) {
                        AppConstant.id = fire.id
//                        Toast.makeText(
//                            this,
//                            "",
//                            Toast.LENGTH_SHORT
//                        ).show()
                        startActivity(Intent(this, CalenderDate::class.java))
                    }
                }
            } else {
                try {
                    Intent(this, DoctorProfileActivity::class.java).also {
                        it.putExtra("name", sandwich[position].name)
                        it.putExtra("special", sandwich[position].speciality)
                        startActivity(it)
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        }

        data?.let { getDoctors(it) }

        binding.docDetail.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDoctors(value: String) {
        fireStore.collection("DOCTORS")
            .whereEqualTo("speciality", value)
            .get()
            .addOnCompleteListener {
                it.result.forEach {
                    val model = it.toObject(DoctorDetailsModel::class.java)
                    sandwich.add(model)
                }.also {
                    adapter?.notifyDataSetChanged()
                }
            }
    }
}
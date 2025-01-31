package com.example.homerentalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.homerentalapp.databinding.ActivityNurseProfileBinding

class NurseProfileActivity : AppCompatActivity() {
    lateinit var binding:ActivityNurseProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNurseProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.extras?.getString("name","")
        findViewById<TextView>(R.id.name).text = name
        val specility = intent.extras?.getString("specility","")
        findViewById<TextView>(R.id.speciality).text = specility
        binding.backArrow.setOnClickListener {
            this.finish()
        }

    }
}
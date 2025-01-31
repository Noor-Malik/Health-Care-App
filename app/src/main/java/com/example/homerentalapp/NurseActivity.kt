package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.NurseModelclass
import com.example.homerentalapp.Fragmentsadopter.NurseAdopter
import com.example.homerentalapp.databinding.ActivityNurseBinding

class NurseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNurseBinding
    private val list = mutableListOf<NurseModelclass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNurseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.RecyclerNurse.setHasFixedSize(true)
        binding.RecyclerNurse.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        list.add(
            NurseModelclass(
                R.drawable.nurse1
                ,
                "Afreen",
                "Bandages",
                "700 pkr",
                "14 years",
                "30 min"
            )
        )
        list.add(
            NurseModelclass(
                R.drawable.nurse1,
                "Noor",
                "CGG",
                "700 pkr",
                "14 years",
                "30 min"
            )
        )
        list.add(
            NurseModelclass(
                R.drawable.nurse1,
                "Nurgis",
                "MBA",
                "700 pkr",
                "14 years",
                "30 min"
            )
        )
        list.add(
            NurseModelclass(
                R.drawable.nurse1,
                "Rabia",
                "CBA",
                "700 pkr",
                "14 years",
                "30 min"
            )
        )
        list.add(
            NurseModelclass(
                R.drawable.nurse1,
                "Hina",
                "DBA",
                "700 pkr",
                "14 years",
                "30 min"
            )
        )
        val adopter = NurseAdopter(this, list) {
            val intent = Intent(this,NurseProfileActivity::class.java)
            intent.putExtra("name", list[it].name)
            intent.putExtra("specility", list[it].Specility)
            startActivity(intent)
        }

        binding.RecyclerNurse.adapter = adopter


        binding.back.setOnClickListener {
            finish()
        }
    }
}
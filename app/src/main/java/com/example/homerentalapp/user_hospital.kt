package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.hos_item_user
import com.example.homerentalapp.Fragmentsadopter.hos_user_item
import com.example.homerentalapp.databinding.ActivityUserHospitalBinding
import java.util.Locale

class user_hospital : AppCompatActivity() {
    lateinit var binding: ActivityUserHospitalBinding
    private lateinit var adapter: hos_user_item
    private val sandwich = mutableListOf<hos_item_user>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button click listener
        binding.backpress.setOnClickListener {
            this.finish()
        }

        // Spinner setup for city selection
        val city = arrayOf("Select city")
        val city1 = arrayOf("Lahore")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, city)
        binding.spin.adapter = arrayAdapter
        binding.spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // Not implemented
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Not implemented
            }
        }
        val arrayAdapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, city1)
        binding.spin2.adapter = arrayAdapter1
        binding.spin2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(
                    applicationContext,
                    "Select city is " + city1[p2],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Not implemented
            }
        }

        // RecyclerView setup
        binding.hosItem.setHasFixedSize(true)
        binding.hosItem.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Adding data to the list
        sandwich.add(hos_item_user(R.drawable.hospital1, "Lahore | Porani Anarkali ", "RAZA Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital2, "Lahore | DHA Phase 7", "Hameed Latif Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital3, "Lahore | DHA Phase 8", "Fatima Mamoriyal Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital4, "Lahore | Shahdara ", "Jinnah Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital6, "Lahore | Bhatti Gate", "GOVT Jinnah dentist Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital7, "Lahore | Porani Anerkali", "MIYO Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospital8, "Lahore | Rawaind Lahore", "Umeed Hospital"))
        sandwich.add(hos_item_user(R.drawable.hospial5, "Lahore  | Gulshan RAVi ", "Tayyab Hospital"))

        // Setting up the adapter
        adapter = hos_user_item(this, sandwich) {
            try {
                val intent = Intent(this, hospital_detail::class.java)
                intent.putExtra("data", it)
                intent.putExtra("text", sandwich[it].text1)
                startActivity(intent)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        binding.hosItem.adapter = adapter

        // Setting up search functionality
        binding.search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText.orEmpty())
                return true
            }
        })
    }

    // Filter the list based on search query
    private fun filterList(query: String) {
        val filteredList = sandwich.filter { it.text1.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredList)
        }
    }
}

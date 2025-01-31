package com.example.homerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.data_specility_doc
import com.example.homerentalapp.Fragmentsadopter.doc_specility_adopter
import com.example.homerentalapp.databinding.ActivityDoctorSpeciliatiyBinding
import java.util.Locale

class DoctorSpeciliatiyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDoctorSpeciliatiyBinding
    private lateinit var adapter: doc_specility_adopter
    private val sandwich = mutableListOf<data_specility_doc>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorSpeciliatiyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backpress.setOnClickListener {
            this.finish()
        }
        // Setting up the RecyclerView
        binding.hosItem.setHasFixedSize(true)
        binding.hosItem.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        // Adding data to the list
        sandwich.add(data_specility_doc(R.drawable.radiologist, "ریڈیولوجسٹ","Radiologist"))
        sandwich.add(data_specility_doc(R.drawable.psychiatrist, "ماہر نفسیات","Psychiatrist"))
        sandwich.add(data_specility_doc(R.drawable.neutrionist, "ماہر غذائیت","Nutritionist"))
        sandwich.add(data_specility_doc(R.drawable.surgen, "پلاسٹک سرجن","Plastic Surgeon"))
        sandwich.add(data_specility_doc(R.drawable.nephrologist, "ماہر امراض گردہ","Nephrologist"))
        sandwich.add(data_specility_doc(R.drawable.d, "دندان ساز","Dentist"))
        sandwich.add(data_specility_doc(R.drawable.dermatologist, "ماہر امراض جلد","Dermatologist"))
        sandwich.add(data_specility_doc(R.drawable.hand, "ہاتھ کا سرجن","Hand Surgeon"))

        // Setting up the adapter
        adapter = doc_specility_adopter(this, sandwich) {
            try {
                startActivity(Intent(this , DoctorDetailActivity::class.java).putExtra("data", sandwich[it].text1))
            } catch (exception : Exception) {
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

    private fun filterList(query: String) {
        val filteredList = sandwich.filter { it.text1.toLowerCase(Locale.ROOT).contains(query.toLowerCase(Locale.ROOT)) }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
        } else {
            adapter.filterList(filteredList)
        }
    }
}

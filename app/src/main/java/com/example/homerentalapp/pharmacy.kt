package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.productData
import com.example.homerentalapp.Dataclass.shopData
import com.example.homerentalapp.Fragmentsadopter.productAdopter
import com.example.homerentalapp.Fragmentsadopter.shopadopterclass
import com.example.homerentalapp.appcontant.AppConstant
import com.example.homerentalapp.databinding.ActivityPharmacyBinding

class pharmacy : AppCompatActivity() {

    private lateinit var binding: ActivityPharmacyBinding
    private lateinit var shopAdapter: shopadopterclass
    private lateinit var productAdapter: productAdopter
    private val originalShopList = mutableListOf<shopData>()
    private val originalProductList = mutableListOf<productData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPharmacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up shop RecyclerView
        binding.shopproduct.setHasFixedSize(true)
        originalShopList.addAll(createShopData())
        binding.shopproduct.layoutManager =
            LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        shopAdapter = shopadopterclass(this, originalShopList) {
            AppConstant.image=originalShopList[it].image
            AppConstant.text=originalShopList[it].text
            startActivity(Intent(this, shop::class.java))
        }
        binding.shopproduct.adapter = shopAdapter

        // Set up product RecyclerView
        binding.product.setHasFixedSize(true)
        originalProductList.addAll(createProductData())
        binding.product.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        productAdapter = productAdopter(this, originalProductList) {
            AppConstant.image=originalProductList[it].image
            AppConstant.text=originalProductList[it].text1
            try {
                val intent = Intent(this, shop::class.java)
                intent.putExtra("data", it)
                intent.putExtra("text", originalProductList[it].text1)
                startActivity(intent)
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
        binding.product.adapter = productAdapter

        // Search functionality
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not implemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterShopData(s.toString())
                filterProductData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Not implemented
            }
        })

        binding.backpress.setOnClickListener {
            finish()
        }
    }

    private fun filterShopData(query: String) {
        val filteredList = originalShopList.filter { it.text.contains(query, ignoreCase = true) }
        shopAdapter.submitList(filteredList.toMutableList())
    }

    private fun filterProductData(query: String) {
        val filteredList =
            originalProductList.filter { it.text1.contains(query, ignoreCase = true) }
        productAdapter.submitList(filteredList.toMutableList())
    }

    private fun createShopData(): List<shopData> {
        return mutableListOf(
            shopData(R.drawable.syringe, "Syringe"),
            shopData(R.drawable.medicine, "Pills"),
            shopData(R.drawable.multi_medicine, "Medicine"),
            shopData(R.drawable.medicine2, "Medicine"),
            shopData(R.drawable.medicine2, "Medicine")
        )
    }

    private fun createProductData(): List<productData> {
        return mutableListOf(
            productData(R.drawable.medicine2, "Multi medicines", "90 pills", "$120", "$129"),
            productData(R.drawable.multi_medicine, "Multi medicines", "90 pills", "$120", "$129"),
            productData(R.drawable.syringe, "Multi medicines", "90 pills", "$120", "$129"),
            productData(R.drawable.medicine, "Multi medicines", "90 pills", "$120", "$129"),
            productData(R.drawable.medicine2, "Multi medicines", "90 pills", "$120", "$129"),
            productData(R.drawable.medicine2, "Multi medicines", "90 pills", "$120", "$129")
        )
    }
}

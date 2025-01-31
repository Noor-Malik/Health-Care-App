package com.example.homerentalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.homerentalapp.appcontant.AppConstant
import com.example.homerentalapp.databinding.ActivityPharmacyBinding
import com.example.homerentalapp.databinding.ActivityShopBinding

class shop : AppCompatActivity()  {
    lateinit var binding: ActivityShopBinding
    var index = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val image=AppConstant.image

        binding.multiMedicineShop.text=AppConstant.text


        if (image!=null){
            binding.imageProductShop.setImageResource(image)
        }





        binding.cardShop.setOnClickListener{
            startActivity(Intent(this,Shop_detail::class.java))
        }

        binding.backArrow.setOnClickListener {
            this.finish()
        }

        binding.favProduct.setOnClickListener {
            if (index == 0) {
                index = 1
                binding.favProduct.setImageResource(R.drawable.baseline_favorite_border_24)
            } else {
                index = 0
                binding.favProduct.setImageResource(R.drawable.favourite)
            }
        }
    }

    fun showToast(value:String){
        Toast.makeText(this, value, Toast.LENGTH_SHORT).show()
    }
}
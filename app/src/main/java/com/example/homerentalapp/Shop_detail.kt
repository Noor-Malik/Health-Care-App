package com.example.homerentalapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.appcontant.AppConstant
import com.example.homerentalapp.databinding.ActivityShopDetailBinding

class Shop_detail : AppCompatActivity() {
    lateinit var binding: ActivityShopDetailBinding
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = AppConstant.image

        binding.multiVitmensDetail.text = AppConstant.text
        if (image != null) {
            binding.imageViewDetail.setImageResource(image)
        }

        binding.backArrow.setOnClickListener {
            this.finish()
        }

        binding.btnAdd.setOnClickListener {
            count++
            binding.tvCount.text = count.toString()
        }

        binding.btnMinus.setOnClickListener {
            if (count > 0) {
                count--
                binding.tvCount.text = count.toString()
            }
        }

        binding.phone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:03096731072")
            }
            startActivity(intent)
            Toast.makeText(this, "calling", Toast.LENGTH_SHORT).show()
        }
    }
}

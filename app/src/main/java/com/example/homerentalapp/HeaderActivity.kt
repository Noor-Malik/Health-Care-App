package com.example.homerentalapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homerentalapp.databinding.ActivityHeaderBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

class HeaderActivity : AppCompatActivity() {
    lateinit var binding: ActivityHeaderBinding

    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        binding = ActivityHeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val num = sessionManager.getnumber()
        val name = sessionManager.getname()
        binding.UserName.text = name
        binding.numberText.text = num
    }

}
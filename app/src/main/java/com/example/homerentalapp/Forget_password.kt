package com.example.homerentalapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.databinding.ActivityForgetPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class Forget_password : AppCompatActivity() {
    lateinit var binding: ActivityForgetPasswordBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.back.setOnClickListener {
            this.finish()
        }
        binding.login.setOnClickListener {
            val spassword = binding.email.text.toString()
            firebaseAuth.sendPasswordResetEmail(spassword)
                .addOnSuccessListener {
                    Toast.makeText(this, "please Check your email", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()

                }
        }

    }
}
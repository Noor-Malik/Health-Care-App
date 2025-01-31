package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        val sessionManager = SessionManager(this)

        binding.login.setOnClickListener {
            binding.progressbar.visibility = android.view.View.VISIBLE
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            sessionManager.setPatient("patient")
                            sessionManager.setTheme(true)
                            val intent = Intent(this, HomePage::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Invalid email or password",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        binding.progressbar.visibility = android.view.View.GONE
                    }
            } else {
                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_LONG
                ).show()
                binding.progressbar.visibility = android.view.View.GONE
            }
        }

        binding.loginDoctor.setOnClickListener {
            binding.progressbar.visibility = android.view.View.VISIBLE
            val email = binding.email.text.toString()
            val pass = binding.pass.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            sessionManager.setTheme(true)
                            sessionManager.setPatient("doctor")
                            val intent = Intent(this, profileDoc::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this,
                                "Invalid email or password",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        binding.progressbar.visibility = android.view.View.GONE
                    }
            } else {
                Toast.makeText(
                    this,
                    "Please enter email and password",
                    Toast.LENGTH_LONG
                ).show()
                binding.progressbar.visibility = android.view.View.GONE
            }
        }
        binding.forgetpassword.setOnClickListener {
            startActivity(Intent(this, Forget_password::class.java))
            this.finish()
        }
        binding.createHere.setOnClickListener {
            startActivity(Intent(this, Sign_up::class.java))
            this.finish()

        }
    }
}

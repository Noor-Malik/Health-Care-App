package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sessionManager = SessionManager(this)
        val textView: TextView = findViewById(R.id.home) // Use the correct ID from your layout
        val sideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        textView.startAnimation(sideAnimation)

        Handler(Looper.getMainLooper()).postDelayed({
            if (sessionManager.getTheme()) {
                if (sessionManager.getPatient().equals("doctor", true)) {
                    startActivity(Intent(this, profileDoc::class.java))
                } else {
                    startActivity(Intent(this, HomePage::class.java))
                }
            } else {
                startActivity(Intent(this, Login::class.java))
            }
            finish()
        }, 2000)
    }
}

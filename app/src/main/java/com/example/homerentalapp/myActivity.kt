package com.example.homerentalapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewbinding.ViewBinding
import com.google.android.material.navigation.NavigationView

abstract class myActivity <viewBinding : ViewBinding> : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private var _binding: viewBinding? = null
    protected val binding: viewBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()
        initViews()
        actions()
        val drawerlayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()
//        binding.dehaze.setOnClickListener {
//
//
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            navView.setNavigationItemSelectedListener {
//                when(it.itemId){
//                    R.id.home -> Toast.makeText(applicationContext,"Clicked on Home",
//                        Toast.LENGTH_LONG
//                    ).show()
//                    R.id.setting -> Toast.makeText(applicationContext,"Clicked on Setting",
//                        Toast.LENGTH_LONG
//                    ).show()
//
//                }
//                true
//            }
    }

    abstract fun getViewBinding(): viewBinding

    abstract fun initViews()

    abstract fun actions()

    fun goToNext(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
    }

    fun goToNextWithFinish(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        finish()
    }

    fun goToNextWithIntent(destination: Class<*>, vararg flags: Int) {
        val intent = Intent(this, destination)
        flags.forEach { intent.addFlags(it) }
        startActivity(intent)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
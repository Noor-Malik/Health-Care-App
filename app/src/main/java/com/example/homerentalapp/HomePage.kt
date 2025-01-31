package com.example.homerentalapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homerentalapp.databinding.ActivityHomePageBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import de.hdodenhof.circleimageview.CircleImageView

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var sessionManager: SessionManager
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drawerlayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
        val name = sessionManager.getname()
        val Number = sessionManager.getnumber()
        binding.UserName.text = "Hello $name !!"

        // Set user name and number in navigation header
        val navHeaderView = binding.navView.getHeaderView(0)
        navHeaderView.findViewById<TextView>(R.id.UserName)?.text = name
        navHeaderView.findViewById<TextView>(R.id.numberText)?.text = Number
        getImage(navHeaderView)


        // Set up ActionBarDrawerToggle
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        drawerlayout = findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerlayout, toolBar, R.string.open, R.string.close)
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set up navigation item click listener
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> replaceFragment(HomeFragment(), menuItem.title.toString())
                R.id.setting -> replaceFragment(setting_Fragment(), menuItem.title.toString())
                R.id.favourite -> replaceFragment(theme_fragment(), menuItem.title.toString())
                R.id.appointment -> startActivity(Intent(this, Appointment::class.java))
                R.id.profile -> startActivity(Intent(this, Profile_Activity::class.java))
                R.id.logout -> {
                    val sessionManager = SessionManager(this)
                    firebaseAuth.signOut()
                    sessionManager.setTheme(false)
                    sessionManager.setPatient("")
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }
            }
            true
        }

        // Set up initial fragment
        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

        // Load user image

    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getImage(view: View) {
        val ImageView = view.findViewById<CircleImageView>(R.id.image1)
        FirebaseFirestore.getInstance().collection("images")
            .document("myImage")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val model = task.result?.toObject(ImageClass::class.java)
                    val imageUrl = model?.image
                    if (imageUrl != null) {
                        Glide.with(this).load(imageUrl).into(ImageView)

                    } else {
                        // Handle case where image URL is null
                        // For example, display a placeholder image or show an error message
                    }
                } else {
                    // Handle error
                }
            }
    }
}

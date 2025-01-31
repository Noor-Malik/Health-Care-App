package com.example.homerentalapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homerentalapp.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.database

class Sign_up : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    lateinit var firebaseAuth: FirebaseAuth
    private var isDoubleClick = false

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.alreadyAccountTV.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
            finish()
        }
//        binding.addButton.setOnClickListener {
//
//            if (!isDoubleClick) {
//                binding.doctor.visibility = View.GONE
//
//                isDoubleClick = true
//            } else {
//                binding.doctor.visibility = View.VISIBLE
//
//                isDoubleClick = false
//            }
//        }
        //Patent button
        binding.patient.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            val name = binding.name.text.toString().trim()
            val email = binding.emails.text.toString().trim()
            val numberText = binding.mobileno.text.toString().trim()
            val password = binding.password.text.toString()


            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                binding.progressbar.visibility = View.GONE
                binding.name.setBackgroundColor(Color.TRANSPARENT)
//                Toast.makeText(this, "Valid input: $name", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressbar.visibility = View.GONE
                binding.name.setBackgroundColor(Color.RED)
            }

            if (isValidEmail(email)) {
                binding.progressbar.visibility = View.GONE
                binding.emails.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.progressbar.visibility = View.GONE
                binding.emails.setBackgroundColor(Color.RED)
                Toast.makeText(
                    this,
                    "Field empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (isValidNumber(numberText) && numberText.length == 11) {
                binding.progressbar.visibility = View.GONE
                binding.mobileno.setBackgroundColor(Color.TRANSPARENT)

            } else if (numberText.isEmpty()) {
                binding.progressbar.visibility = View.GONE
                binding.mobileno.setBackgroundColor(Color.RED)
            }


            if (isValidPassword(password)) {
                binding.progressbar.visibility = View.GONE
                binding.password.setBackgroundColor(Color.TRANSPARENT)
            } else if (password.isEmpty()) {
                binding.progressbar.visibility = View.GONE
                binding.password.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && numberText.isNotEmpty()) {
                binding.progressbar.visibility = View.VISIBLE
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            sessionManager.setPatient("patient")
                            sessionManager.setTheme(true)
                            binding.progressbar.visibility = View.GONE
                            firebaseAuth.currentUser?.sendEmailVerification()
                            val database = Firebase.database.getReference("patient").child(name)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("number", numberText)
                            hashMap.put("password", password)


                            database.setValue(hashMap)
                                .addOnCompleteListener { db ->
                                    if (db.isSuccessful) {
                                        binding.progressbar.visibility = View.GONE
//                                        val intent1 = Intent(this, Profile_Activity::class.java)
//                                        intent1.putExtra(name, "name")
//                                        intent1.putExtra(email, "email")
//                                        intent1.putExtra(numberText, "number")
//                                        startActivity(intent1)

                                        Toast.makeText(
                                            this,
                                            "Sign up successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        sessionManager.apply {
                                            setname(name)
                                            setemail(email)
                                            setpassword(password)
                                            setnumber(numberText)
                                        }
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                                .addOnFailureListener {
                                    binding.progressbar.visibility = View.GONE
                                    Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
                            binding.progressbar.visibility = View.GONE
                            Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                binding.progressbar.visibility = View.GONE
                Toast.makeText(this, "Field empty ", Toast.LENGTH_SHORT).show()
            }
        }
        //admin button
        //doctor button
        binding.doctor.setOnClickListener {
            binding.progressbar.visibility = View.VISIBLE
            val name = binding.name.text.toString().trim()
            val email = binding.emails.text.toString().trim()
            val numberText = binding.mobileno.text.toString().trim()
            val password = binding.password.text.toString()

            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                binding.progressbar.visibility = View.GONE
                binding.name.setBackgroundColor(Color.TRANSPARENT)
//                Toast.makeText(this, "Valid input: $name", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressbar.visibility = View.GONE
                binding.name.setBackgroundColor(Color.RED)
            }

            if (isValidEmail(email)) {
                binding.progressbar.visibility = View.GONE
                binding.emails.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.progressbar.visibility = View.GONE
                binding.emails.setBackgroundColor(Color.RED)
                Toast.makeText(
                    this,
                    "Field empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (isValidNumber(numberText) && numberText.length == 11) {
                binding.progressbar.visibility = View.GONE
                binding.mobileno.setBackgroundColor(Color.TRANSPARENT)

            } else if (numberText.isEmpty()) {
                binding.progressbar.visibility = View.GONE
                binding.mobileno.setBackgroundColor(Color.RED)
            }


            if (isValidPassword(password)) {
                binding.progressbar.visibility = View.GONE
                binding.password.setBackgroundColor(Color.TRANSPARENT)
            } else if (password.isEmpty()) {
                binding.progressbar.visibility = View.GONE
                binding.password.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && numberText.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            sessionManager.setPatient("doctor")
                            sessionManager.setTheme(true)
                            binding.progressbar.visibility = View.GONE
                            val database = Firebase.database.getReference("Doctor").child(name)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("number", numberText)
                            hashMap.put("password", password)


                            database.setValue(hashMap)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Sign up successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                                .addOnFailureListener {
                                    binding.progressbar.visibility = View.GONE
                                    Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
                            binding.progressbar.visibility = View.GONE
                            Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                binding.progressbar.visibility = View.GONE
                Toast.makeText(this, "Field empty ", Toast.LENGTH_SHORT).show()
            }
//            val database = Firebase.database.getReference("patient").child(name)
//            database.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val userData = dataSnapshot.getValue(User::class.java)
//                    if (userData != null) {
//                        // Data retrieved successfully
//                        val intent = Intent(this@Sign_up, Profile_Activity::class.java)
//                        intent.putExtra("name", userData.name)
//                        intent.putExtra("email", userData.email)
//                        intent.putExtra("number", userData.number)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(this@Sign_up, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    // Getting data failed, log a message
//                    Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
//                }
//            })
        }
        //Pharmacy button
        binding.pharmacy.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val email = binding.emails.text.toString().trim()
            val numberText = binding.mobileno.text.toString().trim()
            val password = binding.password.text.toString()

            sessionManager.setUser("pharmacy")

            binding.progressbar.visibility = it.visibility

            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                binding.name.setBackgroundColor(Color.TRANSPARENT)
//                Toast.makeText(this, "Valid input: $name", Toast.LENGTH_SHORT).show()


            } else {
                binding.name.setBackgroundColor(Color.RED)

            }

            if (isValidEmail(email)) {
                binding.emails.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.emails.setBackgroundColor(Color.RED)
                Toast.makeText(
                    this,
                    "Field empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (isValidNumber(numberText) && numberText.length == 11) {
                binding.mobileno.setBackgroundColor(Color.TRANSPARENT)

            } else if (numberText.isEmpty()) {
                binding.mobileno.setBackgroundColor(Color.RED)


            }


            if (isValidPassword(password)) {
                binding.password.setBackgroundColor(Color.TRANSPARENT)
            } else if (password.isEmpty()) {

                binding.password.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && numberText.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val database = Firebase.database.getReference("pharmacy").child(name)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("number", numberText)
                            hashMap.put("password", password)


                            database.setValue(hashMap)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Sign up successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                                .addOnFailureListener {

                                    Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
                            Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                Toast.makeText(this, "Field empty ", Toast.LENGTH_SHORT).show()
            }
        }
        //Hospital button
        binding.Hospital.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val email = binding.emails.text.toString().trim()
            val numberText = binding.mobileno.text.toString().trim()
            val password = binding.password.text.toString()

            binding.progressbar.visibility = it.visibility

            sessionManager.setUser("Hospital")


            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                binding.name.setBackgroundColor(Color.TRANSPARENT)
//                Toast.makeText(this, "Valid input: $name", Toast.LENGTH_SHORT).show()


            } else {
                binding.name.setBackgroundColor(Color.RED)

            }

            if (isValidEmail(email)) {
                binding.emails.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.emails.setBackgroundColor(Color.RED)
                Toast.makeText(
                    this,
                    "Field empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (isValidNumber(numberText) && numberText.length == 11) {
                binding.mobileno.setBackgroundColor(Color.TRANSPARENT)

            } else if (numberText.isEmpty()) {
                binding.mobileno.setBackgroundColor(Color.RED)


            }


            if (isValidPassword(password)) {
                binding.password.setBackgroundColor(Color.TRANSPARENT)
            } else if (password.isEmpty()) {

                binding.password.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && numberText.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val database = Firebase.database.getReference("Hospital").child(name)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("number", numberText)
                            hashMap.put("password", password)


                            database.setValue(hashMap)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Sign up successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                                .addOnFailureListener {

                                    Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
                            Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }

            } else {
                Toast.makeText(this, "Field empty", Toast.LENGTH_SHORT).show()
            }
        }
        //vendor button
        binding.vendor.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val email = binding.emails.text.toString().trim()
            val numberText = binding.mobileno.text.toString().trim()
            val password = binding.password.text.toString()

            sessionManager.setUser("vendor")

            binding.progressbar.visibility = it.visibility

            if (name.matches(Regex("^[a-zA-Z ]+$"))) {
                binding.name.setBackgroundColor(Color.TRANSPARENT)
//                Toast.makeText(this, "Valid input: $name", Toast.LENGTH_SHORT).show()


            } else {
                binding.name.setBackgroundColor(Color.RED)

            }

            if (isValidEmail(email)) {
                binding.emails.setBackgroundColor(Color.TRANSPARENT)
            } else {
                binding.emails.setBackgroundColor(Color.RED)
                Toast.makeText(
                    this,
                    "Field empty",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (isValidNumber(numberText) && numberText.length == 11) {
                binding.mobileno.setBackgroundColor(Color.TRANSPARENT)

            } else if (numberText.isEmpty()) {
                binding.mobileno.setBackgroundColor(Color.RED)


            }


            if (isValidPassword(password)) {
                binding.password.setBackgroundColor(Color.TRANSPARENT)
            } else if (password.isEmpty()) {

                binding.password.setBackgroundColor(Color.RED)
                Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show()
            }
            if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && numberText.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {

                            val database = Firebase.database.getReference("Vendor").child(name)

                            val hashMap: HashMap<String, String> = HashMap()
                            hashMap.put("name", name)
                            hashMap.put("email", email)
                            hashMap.put("number", numberText)
                            hashMap.put("password", password)


                            database.setValue(hashMap)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Sign up successful",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        val intent = Intent(this, Login::class.java)
                                        startActivity(intent)
                                        finish()
                                    }
                                }
                                .addOnFailureListener {

                                    Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT)
                                        .show()
                                }

                        } else {
                            Toast.makeText(this, "Sign up Unsuccessful", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Field empty ", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //email validation
    private fun isValidEmail(email: String): Boolean {
        // Using Android's Patterns.EMAIL_ADDRESS for basic email validation
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //number validation
    private fun isValidNumber(numberText: String): Boolean {
        return try {
            // Try parsing the string to a number
            val number = numberText.toDouble()
            true
        } catch (e: NumberFormatException) {
            // Not a valid number
            false
        }
    }

    //valdiation password
    private fun isValidPassword(password: String): Boolean {
        // Define your password validation rules
        val passwordPattern =
            Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@$!%*?&]{8,}\$")

        return passwordPattern.matches(password)

    }

//    }
}
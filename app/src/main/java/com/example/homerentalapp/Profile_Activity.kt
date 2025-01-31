package com.example.homerentalapp

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.homerentalapp.databinding.ActivityProfileBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Profile_Activity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storageRef: StorageReference
    private lateinit var firebaseFirestore: FirebaseFirestore
    private var imageUri: Uri? = null
    private var selectedImage: ImageView? = null
    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getImage()
        initVars()
        setUpViews()
        sessionManager = SessionManager(this)
        val name = sessionManager.getname()
        val email = sessionManager.getemail()
        val pass = sessionManager.getpassword()
        val num = sessionManager.getnumber()
        binding.name.setText(name)
        binding.email.setText(email)
        binding.Password.setText(pass)
        binding.numberText.setText(num)

        binding.EditProfile.setOnClickListener {
            val name1 = binding.name.text.toString()
            val email1 = binding.email.text.toString()
            val pass1 = binding.Password.text.toString()
            val num1 = binding.numberText.text.toString()

            if (name1.isNotEmpty() && email1.isNotEmpty() && num1.isNotEmpty() && pass1.isNotEmpty()){
                sessionManager.apply {
                    setname(name1)
                    setemail(email1)
                    setpassword(pass1)
                    setnumber(num1)
                }
                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show()
                this.finish()

            }
           else {
                Toast.makeText(this, "All filled required", Toast.LENGTH_SHORT).show()
            }

            val name = sessionManager.getname()
            val email = sessionManager.getemail()
            val pass = sessionManager.getpassword()
            val num = sessionManager.getnumber()

            binding.name.setText(name)
            binding.email.setText(email)
            binding.Password.setText(pass)
            binding.numberText.setText(num)

        }
    }
    private fun initVars() {
        storageRef = FirebaseStorage.getInstance().reference.child("Images")
        firebaseFirestore = FirebaseFirestore.getInstance()
    }

    private fun setUpViews() {
        binding.addBtn.setOnClickListener {
            AlertDialog()
        }
    }
    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            imageUri = uri
            selectedImage?.setImageURI(uri) // Set selected image URI
        }
    }
    private fun openImagePicker() {
        resultLauncher.launch("image/*")
    }

    private fun uploadImage() {
        imageUri?.let { uri ->
            val imageRef = storageRef.child("${System.currentTimeMillis()}")
            imageRef.putFile(uri)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        imageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            val imageUrl = downloadUri.toString()
                            // Update ImageClass to hold the correct field for the image URL
                            val map = ImageClass(image = imageUrl)

                            Glide.with(this)
                                .load(imageUrl)
                                .into(binding.image)

                            firebaseFirestore.collection("images").document("myImage")
                                .set(map)
                                .addOnCompleteListener { firestoreTask ->
                                    if (firestoreTask.isSuccessful) {
                                        Toast.makeText(
                                            this,
                                            "Uploaded Successfully",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        // Optionally, clear selected image after successful upload
                                        selectedImage?.setImageURI(null)
                                    } else {
                                        Toast.makeText(
                                            this,
                                            firestoreTask.exception?.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        } ?: run {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show()
        }
    }

    fun AlertDialog(){
        val Dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        val inflate = layoutInflater
        val view = inflate.inflate(R.layout.alertdailge,null)
        Dialog.setView(view)
        val AlertDialog = Dialog.create()
        val add = view.findViewById<Button>(R.id.add_btn)
        val fab = view.findViewById<FloatingActionButton>(R.id.floating_btn)
        selectedImage = view.findViewById(R.id.alert_post_image)
        fab.setOnClickListener {
            openImagePicker()
        }
        add.setOnClickListener {
            uploadImage()
            AlertDialog.dismiss()

        }

        AlertDialog.show()

    }
    private fun getImage() {
        FirebaseFirestore.getInstance().collection("images")
            .document("myImage")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val model = task.result?.toObject(ImageClass::class.java)
                    val imageUrl = model?.image
                    if (imageUrl != null) {
                        Glide.with(this).load(imageUrl).into(binding.image)

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

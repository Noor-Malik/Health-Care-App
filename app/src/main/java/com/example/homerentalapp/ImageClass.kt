package com.example.homerentalapp

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date

data class ImageClass(
    val image: String? = null,
    @ServerTimestamp
    var timeDate: Date? = null
): Serializable
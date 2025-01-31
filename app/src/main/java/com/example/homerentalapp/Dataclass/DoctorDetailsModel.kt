package com.example.homerentalapp.Dataclass

import com.google.firebase.firestore.ServerTimestamp
import java.io.Serializable
import java.util.Date

data class DoctorDetailsModel(
    val id: String? = null,
    val name: String? = null,
    val hospital: String? = null,
    val city: String? = null,
    val speciality: String? = null,
    val pkr:  String? = null,
    val years :String? = null,
    val limit : String? = null,
    val date : String? = null,
    val time: String? = null,

    @ServerTimestamp
    var timestamp: Date? = null
) : Serializable

package com.example.homerentalapp.Dataclass

import java.io.Serializable

class AppointmentModelClass(
    var id: String? = null,
    val image: Int? = null,
    val name: String? = null,
    val hospital: String? = null,
    val date: String? = null,
    val time: String? = null,
    val ContactNo: String? = null,
    val image2: Int? = null,
) : Serializable
package com.example.homerentalapp.Fragmentsadopter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.CalenderDate
import com.example.homerentalapp.Dataclass.DoctorDetailsModel
import com.example.homerentalapp.R
import com.google.android.material.button.MaterialButton

class Doctor_adopter(
    val context: Context,
    val Car: List<DoctorDetailsModel>,
    private var click: (Int, String) -> Unit
) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.doctorimage.setImageResource(R.drawable.doctor_image)
        holder.name.text = Car[position].name
        holder.hospital.text = Car[position].hospital
        holder.specility.text = Car[position].speciality
        holder.hospital_image.setImageResource(R.drawable.hp)
        holder.pkr.text = Car[position].pkr + " PKR"
        holder.years.text = Car[position].years + " years"
        holder.limit.text = "Under " + Car[position].limit + "  min "

        holder.card.setOnClickListener {
            click.invoke(position, "card")
        }

        holder.itemView.setOnClickListener {
            click.invoke(position, "position")
        }
    }

    override fun getItemCount(): Int {
        return Car.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var doctorimage = itemView.findViewById<ImageView>(R.id.image)
    var name = itemView.findViewById<TextView>(R.id.name)
    var hospital = itemView.findViewById<TextView>(R.id.hospital)
    var city = itemView.findViewById<TextView>(R.id.cetCity)
    var specility = itemView.findViewById<TextView>(R.id.speciality)
    var hospital_image = itemView.findViewById<ImageView>(R.id.hospital_image)
    var pkr = itemView.findViewById<TextView>(R.id.pkr)
    var years = itemView.findViewById<TextView>(R.id.years)
    var limit = itemView.findViewById<TextView>(R.id.limit)
    var card = itemView.findViewById<MaterialButton>(R.id.cardBtn)
}
package com.example.homerentalapp.Fragmentsadopter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.NurseModelclass
import com.example.homerentalapp.NurseActivity
import com.example.homerentalapp.R
import com.google.android.material.button.MaterialButton
import de.hdodenhof.circleimageview.CircleImageView

class NurseAdopter(
    val activity: Activity,
    val nurse: List<NurseModelclass>,
    private var click: (Int) -> Unit
) : RecyclerView.Adapter<Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.nurse, parent, false)
        return Holder(view)
    }


    override fun getItemCount(): Int {
        return nurse.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val nurse = nurse[position]
        holder.doctorimage.setImageResource(nurse.Image)
        holder.name.text = nurse.name
        holder.specility.text = nurse.Specility
        holder.fees.text = nurse.fees
        holder.year.text = nurse.years
        holder.time.text = nurse.limit

        holder.profile.setOnClickListener {
            click.invoke(position)
        }

        holder.appointment.setOnClickListener {
            Toast.makeText(activity, "Set Appointment with ${nurse.name}", Toast.LENGTH_SHORT).show()
        }
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val doctorimage = itemView.findViewById<CircleImageView>(R.id.image)
    val name = itemView.findViewById<TextView>(R.id.name)
    val specility = itemView.findViewById<TextView>(R.id.speciality)
    val fees = itemView.findViewById<TextView>(R.id.pkr)
    val year = itemView.findViewById<TextView>(R.id.years)
    val time = itemView.findViewById<TextView>(R.id.limit)
    val profile: MaterialButton = itemView.findViewById(R.id.btnProfile)
    val appointment: MaterialButton = itemView.findViewById(R.id.btnAppoint)
}
package com.example.homerentalapp.Fragmentsadopter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.AppointmentModelClass
import com.example.homerentalapp.R
import com.example.homerentalapp.appcontant.AppConstant
import de.hdodenhof.circleimageview.CircleImageView

class AppointmentAdopter(
    val context: Context,
    var Car: List<AppointmentModelClass>,
    private var click: (Int, String) -> Unit
) :
    RecyclerView.Adapter<UserHolder1>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder1 {
        var a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.appointment_item, parent, false)
        return UserHolder1(view)
    }

    override fun onBindViewHolder(holder: UserHolder1, position: Int) {
        Car[position].image?.let { holder.Carimage.setImageResource(it) }
        holder.Cartext.text = Car[position].name
        holder.Cartext1.text = Car[position].hospital
        holder.Phone.text = Car[position].time
        holder.date.text = Car[position].date
//        Car[position].image2?.let { holder.Phone.setImageResource(it) }
        holder.itemView.setOnClickListener {
            click.invoke(position, "item")
        }

        holder.image.setOnClickListener {
            click.invoke(position, "dial")
        }
    }

    override fun getItemCount(): Int {
        return Car.size
    }

//    fun filterList(filteredList: List<hos_item_user>) {
//        this.Car = filteredList
//        notifyDataSetChanged()
//    }
}

class UserHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var Carimage = itemView.findViewById<CircleImageView>(R.id.image)
    var Cartext = itemView.findViewById<TextView>(R.id.text1)
    var Cartext1 = itemView.findViewById<TextView>(R.id.text2)
    var ContactNo = itemView.findViewById<TextView>(R.id.ContactNo)
    var Phone = itemView.findViewById<TextView>(R.id.phonetxt)
    var date = itemView.findViewById<TextView>(R.id.calender)
    var image = itemView.findViewById<ImageView>(R.id.dial)
}
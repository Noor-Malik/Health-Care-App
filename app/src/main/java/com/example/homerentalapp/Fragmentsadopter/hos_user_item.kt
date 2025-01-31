package com.example.homerentalapp.Fragmentsadopter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.hos_item_user
import com.example.homerentalapp.R
import de.hdodenhof.circleimageview.CircleImageView

class hos_user_item(
    val context: Context,
    var Car: List<hos_item_user>,
    private var click: (Int) -> Unit
) :
    RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        var a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.user_hospital_item, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.Carimage.setImageResource(Car[position].image)
        holder.Cartext.text = Car[position].text
        holder.Cartext1.text = Car[position].text1
        holder.itemView.setOnClickListener {
            click.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return Car.size
    }

    fun filterList(filteredList: List<hos_item_user>) {
         this.Car = filteredList
        notifyDataSetChanged()
    }
}

class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var Carimage = itemView.findViewById<CircleImageView>(R.id.image)
    var Cartext = itemView.findViewById<TextView>(R.id.text2)
    var Cartext1 = itemView.findViewById<TextView>(R.id.text)
}
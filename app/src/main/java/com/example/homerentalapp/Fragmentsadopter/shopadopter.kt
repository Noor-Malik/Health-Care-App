package com.example.homerentalapp.Fragmentsadopter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.hos_item_user
import com.example.homerentalapp.Dataclass.shopData
import com.example.homerentalapp.R
import de.hdodenhof.circleimageview.CircleImageView

class shopadopterclass (
    val context: Context,
    var Car: List<shopData>,
    private var click: (Int) -> Unit
) :
RecyclerView.Adapter<UserHolders>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolders {
        var a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.item_shop, parent, false)
        return UserHolders(view)
    }

    override fun onBindViewHolder(holder: UserHolders, position: Int) {
        holder.image.setImageResource(Car[position].image)
        holder.text.text = Car[position].text

        holder.itemView.setOnClickListener {
            click.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return Car.size
    }

    fun submitList(toMutableList: MutableList<shopData>) {
        this.Car = toMutableList
        notifyDataSetChanged()
    }
}

class UserHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.image)
    var text = itemView.findViewById<TextView>(R.id.medicine)
}


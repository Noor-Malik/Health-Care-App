package com.example.homerentalapp.Fragmentsadopter

import android.annotation.SuppressLint
import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.data_specility_doc
import com.example.homerentalapp.Dataclass.productData
import com.example.homerentalapp.R
import de.hdodenhof.circleimageview.CircleImageView

class productAdopter(
    val context: Context,
    var Car: List<productData>,
    private var click: (Int) -> Unit
) : RecyclerView.Adapter<useHolder>() {

    var index = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): useHolder {
        var a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.item_product, parent, false)
        return useHolder(view)
    }

    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onBindViewHolder(holder: useHolder, position: Int) {
        holder.image.setImageResource(Car[position].image)
        holder.text1.text = Car[position].text1
        holder.text2.text = Car[position].text2
        holder.text3.text = Car[position].text3
        holder.text4.text = Car[position].text4

        holder.itemView.setOnClickListener {
            click.invoke(position)
        }

        holder.fav.setOnClickListener {
            if (index == 0) {
                index = 1
                holder.fav.setImageResource(R.drawable.baseline_favorite_border_24)
            } else {
                index = 0
                holder.fav.setImageResource(R.drawable.favourite)
            }
        }
    }

    override fun getItemCount(): Int {
        return Car.size
    }

    fun submitList(toMutableList: MutableList<productData>) {
        this.Car = toMutableList
        notifyDataSetChanged()
    }
}

class useHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image = itemView.findViewById<ImageView>(R.id.medicine2)
    var fav = itemView.findViewById<ImageView>(R.id.fav_product)
    var text1 = itemView.findViewById<TextView>(R.id.multi)
    var text2 = itemView.findViewById<TextView>(R.id.pills)
    var text3 = itemView.findViewById<TextView>(R.id.final_price_product)
    var text4 = itemView.findViewById<TextView>(R.id.final_product)
}

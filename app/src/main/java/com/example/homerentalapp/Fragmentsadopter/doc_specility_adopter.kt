package com.example.homerentalapp.Fragmentsadopter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homerentalapp.Dataclass.data_specility_doc
import com.example.homerentalapp.R
import de.hdodenhof.circleimageview.CircleImageView

class doc_specility_adopter(
    val context: Context,
    var Car: List<data_specility_doc>,
    private var click: (Int) -> Unit
) :
    RecyclerView.Adapter<useHolder1>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): useHolder1 {
        var a = LayoutInflater.from(parent.context)
        val view = a.inflate(R.layout.doctor_specility_item, parent, false)
        return useHolder1(view)
    }

    override fun onBindViewHolder(holder: useHolder1, position: Int) {
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

    fun filterList(filteredList: List<data_specility_doc>) {
        this.Car = filteredList
        notifyDataSetChanged()
    }
}

 class useHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var Carimage = itemView.findViewById<CircleImageView>(R.id.image)
    var Cartext = itemView.findViewById<TextView>(R.id.text2)
    var Cartext1 = itemView.findViewById<TextView>(R.id.text1)
}
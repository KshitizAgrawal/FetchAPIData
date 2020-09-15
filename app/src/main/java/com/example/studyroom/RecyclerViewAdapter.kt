package com.example.studyroom

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studyroom.Data

class RecyclerViewAdapter(val context: Context, var list: ArrayList<Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.textView)
        var tags: TextView = itemView.findViewById(R.id.textView1)
        var imgbtn: ImageView = itemView.findViewById(R.id.imageView)
        fun bind(position: Int) {
            val recyclerViewModel = list[position]
            title.text = recyclerViewModel.title
            tags.text = recyclerViewModel.tags
//            imgbtn.setImageBitmap(recyclerViewModel.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.itemview, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as ViewHolder).bind(position)

    }
}
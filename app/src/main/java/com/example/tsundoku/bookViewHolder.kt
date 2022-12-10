package com.example.tsundoku

import android.icu.text.CaseMap.Title
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val cardView: CardView = itemView.findViewById(R.id.root)
    val tvTitle: TextView = itemView.findViewById(R.id.ItemBookTitle)
    val tvAuthor: TextView = itemView.findViewById(R.id.ItemBookAuthor)
    val tvNotes: TextView = itemView.findViewById(R.id.ItemBookNotes)
    val deleteBook: ImageView = itemView.findViewById(R.id.deleteBook)
//    val editBook: ImageView = itemView.findViewById(R.id.editBook)

}
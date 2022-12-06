package com.example.tsundoku

import android.icu.text.CaseMap.Title
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val tvTitle: TextView = itemView.findViewById(R.id.bookTitle)
    val tvAuthor: TextView = itemView.findViewById(R.id.bookAuthor)
    val tvNotes: TextView = itemView.findViewById(R.id.bookNotes)
    val deleteBook: ImageView = itemView.findViewById(R.id.deleteBook)
    val editBook: ImageView = itemView.findViewById(R.id.editBook)

}
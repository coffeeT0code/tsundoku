package com.example.tsundoku

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class bookAdapter (
    private val bookTitles: List<String>
)   :RecyclerView.Adapter<bookViewHolder>(){

    override fun onBindViewHolder(holder: bookViewHolder, position: Int) {
        val bookTitle = bookTitles[position]
        holder.display(bookTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bookViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_main, parent, false)
        return bookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookTitles.count()
    }
}

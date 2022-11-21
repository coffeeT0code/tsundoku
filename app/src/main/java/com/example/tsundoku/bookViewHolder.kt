package com.example.tsundoku

import android.icu.text.CaseMap.Title
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class bookViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val textView: TextView

    init {
        textView = view as TextView
    }

    fun display(bookTitle: String) {
        textView.text = bookTitle
    }
}
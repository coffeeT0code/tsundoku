package com.example.tsundoku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView

class ViewBook : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book)
        val book = intent.getSerializableExtra("book") as Books
        val title: EditText = findViewById<EditText>(R.id.viewBookTitle)
        val author: EditText = findViewById(R.id.viewBookAuthor)
        val notes: EditText = findViewById(R.id.viewBookNotes)

        title.setText(book.title)
        author.setText(book.author)
        notes.setText(book.notes)


    }



}
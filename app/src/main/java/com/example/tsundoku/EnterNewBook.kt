package com.example.tsundoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class EnterNewBook : AppCompatActivity() {

    private val myDb = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_new_book)

        findViewById<Button>(R.id.saveButton).setOnClickListener() {
            saveBook(it)
        }
    }

    fun saveBook(view: View){
        val myIntent = Intent(this, MainActivity::class.java)

        val bookTitle = findViewById<EditText>(R.id.bookTitle).text.toString()
        val bookAuthor = findViewById<EditText>(R.id.bookAuthor).text.toString()
        val bookNotes = findViewById<EditText>(R.id.bookNotes).text.toString()
        myIntent.putExtra("bookTitle", bookTitle)
        myIntent.putExtra("bookAuthor", bookAuthor)
        myIntent.putExtra("bookNotes", bookNotes)
        myDb.insertBook(bookTitle, bookAuthor, bookNotes)

        startActivity(myIntent)
    }
}
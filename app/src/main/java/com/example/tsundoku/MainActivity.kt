package com.example.tsundoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val myDb = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var allBooks: MutableList<String> = mutableListOf()
        allBooks = myDb.getAllBooks()

        findViewById<TextView>(R.id.textView).apply{
            text = allBooks.toString()
        }
    }



    fun enterNewBook(view: View){
        val myIntent = Intent(this, EnterNewBook::class.java)

        startActivity(myIntent)
    }
}


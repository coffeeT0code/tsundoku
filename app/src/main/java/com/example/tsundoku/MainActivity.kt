package com.example.tsundoku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var myDb: DatabaseHandler
    lateinit var bookList: MutableList<Books>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Tsundoku"
        val bookView: RecyclerView = findViewById(R.id.myBookList)
        val linearLayoutManager = LinearLayoutManager(this)
        bookView.layoutManager = linearLayoutManager
        bookView.setHasFixedSize(true)
        myDb = DatabaseHandler(this)
        bookList = myDb.getAllBooks().toMutableList()

        if (bookList.size > 0) {
            bookView.visibility = View.VISIBLE
            val myAdapter = BookAdapter(this, bookList)
            bookView.adapter = myAdapter
        } else {
            bookView.visibility = View.GONE
            Toast.makeText(
                this,
                "there is no book in this library",
                Toast.LENGTH_LONG
            ).show()
        }

        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener { enterNewBook() }
    }

    private fun enterNewBook() {
        val myIntent = Intent(this, EnterNewBook::class.java)
        startActivity(myIntent)
    }
}


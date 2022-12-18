package com.example.tsundoku

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewCacheExtension
import java.io.Serializable
import java.util.Collections

class MainActivity : AppCompatActivity() {

    private lateinit var myDb: DatabaseHandler
    lateinit var bookList: MutableList<Books>
    lateinit var bookView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Tsundoku"
        myDb = DatabaseHandler(this)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener { enterNewBook() }

        checkBoxFilter()

    }

    override fun onResume() {
        super.onResume()

        bookList = myDb.getAllBooks().toMutableList()

        bookView = findViewById(R.id.myBookList)
        val linearLayoutManager = LinearLayoutManager(this)
        bookView.layoutManager = linearLayoutManager
        bookView.setHasFixedSize(true)

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
        checkBoxFilter()

    }


    private fun enterNewBook() {
        val myIntent = Intent(this, EnterNewBook::class.java)
        startActivity(myIntent)
    }

    private fun checkBoxFilter() {
        val checkCurrentlyReading: CheckBox = findViewById(R.id.checkCurrentlyReading)
        val checkFinished: CheckBox = findViewById(R.id.checkFinished)
        val checkTBR: CheckBox = findViewById(R.id.checkTBR)

        checkCurrentlyReading.setOnClickListener() { _, isChecked ->
                    bookList = myDb.getCurrentlyReadingBooks().toMutableList()
        }

        checkFinished.setOnClickListener() { _, isChecked ->
                    bookList = myDb.getFinishedBooks().toMutableList()
        }

        checkTBR.setOnClickListener() { _, isChecked ->
                    bookList = myDb.getTBRBooks().toMutableList()
        }
    }
}

private fun CheckBox.setOnClickListener(function: (View, Any?) -> Unit) {

}


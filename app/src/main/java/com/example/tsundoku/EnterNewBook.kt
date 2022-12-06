package com.example.tsundoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class EnterNewBook : AppCompatActivity() {

    private val myDb = DatabaseHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_new_book)
        findViewById<Button>(R.id.saveButton).setOnClickListener() {
            saveBook(it)
        }
    }

    private fun saveBook(view: View){
        val myIntent = Intent(this, MainActivity::class.java)

        val bookTitleField = findViewById<TextView>(R.id.bookTitle).text.toString()
        val bookAuthorField = findViewById<TextView>(R.id.bookAuthor).text.toString()
        val bookNotesField = findViewById<TextView>(R.id.bookNotes).text.toString()

        myDb.addBooks(bookTitleField, bookAuthorField, bookNotesField)
        startActivity(myIntent)

//        if (TextUtils.isEmpty(bookTitleField)) {
//                Toast.makeText(
//                    this@EnterNewBook,
//                    "Something went wrong",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//            else {
//                myDb.addBooks(bookTitle, bookAuthor, bookNotes)
//                finish()
//                startActivity(myIntent)
//            }
//        }

//            Toast.makeText(this@EnterNewBook, "Task cancelled",
//            Toast.LENGTH_LONG).show()}
}

    override fun onDestroy() {
        super.onDestroy()
        myDb.close()
    }
}

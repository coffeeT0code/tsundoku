package com.example.tsundoku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class EnterNewBook : AppCompatActivity() {

    private val myDb = DatabaseHandler(this)
    var state = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_new_book)

        val spinner: Spinner = findViewById(R.id.enterSpinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.StateArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                    state = spinner.getItemAtPosition(position).toString()

//                    Toast.makeText(applicationContext, "selected state ="+R.array.StateArray[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }



        findViewById<Button>(R.id.saveButton).setOnClickListener() {
            saveBook(it)
        }
    }

    private fun saveBook(view: View){
        val myIntent = Intent(this, MainActivity::class.java)

        val bookTitleField = findViewById<TextView>(R.id.bookTitle).text.toString()
        val bookAuthorField = findViewById<TextView>(R.id.bookAuthor).text.toString()
        val bookNotesField = findViewById<TextView>(R.id.bookNotes).text.toString()
        val ratingBar = findViewById<RatingBar>(R.id.rBar).rating.toInt()

        myDb.addBooks(bookTitleField, bookAuthorField, bookNotesField, state, ratingBar)
        startActivity(myIntent)

//        if (TextUtils.isEmpty(bookTitleField)) {
//                Toast.makeText(
//                    this@EnterNewBook,
//                    "Something went wrong",
//                    Toast.LENGTH_LONG
//               ).show()
//            }
//            else {
//                myDb.addBooks(title, author, notes)
//                finish()
//                startActivity(myIntent)
//            }
//        }
//
//            Toast.makeText(this@EnterNewBook, "Task cancelled",
//            Toast.LENGTH_LONG).show()}
}

    override fun onDestroy() {
        super.onDestroy()
        myDb.close()
    }
}

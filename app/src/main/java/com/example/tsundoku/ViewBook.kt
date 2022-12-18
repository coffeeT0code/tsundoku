package com.example.tsundoku

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ViewBook : AppCompatActivity() {

    private val myDb = DatabaseHandler(this)
    private var id = 0
    private  var state = "null"
    private var editActive = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_book)

        val book = intent.getSerializableExtra("book") as Books
        id = book.id
        state = book.state

        val title: EditText = findViewById<EditText>(R.id.viewBookTitle)
        val author: EditText = findViewById(R.id.viewBookAuthor)
        val notes: EditText = findViewById(R.id.viewBookNotes)
        val edit: ImageButton = findViewById(R.id.BtnEditBook)
        val save: Button = findViewById(R.id.BtnSaveEdit)
        val spinner: Spinner = findViewById(R.id.spinner)
        val ratingBar: RatingBar = findViewById(R.id.ratingBarView)
        val editTexts = arrayListOf(title, author, notes, spinner, ratingBar)

        title.setText(book.title)
        author.setText(book.author)
        notes.setText(book.notes)
        val compareValue = state.toString()
        spinner.isEnabled = false;
        spinner.isClickable = false;
        ratingBar.rating = book.rating.toFloat()
        ratingBar.isClickable = false
        ratingBar.isEnabled = false

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

            if (compareValue != null) {
                val spinnerPosition = adapter.getPosition(compareValue)
                spinner.setSelection(spinnerPosition)
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                    // makes a string out of the position
                    state = spinner.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }


        edit.setOnClickListener() {
            if (!editActive) {
                for (element in editTexts) {
                    element.isEnabled = true
                    element.isClickable = true
                }
                save.visibility = View.VISIBLE
                editActive = true
            } else {
                for (element in editTexts) {
                    element.isEnabled = false
                    spinner.isClickable = false
                }
                save.visibility = View.INVISIBLE
                editActive = false
            }
        }
        save.setOnClickListener() {
           updateBook(it)
        }
    }

    private fun updateBook(view: View) {
        val myIntent = Intent(this, MainActivity::class.java)

        val bookTitleField = findViewById<EditText>(R.id.viewBookTitle).text.toString()
        val bookAuthorField = findViewById<EditText>(R.id.viewBookAuthor).text.toString()
        val bookNotesField = findViewById<EditText>(R.id.viewBookNotes).text.toString()
        val ratingBar = findViewById<RatingBar>(R.id.ratingBarView).rating.toInt()

        myDb.updateBooks(id, bookTitleField, bookAuthorField, bookNotesField, state, ratingBar)
        startActivity(myIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        myDb.close()
    }


}

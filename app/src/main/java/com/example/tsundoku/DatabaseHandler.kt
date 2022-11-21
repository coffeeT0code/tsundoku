package com.example.tsundoku

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, dbName, null, 1) {
    companion object DataConfig {
        private const val dbName:String = "enterBook"
        private const val tableName = "books"
        private const val id = "_id"
        private const val title = "title"
        private const val titleSize = "100"
        private const val author = "author"
        private const val authorSize = "100"
        private const val notes = "notes"
        private const val notesSize = "500"

        val allBooks: MutableList<String> = mutableListOf()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS " +
                "$tableName " +
                "($id INTEGER PRIMARY KEY, " +
                "$title VARCHAR($titleSize), " +
                "$author VARCHAR($authorSize), " +
                "$notes VARCHAR($notesSize));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tableName;")
        onCreate(db)
    }

    fun insertBook(bookTitle: String, bookAuthor: String, bookNotes: String) {
        val db = this.writableDatabase
        val ctv = ContentValues()
        ctv.put("title", bookTitle)
        ctv.put("author", bookAuthor)
        ctv.put("notes", bookNotes)
        db.insert(tableName, null, ctv)
    }

    fun getAllBooks(): MutableList<String> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $tableName", null)

        while (cursor.moveToNext()) {
            val index = cursor.getColumnIndex("title")
            if (index >= 0)
                allBooks.add(cursor.getString(index))
        }
        return allBooks
    }


}
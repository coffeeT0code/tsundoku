package com.example.tsundoku

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context, dbName, null, 1) {

    companion object DataConfig {
        private const val dbName:String = "enterBook"
        private const val tableName = "books"
        private const val idColumnName = "_id"
        private const val title = "title"
        private const val titleSize = "100"
        private const val author = "author"
        private const val authorSize = "100"
        private const val notes = "notes"
        private const val notesSize = "500"
        private const val state = "state"
        private const val stateSize = "100"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS " +
                "$tableName " +
                "($idColumnName INTEGER PRIMARY KEY, " +
                "$title VARCHAR($titleSize), " +
                "$author VARCHAR($authorSize), " +
                "$notes VARCHAR($notesSize), " +
                "$state VARCHAR($stateSize));")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $tableName;")
        onCreate(db)
    }

//    fun listBooks(): MutableList<Books> {
//        val sql = "SELECT * FROM $tableName"
//        val db = this.readableDatabase
//        val storeBooks = ArrayList<Books>()
//        val cursor = db.rawQuery(sql, null)
//
//        if (cursor.moveToFirst()) {
//            do {
//                val id = cursor.getString(0).toInt()
//                val bookTitle = cursor.getString(1)
//                val bookAuthor = cursor.getString(2)
//                val bookNotes = cursor.getString(3)
//                storeBooks.add(Books(id, bookTitle, bookAuthor, bookNotes))
//            }
//            while (cursor.moveToNext())
//
//        }
//        cursor.close()
//        return storeBooks
//    }

    // get list of all notes
    fun getAllBooks(): List<Books>  {
        val db = this.readableDatabase
        val allBooks = mutableListOf<Books>()

        // get all entries from table and put it into the list
        db.rawQuery("SELECT * FROM $tableName",null).use {
            while(it.moveToNext()){
                allBooks.add(
                    Books(
                        id = it.getInt(it.getColumnIndex(DataConfig.idColumnName) as Int),
                        title = it.getString(it.getColumnIndex(DataConfig.title) as Int),
                        author = it.getString(it.getColumnIndex(DataConfig.author) as Int),
                        notes = it.getString(it.getColumnIndex(DataConfig.notes) as Int),
                        state = it.getString(it.getColumnIndex(DataConfig.state) as Int)
                    )
                )
            }
        }
        return allBooks
    }

    fun addBooks(title: String, author: String, notes: String, state: String) {
        val db = this.writableDatabase
        val ctv = ContentValues()
        ctv.put("title", title)
        ctv.put("author", author)
        ctv.put("notes", notes)
        ctv.put("state", state)
        db.insert(tableName, null, ctv)
    }

    fun updateBooks(id: Int, title: String, author: String, notes: String, state: String) {
        val db = this.writableDatabase
        val ctv = ContentValues()
        ctv.put("title", title)
        ctv.put("author", author)
        ctv.put("notes", notes)
        ctv.put("state", state)
        db.update(
            tableName, ctv, "$idColumnName = ?", arrayOf(id.toString())
        )
    }

    fun deleteBooks(id: Int) {
        val db = this.writableDatabase
        db.delete(
            tableName,"$idColumnName = ?", arrayOf(id.toString())
        )
    }
}
package com.example.tsundoku

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import java.util.Objects

internal class BookAdapter (
    private val context: Context, listBooks: MutableList<Books>
)   :RecyclerView.Adapter<BookViewHolder>() {
    private var listBooks: MutableList<Books>
    private var mMutableList: MutableList<Books>
    private var myDb: DatabaseHandler

    init {
        this.listBooks = listBooks
        this.mMutableList = listBooks
        myDb = DatabaseHandler(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.book_list_layout, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        val book = listBooks[position]
        holder.tvTitle.text = book.title
        holder.tvAuthor.text = book.author
//        holder.tvNotes.text = book.notes
        holder.deleteBook.setOnClickListener {
            myDb.deleteBooks(book.id)
            listBooks.removeAt(position)
            notifyItemRemoved(position)
        }
        holder.cardView.setOnClickListener {
            val myIntent = Intent(context, ViewBook::class.java)
            myIntent.putExtra("book", book)
            context.startActivity(myIntent)
        }
    }

    override fun getItemCount(): Int {
        return listBooks.count()
    }

}

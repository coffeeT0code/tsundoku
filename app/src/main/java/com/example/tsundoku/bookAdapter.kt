package com.example.tsundoku

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.ContactsContract.Data
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        val books = listBooks[position]
        holder.tvTitle.text = books.title
        holder.tvAuthor.text = books.author
        holder.tvNotes.text = books.notes
        holder.deleteBook.setOnClickListener {
            myDb.deleteBooks(books.id)
            (context as Activity).finish()
            context.startActivity(context.intent)
        }
    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence?): FilterResults {
//                val charString = charSequence.toString()
//                listBooks = if (charString.isEmpty()) {
//                    mMutableList
//                } else {
//                    val filteredList = MutableList<Books>()
//                    for (books in mMutableList) {
//                        if (books.title.toLowerCase().contains(charString)) {
//                            filteredList.add(books)
//                        }
//                    }
//                    filteredList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = listBooks
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
//                listBooks = filterResults.values as MutableList<Books>
//                notifyDataSetChanged()
//            }
//        }
//    }

    override fun getItemCount(): Int {
        return listBooks.count()
    }

}

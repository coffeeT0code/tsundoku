package com.example.tsundoku

//class Books {
//    var id = 0
//    lateinit var title: String
//    lateinit var author: String
//    lateinit var notes: String
//
//    internal constructor(title: String, author: String, notes: String) {
//        this.title = title
//        this.author = author
//        this.notes = notes
//    }
//    internal constructor(id: Int, title: String, author: String, notes: String) {
//        this.id = id
//        this.title = title
//        this.notes = notes
//    }
//}

data class Books(val id :Int, val title :String, val author :String, val notes :String) {

}
package com.example.tsundoku

import android.os.Parcelable
import java.io.Serializable

data class Books(val id: Int, val title: String, val author: String, val notes: String, val state: String)
    : Serializable


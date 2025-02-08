package com.kankinara.notesapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["title"])
class Note(val title: String, val description: String) {
}
package com.kankinara.notesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kankinara.notesapplication.model.Note
import com.kankinara.notesapplication.room.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val noteDao: NoteDao):ViewModel() {

    val allNotes :LiveData<List<Note>> = noteDao.getAllNotes()

    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteDao.insert(note)
        }
    }
}
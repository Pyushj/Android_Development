package com.kankinara.notesapplication.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kankinara.notesapplication.model.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(note: Note)

    @Update
     fun update(note: Note)

    @Delete
     fun delete(note: Note)

    @Query("SELECT * FROM Note")
    fun getAllNotes(): LiveData<List<Note>>

}
package com.kankinara.notesapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kankinara.notesapplication.model.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, "app_database.db"
                )
                    .build()

                INSTANCE = instance

                instance

            }
        }

    }
}
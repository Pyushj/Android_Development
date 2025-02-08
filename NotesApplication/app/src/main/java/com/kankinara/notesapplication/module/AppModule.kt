package com.kankinara.notesapplication.module

import androidx.room.RoomDatabase
import com.kankinara.notesapplication.room.AppDataBase
import com.kankinara.notesapplication.viewmodel.NotesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<RoomDatabase> {AppDataBase.getInstance(get()) }
    single { AppDataBase.getInstance(get()) }

    single { get<AppDataBase>().noteDao }

    viewModel { NotesViewModel(get()) }
}
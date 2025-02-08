package com.kankinara.notesapplication.view

import android.os.Bundle

class NoteListActivity:BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(NoteListFragment())
    }
}
package com.kankinara.notesapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kankinara.notesapplication.R
import com.kankinara.notesapplication.databinding.ItemNoteBinding
import com.kankinara.notesapplication.model.Note

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.NoteViewHolder>() {

    private val noteList = mutableListOf<Note>()

    fun setNote(list: List<Note>) {
        noteList.clear()
        noteList.addAll(list)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNoteBinding>(inflater, R.layout.item_note,parent,false)
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val binding = holder.binding
        val note = noteList[position]

        binding.title.text = note.title
        binding.description.text = note.description

    }
}
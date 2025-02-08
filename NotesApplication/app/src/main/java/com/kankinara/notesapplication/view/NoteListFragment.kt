package com.kankinara.notesapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kankinara.notesapplication.adapter.NoteListAdapter
import com.kankinara.notesapplication.databinding.FragmentNoteListBinding
import com.kankinara.notesapplication.model.Note
import com.kankinara.notesapplication.viewmodel.NotesViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NoteListFragment : Fragment() {
    private lateinit var binding: FragmentNoteListBinding
    private lateinit var adapter: NoteListAdapter
    private val viewModel: NotesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        binding.fragment = this@NoteListFragment
        binding.lifecycleOwner = this@NoteListFragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //init observers
        viewModel.allNotes.observe(viewLifecycleOwner) {
            adapter.setNote(it)
        }

        //init recycler view
        adapter = NoteListAdapter()
        binding.notesContainer.adapter = adapter
    }

    fun onClickAdd() {
        viewModel.addNote(Note("Complete the app","This is just a remainder to complete the app today"))
    }
}
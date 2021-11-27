package com.mariotorrese.app_notas.ui.notes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mariotorrese.app_notas.R
import com.mariotorrese.app_notas.core.Resource
import com.mariotorrese.app_notas.data.local.AppDataBase
import com.mariotorrese.app_notas.data.local.LocalDataSource
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.remote.ApiClientD
import com.mariotorrese.app_notas.data.remote.FirebaseService
import com.mariotorrese.app_notas.data.remote.NoteDataSource
import com.mariotorrese.app_notas.databinding.FragmentNoteBinding
import com.mariotorrese.app_notas.presentation.NoteViewModel
import com.mariotorrese.app_notas.presentation.NoteViewModelFactory
import com.mariotorrese.app_notas.repository.NoteRepositoryImp
import com.mariotorrese.app_notas.ui.notes.adapters.NoteAdapter


class NoteFragment : Fragment(R.layout.fragment_note) {
    private lateinit var binding:FragmentNoteBinding
    private lateinit var adapter:NoteAdapter

    private val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(NoteRepositoryImp(
            LocalDataSource(AppDataBase.getDataBase(this.requireContext()).noteDao()),
            NoteDataSource(FirebaseService())))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteBinding.bind(view)

        binding.recyclerNotes.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.btnAddNote.setOnClickListener {
            val action = NoteFragmentDirections.actionNoteFragmentToNoteEditFragment()
            findNavController().navigate(action)
        }

        viewModel.fetchNotes().observe(viewLifecycleOwner, { result ->
            when(result){
                is Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    binding.progressbar.visibility = View.GONE
                    adapter = NoteAdapter(result.data.data){ note ->
                        onNoteClick(note)
                    }
                    binding.recyclerNotes.adapter = adapter
                    Log.d("LiveData","${result.data.toString()}")
                }
                is Resource.Failure -> {
                    binding.progressbar.visibility = View.GONE
                    Log.d("LiveData","${result.exception.toString()}")
                }
            }
        })
    }

    private fun onNoteClick(note: Note){
        val action = NoteFragmentDirections.actionNoteFragmentToNoteDetailFragment(
            note.id,
            note.title,
            note.content,
            note.image
        )
        findNavController().navigate(action)
    }
}
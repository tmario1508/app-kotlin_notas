package com.mariotorrese.app_notas.ui.notesdetails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mariotorrese.app_notas.R
import com.mariotorrese.app_notas.core.Resource
import com.mariotorrese.app_notas.data.local.AppDataBase
import com.mariotorrese.app_notas.data.local.LocalDataSource
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.remote.ApiClientD
import com.mariotorrese.app_notas.data.remote.NoteDataSource
import com.mariotorrese.app_notas.databinding.FragmentNoteEditBinding
import com.mariotorrese.app_notas.presentation.NoteViewModel
import com.mariotorrese.app_notas.presentation.NoteViewModelFactory
import com.mariotorrese.app_notas.repository.NoteRepositoryImp
import com.mariotorrese.app_notas.ui.notes.adapters.NoteAdapter


class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {
    private  lateinit var binding: FragmentNoteEditBinding

    private  val viewModel by viewModels<NoteViewModel> {
        NoteViewModelFactory(NoteRepositoryImp(
            LocalDataSource(AppDataBase.getDataBase(this.requireContext()).noteDao()),
            NoteDataSource(ApiClientD.service)))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteEditBinding.bind(view)

        binding.btnAddNote.setOnClickListener {
            var note = Note(
                0,
                binding.editTitle.text.toString(),
                binding.editContent.text.toString(),
                binding.editImageUrl.text.toString()
            )

            viewModel.saveNote(note).observe(viewLifecycleOwner, {result ->
                when(result) {
                    is Resource.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressbar.visibility = View.GONE
                        findNavController().popBackStack()
                    }
                    is Resource.Failure -> {
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(requireContext(), "${result.exception.toString()}", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }



}
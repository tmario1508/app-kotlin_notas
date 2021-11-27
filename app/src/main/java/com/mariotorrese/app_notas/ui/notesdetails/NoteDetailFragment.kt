package com.mariotorrese.app_notas.ui.notesdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mariotorrese.app_notas.R
import com.mariotorrese.app_notas.databinding.FragmentNoteDetailBinding
import com.squareup.picasso.Picasso


class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {

    private lateinit var binding: FragmentNoteDetailBinding
    private val args by navArgs<NoteDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteDetailBinding.bind(view)

        binding.textTitle.text = args.title
        binding.textContent.text = args.content
        Picasso.get().load(args.image).into(binding.imgNote)
    }
}
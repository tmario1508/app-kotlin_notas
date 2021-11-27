package com.mariotorrese.app_notas.repository

import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList

interface NoteRepository {
    suspend fun getNotes(): NoteList
    suspend fun saveNote(note: Note?) : Note?
}
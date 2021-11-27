package com.mariotorrese.app_notas.data.local

import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteEntity
import com.mariotorrese.app_notas.data.model.NoteList
import com.mariotorrese.app_notas.data.model.toNoteList

class LocalDataSource( private val noteDao: NoteDao) {

    suspend fun getNotes(): NoteList = noteDao.getNotes().toNoteList()

    suspend fun saveNote(note: NoteEntity) = noteDao.saveNote(note)
}
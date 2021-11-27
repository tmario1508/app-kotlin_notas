package com.mariotorrese.app_notas.repository

import com.mariotorrese.app_notas.data.local.LocalDataSource
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList
import com.mariotorrese.app_notas.data.remote.NoteDataSource
import com.mariotorrese.app_notas.data.model.toNoteEntity

class NoteRepositoryImp(
    private val localDataSource: LocalDataSource,
    private val dataSource:NoteDataSource) : NoteRepository {

    override suspend fun getNotes(): NoteList {
        dataSource.getNotes().data.forEach{
                note -> localDataSource.saveNote(note.toNoteEntity())
        }
        return localDataSource.getNotes()
    }

    override suspend fun saveNote(note: Note?): Note? {
        return  dataSource.saveNote(note)
    }


}
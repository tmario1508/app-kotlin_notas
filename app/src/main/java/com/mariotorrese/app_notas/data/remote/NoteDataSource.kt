package com.mariotorrese.app_notas.data.remote

import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList

class NoteDataSource (private  val apiService: ApiService) {
    /*suspend fun getNotes(): NoteList
    {
        return  apiService.getNotes()
    }*/

    suspend fun getNotes(): NoteList = apiService.getNotes()

    suspend fun saveNote(note:Note?) : Note? = apiService.saveNote(note)
}
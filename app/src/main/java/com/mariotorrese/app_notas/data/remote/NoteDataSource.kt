package com.mariotorrese.app_notas.data.remote

import com.mariotorrese.app_notas.data.extensions.toNoteList
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList
import kotlinx.coroutines.tasks.await

//class NoteDataSource (private  val apiService: ApiService) {
class NoteDataSource (private val firebase:FirebaseService){
    /*suspend fun getNotes(): NoteList
    {
        return  apiService.getNotes()
    }*/

    //suspend fun getNotes(): NoteList = apiService.getNotes()
    suspend fun getNotes(): NoteList = firebase.notesRef().get().await().toNoteList()

    //suspend fun saveNote(note:Note?) : Note? = apiService.saveNote(note)
    suspend fun saveNote(note:Note?) : Note? {
        note.let {
            firebase.noteRef().set(note!!).await()
        }
        return note
    }
}
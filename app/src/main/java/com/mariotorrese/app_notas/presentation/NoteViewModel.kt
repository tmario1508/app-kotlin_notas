package com.mariotorrese.app_notas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.mariotorrese.app_notas.core.Resource
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.repository.NoteRepository
import kotlinx.coroutines.Dispatchers

class NoteViewModel(private  val repository: NoteRepository) : ViewModel() {
    fun fetchNotes() = liveData(Dispatchers.IO){

        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getNotes()))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }

    fun saveNote(note: Note?) = liveData(Dispatchers.IO){

        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.saveNote(note)))
        }catch (exception:Exception){
            emit(Resource.Failure(exception))
        }
    }
}

class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NoteRepository::class.java).newInstance(repository)
    }
}

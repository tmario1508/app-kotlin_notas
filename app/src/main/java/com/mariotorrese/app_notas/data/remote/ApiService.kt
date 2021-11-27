package com.mariotorrese.app_notas.data.remote

import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    //	https://testapi.io/api/mte616045/resource/notas
    @GET("notas")
    suspend fun getNotes(): NoteList

    @POST("notas")
    suspend fun saveNote(@Body note: Note?) : Note?
}
package com.mariotorrese.app_notas.data.local

import  androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mariotorrese.app_notas.data.model.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM noteEntity")
    suspend fun getNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note:NoteEntity)
}
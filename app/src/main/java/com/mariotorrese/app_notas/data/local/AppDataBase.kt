package com.mariotorrese.app_notas.data.local

import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mariotorrese.app_notas.data.model.NoteEntity


@Database(entities = [NoteEntity::class], version = 1)

abstract class AppDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var INSTANCE: AppDataBase? = null

        fun getDataBase(context: Context) : AppDataBase {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "noteapp1"
            ).build()
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
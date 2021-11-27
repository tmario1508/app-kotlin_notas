package com.mariotorrese.app_notas.data.extensions

import com.google.firebase.firestore.QuerySnapshot
import com.mariotorrese.app_notas.data.model.Note
import com.mariotorrese.app_notas.data.model.NoteList

fun QuerySnapshot.toNoteList(): NoteList {
    var list = mutableListOf<Note>()

    for (document in this){
        var note = document.toObject(Note::class.java)
        list.add(note)
    }

    return  NoteList(list)
}
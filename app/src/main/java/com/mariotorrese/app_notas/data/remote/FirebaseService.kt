package com.mariotorrese.app_notas.data.remote

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseService {
    fun collectionRef(collection:String) = FirebaseFirestore.getInstance().collection(collection)
    fun notesRef() = collectionRef("notes")
    fun noteRef() = notesRef().document()
}
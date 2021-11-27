package com.mariotorrese.app_notas.data.model

import com.google.firebase.firestore.DocumentId

data class Note (
    //val id:Int = 0,
    @DocumentId
    val id:String = "",
    val title:String = "",
    val content:String = "",
    val image:String = ""
)
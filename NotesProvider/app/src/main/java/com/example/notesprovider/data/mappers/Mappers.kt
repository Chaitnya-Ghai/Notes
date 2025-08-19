package com.example.notesprovider.data.mappers

import com.example.notesprovider.data.local.NoteEntity
import com.example.notesprovider.domain.model.Note

fun Note.toNoteEntity():NoteEntity{
    return NoteEntity(id,title,desc)
}
fun NoteEntity.toNote():Note{
    return Note (id,title,desc)
}
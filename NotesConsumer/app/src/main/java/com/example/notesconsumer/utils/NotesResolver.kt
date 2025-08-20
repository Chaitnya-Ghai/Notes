package com.example.notesconsumer.utils

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import com.example.notesconsumer.Note

const val AUTHORITY = "chaitnya.ghai.notes.provider"
const val NOTES_TABLE = "notes"
const val URL = "content://$AUTHORITY/$NOTES_TABLE"
val CONTENT_URI = URL.toUri()

fun getAllNotes(context: Context): List<Note>{
    val list = mutableListOf<Note>()
    val cursor = context.contentResolver.query(
        CONTENT_URI,
        null,
        null,
        null,
        null
    )
    cursor?.let {
        while (it.moveToNext()){
            val id = it.getInt(it.getColumnIndexOrThrow("id"))
            val title = it.getString(it.getColumnIndexOrThrow("title"))
            val desc = it.getString(it.getColumnIndexOrThrow("desc"))
            val note = Note(id, title, desc)
            list.add(note)
        }
        it.close()
    }
    return list
}
fun ContentResolver.insertNotes(
    title: String,
    desc: String,
){
    val values = ContentValues().apply {
        put("title",title)
        put("desc",desc)
    }

    insert(CONTENT_URI,values)
}

fun ContentResolver.updateNote(
    id: Int,
    title: String,
    desc: String,
){
    val values = ContentValues().apply {
        put("title",title)
        put("desc",desc)
    }
    val updateUri = "content://$AUTHORITY/$NOTES_TABLE/$id".toUri()
    update(updateUri,values,null,null)
}

fun ContentResolver.deleteNote(
    id: Int,
) {
    val deleteUri = "content://$AUTHORITY/$NOTES_TABLE/$id".toUri()
    delete(deleteUri, null, null)
}
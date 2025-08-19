package com.example.notesprovider.domain.repository

import com.example.notesprovider.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(note: Note)
    fun getAllNotes(): Flow<List<Note>>
}
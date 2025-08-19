package com.example.notesprovider.domain.use_case

import com.example.notesprovider.domain.repository.NotesRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    operator fun invoke() = notesRepository.getAllNotes()
}
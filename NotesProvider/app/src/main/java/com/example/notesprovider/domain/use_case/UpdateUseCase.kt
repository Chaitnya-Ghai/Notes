package com.example.notesprovider.domain.use_case

import com.example.notesprovider.domain.model.Note
import com.example.notesprovider.domain.repository.NotesRepository
import javax.inject.Inject

class UpdateUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) = notesRepository.update(note)
}
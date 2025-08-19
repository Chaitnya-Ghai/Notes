package com.example.notesprovider.di

import android.content.Context
import com.example.notesprovider.data.local.NotesDao
import com.example.notesprovider.data.local.NotesDatabase
import com.example.notesprovider.data.repository.NotesRepoImpl
import com.example.notesprovider.domain.repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NotesDataModule {


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return NotesDatabase.getInstance(context)
    }

    @Provides
    fun provideNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.getNotesDao()
    }

    @Provides
    fun provideRepository(notesDao: NotesDao): NotesRepository {
        return NotesRepoImpl(notesDao)
    }

}
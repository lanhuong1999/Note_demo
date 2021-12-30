package com.demo.note_demo.database.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.demo.note_demo.database.NoteDatabase
import com.demo.note_demo.database.dao.NoteDao
import com.demo.note_demo.model.Note

class NoteRepository(app:Application) {
    private val noteDao: NoteDao
    init {
        val noteDatabase:NoteDatabase=NoteDatabase.getInstance(app)
        noteDao=noteDatabase.getNoteDao()
    }
    suspend fun insertNote(note:Note) = noteDao.insertNote(note)
    suspend fun updateNote(note:Note) = noteDao.updateNote(note)
    suspend fun deletetNote(note:Note) = noteDao.deleteNote(note)

    fun getAllNote():LiveData<List<Note>> = noteDao.getAllNote()

}
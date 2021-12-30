package com.demo.note_demo.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.demo.note_demo.database.repository.NoteRepository
import com.demo.note_demo.model.Note
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {

    private val noteRepository: NoteRepository = NoteRepository(application)

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun delenote(note: Note) = viewModelScope.launch {
        noteRepository.deletetNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    class factoryNoteViewModel(private val application: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
                return NoteViewModel(application = application) as T
            }
            throw IllegalAccessException("Unable construct viewmodel")
        }

    }
}
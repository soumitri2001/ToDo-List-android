package com.appdev_soumitri.todolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:NoteRepository
    val allNotes:LiveData<List<Note>>

    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repository=NoteRepository(dao)
        allNotes=repository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        // use of coroutines instead of async task
        repository.delete(note) // new thread in background where deletion is worked
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}
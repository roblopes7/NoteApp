package com.udemy.noteapp.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udemy.noteapp.model.Note
import com.udemy.noteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel()  {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow();
    //private var noteList = mutableStateListOf<Note>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository
                .getAllNotes()
                .distinctUntilChanged()
                .collect { listOfNotes ->
                    if(listOfNotes.isEmpty()){
                        Log.d("Empty", ": Emptey list")
                    }else {
                        _noteList.value = listOfNotes
                    }
                }
        }
        //noteList.addAll(NoteDataSource().loadNotes())
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(note: Note) = viewModelScope.launch { repository.updateNote(note) }
    fun deleteNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}
package com.example.noteapplication

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {

    val allNotes:LiveData<List<Note>> =noteDao.getAllNotes()

     fun insert(note: Note){
        noteDao.insert(note)
    }

     fun update(note: Note){
        noteDao.update(note)
    }

     fun delete(note: Note){
        noteDao.delete(note)
    }

}
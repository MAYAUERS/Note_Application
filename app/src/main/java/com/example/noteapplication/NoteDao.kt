package com.example.noteapplication

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
     fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes():LiveData<List<Note>>

}
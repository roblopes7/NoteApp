package com.udemy.noteapp.data

import androidx.room.*
import com.udemy.noteapp.model.Note

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: String): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note: Note)

    @Query("DELETE FROM notes")
    fun deleteAll()

    @Delete
    fun deleteNote(note: Note)
}

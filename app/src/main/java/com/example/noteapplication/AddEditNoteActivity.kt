package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {

    lateinit var editNoteTitle:EditText
    lateinit var editNoteDescription:EditText
    lateinit var editNoteButton:Button
    lateinit var viewModel: NoteViewModel
    var noteID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)

        editNoteTitle=findViewById(R.id.editNoteTitle)
        editNoteDescription=findViewById(R.id.editNoteDescription)
        editNoteButton=findViewById(R.id.edit_save_Button)

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        val noteType= intent.getStringExtra("noteTpe")
        if (noteType.equals("Edit")){
            val noteTitle=intent.getStringExtra("noteTitle")
            val noteDescription=intent.getStringExtra("noteDescription")
            noteID=intent.getIntExtra("noteID" ,-1)
            editNoteButton.setText("Update Note")
            editNoteTitle.setText(noteTitle)
            editNoteDescription.setText(noteDescription)
        }else {
            editNoteButton.setText("Save Note")
        }

        editNoteButton.setOnClickListener{

            val noteTitle=editNoteTitle.text.toString()
            val noteDescription=editNoteDescription.text.toString()

            if (noteType.equals("Edit")){

                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){

                    val sdf=SimpleDateFormat("dd MMM, yyy -HH:mm")
                    val currentDate:String=sdf.format(Date())
                    val updateNote=Note(noteTitle,noteDescription,currentDate)
                    updateNote.id=noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this,"Note Updated", Toast.LENGTH_SHORT).show()

                }
            }
            else{
                    if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()){
                        val sdf=SimpleDateFormat(" dd MMM, yyy -HH:mm")
                        val currentDate:String=sdf.format(Date())
                        viewModel.addNote(Note(noteTitle,noteDescription,currentDate))
                        Toast.makeText(this,"Note Added", Toast.LENGTH_SHORT).show()
                    }
                }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
            }
        }


}

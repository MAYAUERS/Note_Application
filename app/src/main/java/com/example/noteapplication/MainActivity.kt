package com.example.noteapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),NoteDeleteClickInterface,NoteClickInterface {

    lateinit var noteRecyclerView: RecyclerView
    lateinit var floatingbutton:FloatingActionButton

    lateinit var viewModel:NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteRecyclerView=findViewById(R.id.recyclerNote)
        floatingbutton=findViewById(R.id.floatingButton)
        noteRecyclerView.layoutManager=LinearLayoutManager(this)


        val adapter=NoteAdapter(this,this,this)
        noteRecyclerView.adapter=adapter
        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this,Observer<List<Note>>{notes->
            notes?.let{
                adapter.getupdatedList(it)
            }
        })

        floatingbutton.setOnClickListener{
        val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun ondeleteIconClick(note: Note) {
       viewModel.deleteNote(note)
        Toast.makeText(this,"Note Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent=Intent(this@MainActivity,AddEditNoteActivity::class.java)
        intent.putExtra("noteTpe","Edit")
        intent.putExtra("noteTitle",note.noteTitle)
        intent.putExtra("noteDescription",note.description)
        intent.putExtra("noteID",note.id)
        startActivity(intent)
        this.finish()
    }
}
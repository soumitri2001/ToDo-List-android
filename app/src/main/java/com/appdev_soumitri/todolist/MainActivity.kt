package com.appdev_soumitri.todolist

import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialise recycler view
        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesAdapter(this,this)
        recyclerView.adapter = adapter

        // initialise view model (MVVM)
        viewModel=ViewModelProvider(this,
            ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(application))
            .get(NoteViewModel::class.java)


        viewModel.allNotes.observe(this,Observer{list ->
            list?.let {
                adapter.updateList(it as ArrayList<Note>)
            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"ToDo ${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: View) {
        val noteText = todoEdit.text.toString().trim()
        if(noteText.isNotEmpty()) {
         // add note
            viewModel.insertNote(Note(noteText))
        }
    }

}
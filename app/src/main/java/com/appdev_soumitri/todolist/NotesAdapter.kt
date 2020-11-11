package com.appdev_soumitri.todolist

import android.content.Context
import android.view.LayoutInflater
import android.view.OrientationEventListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private val context: Context, private val listener:INoteAdapter) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

        private val allNotes = ArrayList<Note>()

        inner class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textView = itemView.findViewById<TextView>(R.id.todoText)
            val delBtn = itemView.findViewById<ImageView>(R.id.deleteButton)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder=NoteViewHolder(LayoutInflater
                                        .from(context)
                                        .inflate(R.layout.item_note,parent,false))
        viewHolder.delBtn.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote=allNotes[position]
        holder.textView.text=currNote.text
        
    }

    fun updateList(newList:ArrayList<Note>) {
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INoteAdapter {
    fun onItemClicked(note: Note)
}
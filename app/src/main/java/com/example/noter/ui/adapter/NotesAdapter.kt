package com.example.noter.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.noter.R
import com.example.noter.data.model.Note

class NotesAdapter
constructor(
        val context:Context,
        val listener: OnItemClickListener
        ) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private val mInflater = LayoutInflater.from(context)
    private var notes:List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val itemView = mInflater.inflate(R.layout.note, parent, false)
        return NotesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        var currentNote = notes[position]
        holder.titleView.text = currentNote.title
        holder.contentView.text = currentNote.content
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(mNotes:List<Note>){
        notes = mNotes
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        var titleView:TextView = itemView.findViewById(R.id.note_title)
        var contentView:TextView = itemView.findViewById(R.id.note_content)

        override fun onClick(v: View?) {
            val position = adapterPosition
            listener.onItemClick(position, v)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, view:View?)
    }
}
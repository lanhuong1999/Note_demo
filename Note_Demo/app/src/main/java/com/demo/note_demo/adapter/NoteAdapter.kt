package com.demo.note_demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.demo.note_demo.R
import com.demo.note_demo.model.Note

class NoteAdapter(
    private val context: Context,
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var notes: List<Note> = listOf()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtTitle: TextView = itemView.findViewById(R.id.title_note)
        private val txtDes: TextView = itemView.findViewById(R.id.des_note)
        private val btnDelete: ImageView = itemView.findViewById(R.id.delete_note)
        private val layoutNote: CardView = itemView.findViewById(R.id.layout_note)

        fun onbind(note: Note) {
            txtTitle.text = note.title
            txtDes.text = note.description
            btnDelete.setOnClickListener { onDelete(note) }
            layoutNote.setOnClickListener { onClick(note) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.onbind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}
package com.edushare.mvvmintro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.edushare.mvvmintro.R
import com.edushare.mvvmintro.model.Note
import com.edushare.mvvmintro.viewmodel.MainViewModel

class NoteAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<Note>,
    private val context: Context
) : RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): NotesViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return NotesViewHolder(root)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        } else { }

        return arrayList.size
    }


    inner class NotesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val title= view.findViewById<TextView>(R.id.titleL)
        private val delete= view.findViewById<ImageButton>(R.id.deleteL)

        fun bind(note: Note) {
            title.text = note.title
            delete.setOnClickListener {
                viewModel.remove(note)
                notifyItemRemoved(arrayList.indexOf(note))
            }
        }

    }

}
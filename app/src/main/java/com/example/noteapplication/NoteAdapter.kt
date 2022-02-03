package com.example.noteapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(var context: Context,
                  val noteClickInterface: NoteClickInterface,
                  val noteDeleteClickInterface: NoteDeleteClickInterface )
    :RecyclerView.Adapter<NoteAdapter.ViewHolder>()
   {
       private val allnotes:ArrayList<Note> = ArrayList()

       inner class ViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){

                          val noteTV=itemView.findViewById<TextView>(R.id.tv_title)
                          val noteDES=itemView.findViewById<TextView>(R.id.tv_description)
                          val noteTIME=itemView.findViewById<TextView>(R.id.tv_time)
                          val deleteIcon=itemView.findViewById<ImageView>(R.id.delete_icon)
                      }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
           var view=LayoutInflater.from(parent.context).inflate(R.layout.note_item_list,parent,false)
           return ViewHolder(view)
       }

       override fun onBindViewHolder(holder: ViewHolder, position: Int) {
          holder.noteTV.setText(allnotes.get(position).noteTitle)
           holder.noteDES.setText(allnotes.get(position).description)
           holder.noteTIME.setText(allnotes.get(position).timestamp)
           holder.deleteIcon.setOnClickListener{
               noteDeleteClickInterface.ondeleteIconClick(allnotes.get(position))
           }
           holder.itemView.setOnClickListener{
               noteClickInterface.onNoteClick(allnotes.get(position))
           }

       }

       override fun getItemCount(): Int {
         return allnotes.size
       }


       fun getupdatedList(newList: List<Note>){
           allnotes.clear()
           allnotes.addAll(newList)
           notifyDataSetChanged()
       }
   }



interface NoteClickInterface{
    fun onNoteClick(note: Note)
}
interface NoteDeleteClickInterface{
    fun ondeleteIconClick(note: Note)
}
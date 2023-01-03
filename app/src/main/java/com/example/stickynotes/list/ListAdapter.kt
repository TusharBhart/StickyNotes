package com.example.stickynotes.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.example.stickynotes.R
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.stickynotes.data.User


class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {


    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(com.example.stickynotes.R.layout.custom_row,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int){
        val currentItem = userList[position]

        val v: View = holder.itemView
        val noteRow = v.findViewById<TextView>(com.example.stickynotes.R.id.noteRow) as TextView
        val titleRow = v.findViewById<TextView>(com.example.stickynotes.R.id.titleRow) as TextView
        noteRow.text = currentItem.notes.toString()
        titleRow.text = currentItem.title.toString()


        val rowLayout = v.findViewById<CardView>(com.example.stickynotes.R.id.rowLayout) as CardView
        rowLayout.setOnClickListener{
            //listFragmentDirections.actionListFragmentToUpdateFragment2(currentItem)
            val action = listFragmentDirections.actionListFragmentToViewFragment(currentItem)
                 //holder.itemView.findNavController().navigate(act)
                 holder.itemView.findNavController().navigate(action)
        }


    }


    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }


}
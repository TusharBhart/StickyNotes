package com.example.stickynotes.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stickynotes.R
import com.example.stickynotes.data.User
import com.example.stickynotes.data.userViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class addFragment : Fragment() {

    private lateinit var mUserViewModel: userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
        val addNote=view.findViewById<FloatingActionButton>(R.id.addNote)

        addNote.setOnClickListener {
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {
      // val entry = findViewById<EditText>(R.id.entry)
        val entry = requireView().findViewById<EditText>(R.id.entry)
        val titleEntry = requireView().findViewById<EditText>(R.id.titleEntry)
        val note = entry.text.toString()
        val title = titleEntry.text.toString()

        if (inputCheck(note,title)){
            val user = User(0,note,title)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Note added successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Enter a title and a note",Toast.LENGTH_SHORT).show()
        }
    }
    private fun inputCheck(note: String,title :String): Boolean {
        return !(TextUtils.isEmpty(note) || TextUtils.isEmpty(title))
    }


}
package com.example.stickynotes

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.stickynotes.data.User
import com.example.stickynotes.data.userViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_update.*

class UpdateFragment : Fragment() {
    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
        val updateEntry = view.findViewById<EditText>(R.id.updateEntry)
        val titleUpdateEntry = view.findViewById<EditText>(R.id.titleUpdateEntry)
        val updateNote = view.findViewById<FloatingActionButton>(R.id.updateNote)

        updateEntry.setText(args.currentUser.notes)
        titleUpdateEntry.setText(args.currentUser.title)

        updateNote.setOnClickListener {
            updateItem()
        }
        // Add menu
        setHasOptionsMenu(true)
        return view
    }

    private fun updateItem() {
        val note = updateEntry.text.toString()
        val title = titleUpdateEntry.text.toString()

        if (inputCheck(note,title)) {
            val updatedUser = User(args.currentUser.id,note,title)

            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Note updated successfully ", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Enter a title and a note", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(note: String,title :String): Boolean {
        return !(TextUtils.isEmpty(note) || TextUtils.isEmpty(title))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Note deleted successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment2_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_->
        }
        builder.setTitle("Delete Note?")
        builder.setMessage("Are you sure you want to delete?")
        builder.create().show()
    }
}
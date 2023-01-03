package com.example.stickynotes.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stickynotes.R
import com.example.stickynotes.data.userViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class listFragment : Fragment() {
    private lateinit var mUserViewModel: userViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_list, container, false)

        //recyclerview
        val adapter = ListAdapter()
        val recyclerview=view.findViewById<RecyclerView>(R.id.recyclerView)
        val recyclerView = recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        //UserViwModel
        mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)

        })


        val floatingActionButton=view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_->
            mUserViewModel.deleteAllUser()
            Toast.makeText(requireContext(), "All notes deleted successfully", Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("No"){_,_->
        }
        builder.setTitle("Delete All Notes?")
        builder.setMessage("Are you sure you want to delete?")
        builder.create().show()

    }


}
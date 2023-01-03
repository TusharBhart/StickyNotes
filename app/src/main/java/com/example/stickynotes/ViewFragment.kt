package com.example.stickynotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ViewFragment : Fragment() {
    //private var userList = emptyList<User>()
    private val args by navArgs<ViewFragmentArgs>()

    //private lateinit var mUserViewModel: userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_view, container, false)


        val viewText = view.findViewById<TextView>(R.id.viewText)
        val viewTitleRow = view.findViewById<TextView>(R.id.viewTitleRow)

        viewText.text = args.viewpass.notes
        viewTitleRow.text = args.viewpass.title



        val viewUpdateNote=view.findViewById<FloatingActionButton>(R.id.viewUpdateNote)
        viewUpdateNote.setOnClickListener {

           val action = ViewFragmentDirections.actionViewFragmentToUpdateFragment2(args.viewpass)
           view.findNavController().navigate(action)

           //view.findNavController().navigate(R.id.action_viewFragment_to_updateFragment2)
        }
        return view
    }


}
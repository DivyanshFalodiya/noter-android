package com.example.noter.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noter.R
import com.example.noter.data.model.Note
import com.example.noter.data.viewmodel.NotesViewModel
import com.example.noter.ui.adapter.NotesAdapter
import com.example.noter.ui.edit.EditActivity
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArchivesFragment : Fragment(), NotesAdapter.OnItemClickListener {
    private var toolbarHead: EditText? = null
    private var toolbar:Toolbar? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: NotesAdapter
    private val notesViewModel: NotesViewModel by viewModels()
    private var notes:List<Note> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_archives, container, false)

        toolbar = activity?.findViewById(R.id.my_toolbar)
        toolbar?.setTitle(R.string.archives)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerViewAdapter = NotesAdapter(requireContext(), this)

        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = recyclerViewAdapter

        notesViewModel.getArchivedNotes()
        notesViewModel.mArchivedNotes.observe(viewLifecycleOwner, {
            notes = it
            recyclerViewAdapter.setNotes(it)
        })
        return view
    }

    override fun onItemClick(position: Int, view: View?) {
        val intent = Intent(context, EditActivity::class.java)
        startActivity(intent)
    }

}
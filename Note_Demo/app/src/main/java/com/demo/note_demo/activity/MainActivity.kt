package com.demo.note_demo.activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.note_demo.R
import com.demo.note_demo.adapter.NoteAdapter
import com.demo.note_demo.model.Note
import com.demo.note_demo.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.factoryNoteViewModel(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addControls()
        addEvents()

    }

    private fun addEvents() {
        btn_add.setOnClickListener({
            val intent=Intent(this,AddActivity::class.java)
            startActivity(intent)
        })
    }

    private fun addControls() {
        var adapter = NoteAdapter(this, onItemClick,onItemDelete)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter=adapter

        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setNotes(it)
        })
    }
    private var onItemClick:(Note)->Unit={
        val intent =Intent(this,UpdateActivity::class.java)
        intent.putExtra("note_update",it)
        startActivity(intent)
    }
    private var onItemDelete:(Note)->Unit={
        noteViewModel.delenote(it)
    }
}
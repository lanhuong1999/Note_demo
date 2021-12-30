package com.demo.note_demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.demo.note_demo.R
import com.demo.note_demo.model.Note
import com.demo.note_demo.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.factoryNoteViewModel(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        btn_confirm.setOnClickListener({
            val note = Note(title_edt.text.toString(), description_edt.text.toString())
            noteViewModel.insertNote(note)
            finish()
        })
    }
}
package com.demo.note_demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.demo.note_demo.R
import com.demo.note_demo.model.Note
import com.demo.note_demo.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_update.*
import kotlinx.android.synthetic.main.activity_update.btn_confirm
import kotlinx.android.synthetic.main.activity_update.title_edt

class UpdateActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.factoryNoteViewModel(this.application)
        )[NoteViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val intent =  intent
        val note = intent.getSerializableExtra("note_update") as Note
        title_edt.setText(note.title)
        des_edt.setText(note.description)

        btn_confirm.setOnClickListener({
            note.title=title_edt.text.toString()
            note.description=des_edt.text.toString()
            noteViewModel.updateNote(note)
            finish()
        })
    }
}
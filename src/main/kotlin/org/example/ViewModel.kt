package org.example

import org.example.model.DraftNote
import org.example.model.ToDoListSystem
import org.example.model.getToDoListSystem
import org.uqbar.commons.model.annotations.Observable

@Observable
class ToDoListModel(val toDoListSystem: ToDoListSystem = getToDoListSystem()) {

    lateinit var notes: List<NoteModel>

    init {
        updateNotes()
    }

    fun updateNotes() {
        notes = toDoListSystem.notes.map { NoteModel(it.id, it.title, it.description) }
    }

    var selected: NoteModel? = null
        set(value) {
            check = true
            field = value
        }

    var check = false

    fun editNote(noteId: String, note: DraftNoteModel) {
        toDoListSystem.editNote(noteId, DraftNote(note.title, note.description))
        updateNotes()
    }

    fun addNote(note: DraftNoteModel) {
        toDoListSystem.addNote(DraftNote(note.title, note.description))
        updateNotes()
    }

    fun removeNote(noteId: String) {
        toDoListSystem.removeNote(noteId)
        updateNotes()
    }
}

@Observable
class NoteModel(var id: String, var title: String, var description: String)

@Observable
class DraftNoteModel() {
    var title = ""
    var description = ""
    var errors = ""

    constructor(noteModel: NoteModel) : this() {
        title = noteModel.title
        description = noteModel.description
    }

}
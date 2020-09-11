package org.example

import org.example.model.RepeatedException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.commons.model.exceptions.UserException

class ToDoListWindow(owner: WindowOwner, model: ToDoListModel): SimpleWindow<ToDoListModel>(owner, model) {
    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel) with {
            caption = "Agregar Nota"
            onClick {
                val note = DraftNoteModel()
                val view = EditNoteWindow(this@ToDoListWindow, note)
                view.onAccept {
                    try {
                        modelObject.addNote(note)
                    } catch (e: RepeatedException) {
                        throw UserException(e.message)
                    }
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Editar Nota"
            bindEnabledTo("check")
            onClick {
                val note = DraftNoteModel(modelObject.selected!!)
                val view = EditNoteWindow(this@ToDoListWindow, note)
                view.onAccept {
                    modelObject.editNote(modelObject.selected!!.id, note)
                }
                view.open()
            }
        }

        Button(actionsPanel) with {
            caption = "Borrar Nota"
            bindEnabledTo("check")
            onClick {
                val deleteWindow = DeleteWindow(this@ToDoListWindow, modelObject.selected!!)
                deleteWindow.onAccept {
                    modelObject.removeNote(modelObject.selected!!.id)
                }
                deleteWindow.open()
            }
        }
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "ToDo List"

        table<NoteModel>(mainPanel) {
            bindItemsTo("notes")
            bindSelectionTo("selected")
            visibleRows = 10
            column {
                title = "#"
                fixedSize = 30
                bindContentsTo("id")
            }
            column {
                title = "Title"
                fixedSize = 130
                bindContentsTo("title")
            }
            column {
                title = "Description"
                fixedSize = 230
                bindContentsTo("description")
            }
        }
    }

}
package org.example

import org.example.objectModel.ToDoListSystem
import org.example.objectModel.getToDoListSystem
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.arena.Application
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.Window
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button

@Observable
class ToDoListModel(val toDoListSystem: ToDoListSystem = getToDoListSystem()) {

    var notes = toDoListSystem.notes.map { NoteModel(it.id, it.title, it.description) }
    var selected: NoteModel? = null

}

@Observable
data class NoteModel(var id: String, var title: String, var description: String)

class ToDoListWindow(owner: WindowOwner, model: ToDoListModel) : SimpleWindow<ToDoListModel>(owner, model) {
    override fun addActions(mainPanel: Panel) {
        Button(mainPanel) with {
            caption = "Agregar nota"
            onClick {
                println("Addd note")
            }
        }
        Button(mainPanel) with {
            caption = "Editar nota"
            onClick {
                throw UserException("Seleccione una nota")
            }
        }
        Button(mainPanel) with {
            caption = "Eliminar nota"
        }
    }

    override fun createFormPanel(mainPanel: Panel) {
        title = "ToDo List"

        table<NoteModel>(mainPanel) {
            bindItemsTo("notes")
            bindSelectionTo("selected")
            visibleRows = 5
            column {
                title = "ID"
                fixedSize = 30
                bindContentsTo("id")
            }
            column {
                title = "Title"
                weight = 50
                bindContentsTo("title")
            }
            column {
                title = "Description"
                bindContentsTo("description")
            }
        }
    }

}


class ToDoApplication : Application() {
    override fun createMainWindow(): Window<*> {
        return ToDoListWindow(this, ToDoListModel())
    }

}

fun main() {
    ToDoApplication().start()
}


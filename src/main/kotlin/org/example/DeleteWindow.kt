package org.example

import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class DeleteWindow(owner: WindowOwner, model: NoteModel): Dialog<NoteModel>(owner, model) {
    override fun createFormPanel(mainPanel: Panel?) {
        Label(mainPanel) with  {
            text = "¿Estas seguro de quere borrar la nota con id: ${modelObject.id}?"
        }

        Button(mainPanel) with {
            caption = "Aceptar"
            onClick { accept() }
        }

        Button(mainPanel) with {
            caption = "Cancelar"
            onClick { cancel() }
        }
    }

}
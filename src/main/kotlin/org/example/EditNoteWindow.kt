package org.example

import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.arena.kotlin.extensions.*
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.WindowOwner

class EditNoteWindow(owner: WindowOwner, model: DraftNoteModel): Dialog<DraftNoteModel>(owner, model) {

    override fun createFormPanel(mainPanel: Panel) {
        title = "Editar Nota"

        Label(mainPanel) with {
            text = "Titulo"
        }

        TextBox(mainPanel) with {
            bindTo("title")
        }

        Label(mainPanel) with {
            text = "Descripcion"
        }

        TextBox(mainPanel) with {
            bindTo("description")
        }

        Button(mainPanel) with {
            caption = "Guardar"
            onClick {
                if (modelObject.title.isEmpty()) {
                    showError("Se necesita un titulo =S")
                } else {
                    accept()
                }

            }
        }

        Button(mainPanel) with {
            caption = "Cancelar"
            onClick {
                cancel()
            }
        }
    }

}

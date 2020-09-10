package org.example

import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window

class ToDoListApplication: Application() {
    override fun createMainWindow(): Window<*> {
        return ToDoListWindow(this, ToDoListModel())
    }
}

fun main() {
    ToDoListApplication().start()
}

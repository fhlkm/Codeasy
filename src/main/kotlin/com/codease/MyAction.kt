package com.codease

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages


class MyAction: AnAction() {
    private val LOG = Logger.getInstance(MyAction::class.java)
    val TAG="MyAction"
    override fun actionPerformed(e: AnActionEvent) {
        val project :Project?= e.project
        if (project == null) {
            Messages.showErrorDialog("Project not found.", "Error");
            return;
        }

        displayMyDialog(project!!)

    }

    fun displayMyDialog(project:Project){
        val dialog = MyCustomDialog()
        dialog.show()

        if (dialog.isOK) {
            // Perform any actions when the user clicks the OK button.
        }
    }

    fun insertMessageAtCursorLocation(project:Project,msg:String){
        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return
        val offset = editor.caretModel.offset
        val document = editor.document
        WriteCommandAction.runWriteCommandAction(editor.project) {
            // Perform write operations on the document here
            document.insertString(offset, msg)
        }

    }




}
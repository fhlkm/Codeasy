package com.codease.codease

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction

import com.intellij.openapi.fileEditor.FileEditorManager

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.diagnostic.Logger


class MyAction: AnAction() {
    private val LOG = Logger.getInstance(MyAction::class.java)
    val TAG="MyAction"
    override fun actionPerformed(e: AnActionEvent) {
//        val noti = NotificationGroup("myplugin", NotificationDisplayType.BALLOON, true)
//        noti.createNotification("My Title",
//                "My Message",
//                NotificationType.INFORMATION,
//                null
//        ).notify(e.project)
        val project :Project?= e.project
        val message: String = "Please input the the requirement to generate your code:"
        val defaultText: String = "N/A"
        val userInput: String? = Messages.showInputDialog(
            project,
            message,
            "Input Dialog",
            Messages.getQuestionIcon(),
            defaultText,
            null
        )

        // Do something with the user input
        if (userInput != null) {
            // User clicked OK, and entered some text
            // Here you can do something with the user's input
            Messages.showMessageDialog(project, "You entered: $userInput", "Result", Messages.getInformationIcon())
            insertMessageAtCursorLocation(project!!,userInput)
            LOG.info(TAG+"insert message to cursor place")


        } else {
            // User clicked Cancel or closed the dialog
            Messages.showMessageDialog(project, "You canceled or closed the dialog", "Result", Messages.getInformationIcon())
            LOG.info(TAG+"You canceled or closed the dialog")
        }

    }

    fun insertMessageAtCursorLocation(project:Project,msg:String){
        val editor = FileEditorManager.getInstance(project).selectedTextEditor ?: return
        val offset = editor.caretModel.offset
        val document = editor.document
//        ApplicationManager.getApplication().runWriteAction {
//            // Perform write operations here
//            editor.document.insertString(offset, "Hello, world!")
//        }

        WriteCommandAction.runWriteCommandAction(editor.project) {
            // Perform write operations on the document here
            document.insertString(offset, msg)
        }

    }


}
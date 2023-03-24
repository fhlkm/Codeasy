package com.codease

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

// Process to build plugin (.jar)
// File--Project Structure----Artifact----'+'---------jar---from modules with dependency---select modules(main class can be empty).
// build--build Artifacts---jar is in directory "codease\github\build\idea-sandbox\plugins\Codease\lib"

class MyAction: AnAction(), PrintAnswerListener {
    private val LOG = Logger.getInstance(MyAction::class.java)
    val TAG="MyAction"
    var project :Project?=null
    var code:String?=null
    override fun actionPerformed(e: AnActionEvent) {
        project = e.project
        if (project == null) {
            Messages.showErrorDialog("Project not found.", "Error");
            return;
        }

        displayMyDialog(project!!)

    }

    fun displayMyDialog(project:Project){
        val dialog = MyCustomDialog()
        dialog.answerListener = this
        dialog.show()

        if (dialog.isOK) {
            // Perform any actions when the user clicks the OK button.
            try{
                if(project!=null&& code!=null ) {
                    insertMessageAtCursorLocation(project!!, code!!)
                }else{
                    if(null == project)
                    LOG.info(TAG+"project   is NULL")

                    if(null == code)
                        LOG.info(TAG+"code   is NULL")
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
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

    override fun printAnswer(answer: String) {
        code=answer
    }


}
interface PrintAnswerListener{
    fun printAnswer(answer:String)
}
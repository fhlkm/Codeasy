package com.codease

import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBScrollPane
import java.awt.*
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextArea


class MyCustomDialog : DialogWrapper(true) {
    private var mainPanel: JPanel? = null
    private var inputTextArea: JTextArea? = null
    private var outputTextArea: JTextArea? = null
    private var button1: JButton? = null
    private var button2: JButton? = null

    init {
        init()
        title = "My Custom Dialog"
    }


    override fun createCenterPanel(): JComponent? {
        mainPanel = JPanel(BorderLayout())
        inputTextArea = JTextArea(2, 100)
        outputTextArea = JTextArea(20, 100)

        outputTextArea!!.isEditable = false



        val textPanel = JPanel(GridBagLayout())
        val gridBagConstraints = GridBagConstraints()
        gridBagConstraints.gridx = 0
        gridBagConstraints.gridy = 0
        gridBagConstraints.weightx = 1.0
        gridBagConstraints.weighty = 1.0
        gridBagConstraints.fill = GridBagConstraints.BOTH
        textPanel.add(JBScrollPane(inputTextArea), gridBagConstraints)


        gridBagConstraints.gridy = 1
        gridBagConstraints.weighty = 5.0
        textPanel.add(JBScrollPane(outputTextArea), gridBagConstraints)

        mainPanel!!.add(textPanel, BorderLayout.CENTER)
        return mainPanel
    }


    override fun doValidate(): ValidationInfo? {
        // Add any validation rules if needed.
        return null
    } // Implement any action listeners for the buttons if needed.
}
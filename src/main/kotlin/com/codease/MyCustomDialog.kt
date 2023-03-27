package com.codease

import com.connection.RetrofitClient
import com.data.OpenAIRequestBody
import com.data.OpenAIRequestBodyResponse
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.ui.components.JBScrollPane
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.awt.*
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextArea
import com.intellij.openapi.diagnostic.Logger
import javax.swing.text.AbstractDocument
import javax.swing.text.AttributeSet
import javax.swing.text.BadLocationException
import javax.swing.text.DocumentFilter
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

class MyCustomDialog : DialogWrapper(true) {
    private var mainPanel: JPanel? = null
    private var inputTextArea: JTextArea? = null
//    private var outputTextArea: JTextArea? = null
    private var outputTextArea: RSyntaxTextArea? = null
    private var submit :JButton? = null
    var answerListener:PrintAnswerListener?=null
    private val LOG = Logger.getInstance(MyCustomDialog::class.java)
    val TEXT_LENGTH=50
    init {
        init()
        title = "Codease"
    }


    override fun createCenterPanel(): JComponent? {
        mainPanel = JPanel(BorderLayout())
        inputTextArea = JTextArea(5, TEXT_LENGTH)
        inputTextArea!!.setLineWrap(true);
        inputTextArea!!.setWrapStyleWord(false);
//        outputTextArea = JTextArea(20, TEXT_LENGTH)
        outputTextArea= RSyntaxTextArea(20, TEXT_LENGTH)
        outputTextArea!!.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_JAVA;
        outputTextArea!!.isCodeFoldingEnabled = true;
        submit = JButton ("Submit")
        outputTextArea!!.isEditable = false
        val framePanel = JPanel(GridLayout())
        /**********************weight of inputTextArea: sumbit =5:1****************************************/
        var textPanel = JPanel(GridBagLayout())
        var gridBagConstraints = GridBagConstraints()
        gridBagConstraints.fill=  GridBagConstraints.BOTH
        gridBagConstraints.gridx = 0// cell index 0, lef-->right
        gridBagConstraints.gridy = 0//
        gridBagConstraints.weightx = 5.0
        gridBagConstraints.weighty = 1.0
        framePanel.add(JBScrollPane(inputTextArea),gridBagConstraints)

        gridBagConstraints.gridx = 1//
        gridBagConstraints.gridy = 0
        gridBagConstraints.weightx = 1.0

        framePanel.add(submit,gridBagConstraints)
        /**********************weight of inputTextArea: sumbit =5:1****************************************/

        /**********************weight of framePanel: outputTextArea =1:5****************************************/
        gridBagConstraints.fill = GridBagConstraints.BOTH
        gridBagConstraints.gridx = 0
        gridBagConstraints.gridy = 0
        gridBagConstraints.weightx = 1.0
        gridBagConstraints.weighty = 1.0

        textPanel.add(framePanel, gridBagConstraints)
        gridBagConstraints.gridx = 0
        gridBagConstraints.gridy = 1
        gridBagConstraints.weighty = 5.0
        textPanel.add(JBScrollPane(outputTextArea), gridBagConstraints)
        /**********************weight of framePanel: outputTextArea =1:5****************************************/
        mainPanel!!.add(textPanel, BorderLayout.CENTER)
        submit!!.addActionListener {
            outputTextArea!!.text="Generating..."
            val searchCommand = inputTextArea!!.text
            sendPost(searchCommand)
        }
        return mainPanel
    }


    override fun doValidate(): ValidationInfo? {
        // Add any validation rules if needed.
        return null
    } // Implement any action listeners for the buttons if needed.


    fun sendPost(command:String) {


        val request  = OpenAIRequestBody(
            model = "text-davinci-003",
            prompt =command,
            temperature = 0.7,
            max_tokens = 512,
            top_p = 1,
            frequency_penalty = 0,
            presence_penalty = 0
        )
        RetrofitClient.apiService.createPost(request)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<OpenAIRequestBodyResponse> {
                override fun onSubscribe(d: Disposable) {
                    // Handle the subscription
                }

                override fun onNext(t: OpenAIRequestBodyResponse) {
                    // Handle the response
                    outputTextArea!!.text = t.choices[0].text
                    answerListener?.printAnswer(t.choices[0].text!!)
                    println("Response: $t")
                }

                override fun onError(e: Throwable) {
                    // Handle the error
                    outputTextArea!!.text = "Internet Error"
                    LOG.error(e)
                }

                override fun onComplete() {
                    // Handle the completion
                    println("Request completed")
                }
            })
    }

}


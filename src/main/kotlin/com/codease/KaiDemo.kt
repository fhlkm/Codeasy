package com.codease

import com.connection.Constants
import com.connection.RetrofitClient
import com.data.MessageItem
import com.data.OpenAIRequestBody
import com.data.OpenAIRequestBodyResponse
import com.intellij.openapi.diagnostic.Logger
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


fun main(){
    println("main")
    val command="""my phone's sound is too big ,please don't let my phone make any sound"""
    sendPost(command)

    println("end")
}

fun sendPost(command:String) {

    val messageOne = MessageItem(Constants.systemRole,Constants.content)
    val messageTwo=MessageItem(Constants.systemUser,command)
    val request  = OpenAIRequestBody(
        model = Constants.model,
        messages = arrayOf(messageOne,messageTwo),
        temperature = 1.0,
        max_tokens = 256,
        top_p = 1,
        frequency_penalty = 0,
        presence_penalty = 0
    )
    RetrofitClient.apiService.createPost(request)
        .subscribeOn(Schedulers.io())
        .subscribe(object : Observer<OpenAIRequestBodyResponse> {
            override fun onSubscribe(d: Disposable) {
                // Handle the subscription
                println("finish")
            }

            override fun onNext(t: OpenAIRequestBodyResponse) {
                // Handle the response

                println("Response from chatgpt, user command is: ${t.choices[0].message?.content}")
            }

            override fun onError(e: Throwable) {
                println("Exception:")
                println(e)
            }

            override fun onComplete() {
                // Handle the completion
                println("Request completed")
            }
        })

    Thread.sleep(2000)
}
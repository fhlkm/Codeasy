package com.connection



import com.data.PostData
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
class HttpManager {


    fun sendPost() {
        val postData = PostData("My Title", "My Body", 1)

        RetrofitClient.apiService.createPost(postData)
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<PostData> {
                override fun onSubscribe(d: Disposable) {
                    // Handle the subscription
                }

                override fun onNext(t: PostData) {
                    // Handle the response
                    println("Response: $t")
                }

                override fun onError(e: Throwable) {
                    // Handle the error
                    println("Error: ${e.message}")
                }

                override fun onComplete() {
                    // Handle the completion
                    println("Request completed")
                }
            })
    }



}

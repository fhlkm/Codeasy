package com.connection

import com.data.PostData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("posts")
    fun createPost(@Body postData: PostData): Observable<PostData>
}

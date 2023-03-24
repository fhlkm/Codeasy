package com.connection

import com.data.OpenAIRequestBody
import com.data.OpenAIRequestBodyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Authorization: Bearer ***************")
    @POST("/v1/completions")
    fun createPost(@Body postData: OpenAIRequestBody): Observable<OpenAIRequestBodyResponse>
}

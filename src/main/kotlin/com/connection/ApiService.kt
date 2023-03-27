package com.connection

import com.data.OpenAIRequestBody
import com.data.OpenAIRequestBodyResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {



    @Headers("Authorization: Bearer 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")
    @POST("/v1/completions")
    fun createPost(@Body postData: OpenAIRequestBody): Observable<OpenAIRequestBodyResponse>
}

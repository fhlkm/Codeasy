package com.data

import com.google.gson.annotations.SerializedName

/**
{
"prompt": "write a broadcastreceiver in android",
"temperature": 0.7,
"max_tokens": 256,
"top_p": 1,
"frequency_penalty": 0,
"presence_penalty": 0,
"model": "text-davinci-003"
}


 */
data class OpenAIRequestBody(
    val model: String,
    val prompt: String,
    val temperature: Double,
    val max_tokens: Int,
    val top_p: Int,
    val frequency_penalty: Int,
    val presence_penalty: Int
)



data class OpenAIRequestBodyResponse (

    @SerializedName("id"      ) var id      : String?            = null,
    @SerializedName("object"  ) var mObject  : String?            = null,
    @SerializedName("created" ) var created : Int?               = null,
    @SerializedName("model"   ) var model   : String?            = null,
    @SerializedName("choices" ) var choices : ArrayList<Choices> = arrayListOf(),
    @SerializedName("usage"   ) var usage   : Usage?             = Usage()

)


data class Choices (

    @SerializedName("text"          ) var text         : String? = null,
    @SerializedName("index"         ) var index        : Int?    = null,
    @SerializedName("logprobs"      ) var logprobs     : String? = null,
    @SerializedName("finish_reason" ) var finishReason : String? = null

)



data class Usage (

    @SerializedName("prompt_tokens"     ) var promptTokens     : Int? = null,
    @SerializedName("completion_tokens" ) var completionTokens : Int? = null,
    @SerializedName("total_tokens"      ) var totalTokens      : Int? = null

)
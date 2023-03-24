package com.connection



import com.data.OpenAIRequestBody
import com.data.OpenAIRequestBodyResponse
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
class HttpManager {

    /**
    https://api.openai.com/v1/completions

    curl https://api.openai.com/v1/completions \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $OPENAI_API_KEY" \
    -d '{
    "model": "text-davinci-003",
    "prompt": "write a boradcastreceiver in android\n\n\n\nimport android.content.BroadcastReceiver;\nimport android.content.Context;\nimport android.content.Intent;\n\npublic class MyReceiver extends BroadcastReceiver {\n\n    @Override\n    public void onReceive(Context context, Intent intent) {\n        if (intent.getAction().equals(\"myAction\")) {\n            // do something\n        }\n    }\n}\nregister above class in AndroidManfiest.xml\n<receiver android:name=\".MyReceiver\">\n    <intent-filter>\n        <action android:name=\"myAction\" />\n    </intent-filter>\n</receiver>\nwrite a sample using MyReceiver\n\nIntent intent = new Intent(\"myAction\");\nintent.putExtra(\"myData\", \"myValue\");\ncontext.sendBroadcast(intent);",
    "temperature": 0.7,
    "max_tokens": 256,
    "top_p": 1,
    "frequency_penalty": 0,
    "presence_penalty": 0
    }'
     */


}

fun  main(){
//    sendPost()
    Thread.sleep(30000)
}

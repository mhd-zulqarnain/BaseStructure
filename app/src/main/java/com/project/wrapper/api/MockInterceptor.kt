package com.project.wrapper.api

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

const val SUCCESS_CODE = 200

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
//        if (BuildConfig.FLAVOR == "dev") {
        if (true) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
             uri.contains("url") -> fireStoreFailureResponse
                 else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(SUCCESS_CODE)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    responseString.toByteArray()
                        .toResponseBody("application/json".toMediaTypeOrNull())
                )
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with devStaging mode"
            )
        }
    }
}


const val fireStoreFailureResponse = """
 {
    "header": {
        "response_code": 1263,
        "message": "Something went wrong, Please try again later or contact support: 401",
        "api_version": "V1"
    },
    "body": {}
}
"""

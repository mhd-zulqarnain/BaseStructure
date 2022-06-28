package com.project.wrapper.api

import com.project.wrapper.data.model.SampleModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val URL_SAMPLE = "sample/in"


interface SampleApi {


    @GET(URL_SAMPLE)
    suspend fun sampleCall(
        @QueryMap params: HashMap<String, Any>
    ): Response<Envelope<SampleModel>>

}

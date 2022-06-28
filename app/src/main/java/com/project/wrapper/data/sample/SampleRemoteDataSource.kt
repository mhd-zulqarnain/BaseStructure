package com.project.wrapper.data.sample

import android.content.Context
import com.project.wrapper.R
import com.project.wrapper.api.ApiParams
import com.project.wrapper.api.SampleApi
import com.project.wrapper.data.model.SampleModel
import com.project.wrapper.network.NetworkHandler
import com.project.wrapper.utils.safeApiCall
import com.project.wrapper.api.Result
import com.project.wrapper.utils.processResult

import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleRemoteDataSource @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val networkHandler: NetworkHandler,
    private val sampleApi: SampleApi
) {

    suspend fun getIpAddress(): Result<SampleModel> {
        return when (networkHandler.isConnected) {
            true -> {
                safeApiCall(
                    call = { callSampleApi(ApiParams.getSampleParams()) },
                    errorMessage = context.getString(R.string.error_msg)
                )
            }
            false -> {
                Result.Error(IOException(context.getString(R.string.failure_network_connection)))
            }
        }
    }

    private suspend fun callSampleApi(
        params: HashMap<String, Any>
    ): Result<SampleModel> {
        return sampleApi.sampleCall(params).processResult()
    }
}
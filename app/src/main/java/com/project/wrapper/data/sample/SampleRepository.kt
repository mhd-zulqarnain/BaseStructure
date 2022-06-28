package com.project.wrapper.data.sample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import com.project.wrapper.api.Result

@Singleton
class SampleRepository @Inject constructor(
    private val remoteDataSource: SampleRemoteDataSource
) {

    suspend fun sampleCall(): Flow<Result<String>> = flow {
        try {
            when (val result = remoteDataSource.getIpAddress()) {
                is Result.Success -> {
                    val data = result.data.sampleData
                    emit(Result.Success(data))
                }
                is Result.Error -> {
                    emit(result)
                }
                else -> {
                    //not implemented
                }
            }
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }

}
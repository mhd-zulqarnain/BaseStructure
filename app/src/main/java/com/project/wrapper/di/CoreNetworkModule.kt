package com.project.wrapper.di

import com.project.wrapper.api.SampleApi
import com.project.wrapper.di.qualifiers.ApiGateway
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreNetworkModule {


    @Provides
    @Singleton
    @ApiGateway
    fun retrofitGateway(
        @ApiGateway client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("Baseurl").build()


    @Provides
    @Singleton
    fun sampleApi(@ApiGateway retrofit: Retrofit): SampleApi =
        retrofit.create(SampleApi::class.java)
}

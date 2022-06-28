package com.project.wrapper.data.model

import com.google.gson.annotations.SerializedName

data class SampleModel(
    @SerializedName("sample_data")
    val sampleData: String
)

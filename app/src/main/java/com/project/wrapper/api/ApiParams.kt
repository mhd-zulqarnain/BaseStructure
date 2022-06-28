package com.project.wrapper.api

import com.project.wrapper.utils.BuildApi
import java.util.*
import kotlin.collections.HashMap

object ApiParams {
    private fun baseParams(): HashMap<String, Any> {
        val baseParams: HashMap<String, Any> = HashMap()
        baseParams["app_version"] = BuildApi.VERSION_NAME
        baseParams["device_id"] = BuildApi.DEVICE_ID
        baseParams["device_model"] = BuildApi.DEVICE_MODEL
        baseParams["device_type"] = BuildApi.DEVICE_TYPE
        baseParams["locale"] = Locale.getDefault()
        baseParams["release_type"] = BuildApi.RELEASE_TYPE
        return baseParams
    }
    fun getSampleParams(): HashMap<String, Any> {
        return baseParams()
    }
}
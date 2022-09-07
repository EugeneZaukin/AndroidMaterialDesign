package com.eugene.androidmaterialdesign.data.model

import com.google.gson.annotations.SerializedName

data class NasaInfo(
    val copyright: String?,
    val date: String?,
    val explanation: String?,
    val title: String?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("hdurl")
    val hdUrl: String?
)
package com.example.meditopitestcase.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class User(
    @SerialName("avatarURL")
    val avatarURL: String,
    @SerialName("id")
    val id: String,
    @SerialName("nickname")
    val nickname: String
): Parcelable
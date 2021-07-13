package com.example.meditopitestcase.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Message(
    @SerialName("id")
    val id: String,
    @SerialName("text")
    val text: String,
    @SerialName("timestamp")
    val timestamp: Long,
    @SerialName("user")
    val user: User
): Parcelable
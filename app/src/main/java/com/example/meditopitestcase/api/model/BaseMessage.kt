package com.example.meditopitestcase.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class BaseMessage (
    @SerialName("messages")
    val messages: List<Message>
): Parcelable
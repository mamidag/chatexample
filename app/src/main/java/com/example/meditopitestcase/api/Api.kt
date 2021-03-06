package com.example.meditopitestcase.api

import com.example.meditopitestcase.api.model.BaseMessage
import retrofit2.http.GET

interface Api {
    @GET("chatdata-example.json")
    suspend fun fetchMessages() : BaseMessage
}
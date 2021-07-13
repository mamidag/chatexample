package com.example.meditopitestcase.repo.remote

import com.example.meditopitestcase.api.model.BaseMessage
import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.data.Resource
import kotlinx.coroutines.flow.Flow

interface MessageRepoSource {
    suspend fun fetchMessage() : Flow<Resource<BaseMessage>>
}
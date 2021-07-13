package com.example.meditopitestcase.repo.remote

import com.example.meditopitestcase.api.Api
import com.example.meditopitestcase.api.model.BaseMessage
import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.data.Resource
import com.github.ajalt.timberkt.Timber
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MessageRepo @Inject constructor(
    private val api : Api,
    private val ioDispatchers : CoroutineContext
    ) : MessageRepoSource {
    override suspend fun fetchMessage(): Flow<Resource<BaseMessage>> {
        return flow {
            emit(Resource.loading(null))
            val item = api.fetchMessages()
            emit(Resource.success(item))
        }.catch {
            Timber.e {
                "error ${it.localizedMessage}"
            }
            emit(Resource.error(it.localizedMessage,null,it.cause))
        }.flowOn(ioDispatchers)
    }
}
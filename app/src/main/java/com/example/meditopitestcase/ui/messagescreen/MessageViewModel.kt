package com.example.meditopitestcase.ui.messagescreen


import com.example.meditopitestcase.api.model.Message
import com.example.meditopitestcase.data.Resource
import com.example.meditopitestcase.repo.remote.MessageRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meditopitestcase.api.model.BaseMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class MessageViewModel @Inject constructor(
    private val repo: MessageRepo
) : ViewModel() {

    private val _repoMessage: MutableLiveData<Resource<BaseMessage>> = MutableLiveData()


    fun fetchMessage(): LiveData<Resource<BaseMessage>> {
        viewModelScope.launch {
            repo.fetchMessage().collect{
                _repoMessage.postValue(it)
            }
        }

        return _repoMessage
    }
}
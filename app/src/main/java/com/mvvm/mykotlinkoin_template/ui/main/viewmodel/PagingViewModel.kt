package com.mvvm.mykotlinkoin_template.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eduvy.demo.base.BaseViewModel
import com.mvvm.mykotlinkoin_template.data.model.Player
import com.mvvm.mykotlinkoin_template.data.repository.MainRepository
import com.mvvm.mykotlinkoin_template.utils.NetworkHelper
import kotlinx.coroutines.flow.Flow

class PagingViewModel(
    private val repository: MainRepository,
    private val networkHelper: NetworkHelper,
) : BaseViewModel() {

    //using flow
    // This function is not observe when screen
  /*  fun getPagingDataFlow(): Flow<PagingData<Player>> {
        return repository.getPaginatedUsersFlow()
            .cachedIn(viewModelScope)
    }*/

    //This is for observing livedata
    val pagingData = repository.getPaginatedUsers().cachedIn(viewModelScope)

    //This is for collecting flow
    val pagingFlow = repository.getPaginatedUsersFlow()
        .cachedIn(viewModelScope)

}
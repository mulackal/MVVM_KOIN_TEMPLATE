package com.mvvm.mykotlinkoin_template.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.mvvm.mykotlinkoin_template.data.api.ApiService
import com.mvvm.mykotlinkoin_template.data.datasource.UserPagingSource
import com.mvvm.mykotlinkoin_template.data.model.Player
import kotlinx.coroutines.flow.Flow

class MainRepository(private val apiHelper: ApiService) {

    suspend fun getUsers() = apiHelper.getUsers()

    // paging data using Livedata
    fun getPaginatedUsers() =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiHelper) }
        ).liveData

    //Paging data using flow
    fun getPaginatedUsersFlow(): Flow<PagingData<Player>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(apiHelper) }
        ).flow

    companion object {
        const val NETWORK_PAGE_SIZE = 6
    }

}
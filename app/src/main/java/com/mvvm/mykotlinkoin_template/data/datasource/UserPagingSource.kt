package com.mvvm.mykotlinkoin_template.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mvvm.mykotlinkoin_template.data.api.ApiService
import com.mvvm.mykotlinkoin_template.data.model.Player
import com.mvvm.mykotlinkoin_template.utils.Constants.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, Player>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        try {
            val pageIndex = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getPlayers(params.loadSize,pageIndex)
            val data = response.data ?: emptyList()

            val prevKey = if (pageIndex == 1) null else pageIndex - 1

            return LoadResult.Page(
                data = data,
                prevKey = prevKey,
                nextKey = pageIndex.plus(1)
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition
    }
}
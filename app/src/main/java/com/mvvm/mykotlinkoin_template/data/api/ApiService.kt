package com.mvvm.mykotlinkoin_template.data.api

import com.mvvm.mykotlinkoin_template.data.model.Players
import com.mvvm.mykotlinkoin_template.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    //This is api for pagination
//    @GET("api/users")
//    suspend fun getPagingUsers(
//        @Query("page") page: Int
//    ): Response<PagingResponse>

    @GET("players")
    suspend fun getPlayers(
        @Query("per_page") per_page: Int?,
        @Query("page") page: Int?,
    ): Players

}
package com.instory.latest.api

import com.instory.latest.trendingrepo.data.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Lego REST API access points
 */
interface TopGithubService {

    companion object {
        const val ENDPOINT = "https://github-trending-api.now.sh/"
    }


    @GET("developers")
    suspend fun getTrendingRepositories(@Query("language") language: String, @Query("since") since: String): Response<List<User>>

}
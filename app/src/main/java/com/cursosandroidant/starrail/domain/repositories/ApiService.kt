package com.cursosandroidant.starrail.domain.repositories

import com.cursosandroidant.starrail.data.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    suspend fun getPosts(): List<Post>
}
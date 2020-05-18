package com.hh.gitbub.data.api

import com.hh.gitbub.data.model.Repository
import com.hh.gitbub.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/users")
    fun getUserList() : Call<List<User>>

    @GET ("/users/{login}/repos")
    fun getRepositoryList(@Path("login")userLogin : String): Call<List<Repository>>
}
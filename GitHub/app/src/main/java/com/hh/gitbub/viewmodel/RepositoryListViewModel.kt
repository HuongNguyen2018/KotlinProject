package com.hh.gitbub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hh.gitbub.data.api.ApiService
import com.hh.gitbub.data.model.Repository
import com.hh.gitbub.ui.adapter.RepositoryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class RepositoryListViewModel: ViewModel() {

    private var repositoryList = MutableLiveData<List<Repository>>()
    @Inject
    lateinit var apiService : ApiService

    @Inject
    lateinit var repositoryAdapter: RepositoryAdapter
    fun getRepositoryList(login: String) : LiveData<List<Repository>>{
        apiService.getRepositoryList(login).enqueue(object : Callback<List<Repository>>{
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                repositoryList.postValue(ArrayList<Repository>())
            }

            override fun onResponse(
                call: Call<List<Repository>>,
                response: Response<List<Repository>>
            ) {
                if (response != null) {
                    repositoryList.value = response.body()
                    repositoryList.postValue(response.body())
                    repositoryAdapter.setRepositoryData(response.body())
                   // repositoryAdapter.setRepositoryData(repositoryList.value)
                }
            }
        })

        return repositoryList
    }


}
package com.hh.gitbub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hh.gitbub.data.api.ApiService
import com.hh.gitbub.data.model.User
import com.hh.gitbub.ui.adapter.RecyclerViewAdapter
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class UserListViewModel : ViewModel() {

    @Inject
     lateinit var mApiService: ApiService

    @Inject
     lateinit var mRecyclerViewAdapter: RecyclerViewAdapter

//    init {
//        getUserList()
//    }

    fun getUserList() : LiveData<List<User>>{

        var listdata = MutableLiveData<List<User>>()
        mApiService.getUserList().enqueue(object :
            retrofit2.Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response!= null) {
                    val userList = response.body()
                    userList?.let {  mRecyclerViewAdapter.setData(it)}
                    return listdata.postValue(userList)
                }
            }

        })
        return listdata
    }

}
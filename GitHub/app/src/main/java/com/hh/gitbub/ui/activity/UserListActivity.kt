package com.hh.gitbub.ui.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.hh.gitbub.HHApplication
import com.hh.gitbub.R
import com.hh.gitbub.data.model.User
import com.hh.gitbub.di.component.ApplicationComponent
import com.hh.gitbub.di.component.DaggerUserComponent
import com.hh.gitbub.di.module.UserListModule
import com.hh.gitbub.ui.adapter.RecyclerViewAdapter
import com.hh.gitbub.util.Const
import com.hh.gitbub.viewmodel.UserListViewModel

import kotlinx.android.synthetic.main.activity_user_list.*
import javax.inject.Inject

class UserListActivity : AppCompatActivity(), RecyclerViewAdapter.AdapterClickListener<User> {

    @Inject
    lateinit var viewModel: UserListViewModel

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setSupportActionBar(toolbar)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

      //  var viewModel =  ViewModelProvider(this).get(UserListViewModel::class.java)


        val applicationComponent : ApplicationComponent = HHApplication.component
        applicationComponent.inject(this.application)

        var userComponent = DaggerUserComponent.builder()
            .userListModule(UserListModule(this))
            .applicationComponent(applicationComponent)
            .build()
        userComponent.inject(this)
        userComponent.injectData(viewModel)

        viewModel.getUserList().observe(this, Observer<List<User>>{
            if (it != null) {
                recyclerView.adapter = viewModel.mRecyclerViewAdapter
            }
        })





    }

    override fun onClick(item: User) {
        var intent = Intent(this, RepoListActivity::class.java)
        intent.putExtra(Const.KEY_LOGIN, item.login)

        startActivity(intent)

    }

}

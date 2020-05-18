package com.hh.gitbub.ui.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hh.gitbub.HHApplication
import com.hh.gitbub.R
import com.hh.gitbub.data.model.Repository
import com.hh.gitbub.di.component.DaggerRepositoryComponent
import com.hh.gitbub.util.Const
import com.hh.gitbub.viewmodel.RepositoryListViewModel
import javax.inject.Inject

class RepoListActivity : AppCompatActivity(){

    private var login : String? = null
    @Inject
    lateinit var viewModel: RepositoryListViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
      //  window.requestFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo_list)
        setupToolbar()
        if (intent != null) {
            login = intent.getStringExtra(Const.KEY_LOGIN)
        }
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        var applicationComponent = HHApplication.component
        var repositoryComponent = DaggerRepositoryComponent.builder()
            .applicationComponent(applicationComponent)
            .build()
        repositoryComponent.injectActivity(this)
        repositoryComponent.inject(viewModel)
        login?.let {
            viewModel.getRepositoryList(login!!).observe(this, Observer {
                if (it != null) {
                    recyclerView.adapter = viewModel.repositoryAdapter
                }
            })
        }

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setupToolbar() {
        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(getString(R.string.back))
        toolbar.setNavigationIcon(android.R.drawable.arrow_down_float)
        toolbar.setOnClickListener(object: View.OnClickListener{
            override fun onClick(p0: View?) {
                onBackPressed()
            }
        })
    }


}

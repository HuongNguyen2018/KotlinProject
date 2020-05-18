package com.hh.gitbub.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hh.gitbub.BR
import com.hh.gitbub.data.model.Repository

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.MyViewHolder>() {


    private var repositoryList : List<Repository>? = null

    class MyViewHolder(val databinding: ViewDataBinding) : RecyclerView.ViewHolder(databinding.root) {
        fun bind(resposity: Repository) {
            databinding.setVariable(BR.repository, resposity)
            databinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryAdapter.MyViewHolder {

        var layoutInflater = LayoutInflater.from(parent.context)
        var dataBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, com.hh.gitbub.R.layout.content_repository_list_item,parent, false)

        return MyViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RepositoryAdapter.MyViewHolder, position: Int) {
        repositoryList?.let {
        holder.bind(it.get(position))
        }
    }

    override fun getItemCount(): Int {
        return repositoryList?.size ?: 0
    }

    fun setRepositoryData(resposityList: List<Repository>?) {
        this.repositoryList = resposityList?: ArrayList<Repository>()
        notifyDataSetChanged()

    }

}
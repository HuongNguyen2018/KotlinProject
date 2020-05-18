package com.hh.gitbub.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hh.gitbub.BR
import com.hh.gitbub.data.model.User
import javax.inject.Inject


class RecyclerViewAdapter() : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var mClickListener: AdapterClickListener<User>? = null
    private var mListItem: List<User>? = null
    @Inject
    constructor(clickListener: AdapterClickListener<User>) : this() {

        this.mClickListener = clickListener

    }

    fun setData(listItem: List<User>) {
        this.mListItem = listItem
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.getContext())
        val itemBinding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, com.hh.gitbub.R.layout.content_user_list_item, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return mListItem?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return com.hh.gitbub.R.layout.content_user_list_item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding?.setVariable(BR.user, mListItem!!.get(position))
        holder.itemView.setOnClickListener(View.OnClickListener { mClickListener?.onClick(mListItem!!.get(position)) })
        holder.dataBinding?.executePendingBindings()
    }
    inner class ViewHolder(var dataBinding: ViewDataBinding?): RecyclerView.ViewHolder(dataBinding!!.root){

    }
    interface AdapterClickListener<User> {
        fun onClick(item : User)
    }
}
package com.example.githubrepos.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.databinding.ListItemUserBinding


class UserListAdapter(val viewModel: UserViewModel) :
    PagedListAdapter<GithubUser, UserListAdapter.UserViewHolder>(
        GithubUserDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it, viewModel) }

    }

    class UserViewHolder(private val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(githubUser: GithubUser, viewModel: UserViewModel) {
            binding.apply {
                user = githubUser
                executePendingBindings()
            }
            itemView.setOnClickListener { viewModel.selectItemEvent.value = githubUser }
        }
    }

}

private class GithubUserDiffCallback : DiffUtil.ItemCallback<GithubUser>() {
    override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean {
        return oldItem.imageUrl.equals(newItem.imageUrl, true) and
                oldItem.repoUrl.equals(newItem.repoUrl, true)
    }

}



package com.example.githubrepos.ui.users.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.data.GithubRepo
import com.example.githubrepos.databinding.ListItemRepoBinding


class UserRepoAdapter(private val viewModel: UserRepoViewModel) :
    PagedListAdapter<GithubRepo, UserRepoAdapter.UserRepoViewHolder>(
        GithubUserDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepoViewHolder {

        return UserRepoViewHolder(
            ListItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserRepoViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bind(it, viewModel) }

    }

    class UserRepoViewHolder(private val binding: ListItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(githubUser: GithubRepo, viewModel: UserRepoViewModel) {
            binding.apply {
                repository = githubUser
                executePendingBindings()
            }
        }
    }

}

private class GithubUserDiffCallback : DiffUtil.ItemCallback<GithubRepo>() {
    override fun areItemsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.repoId == newItem.repoId
    }

    override fun areContentsTheSame(oldItem: GithubRepo, newItem: GithubRepo): Boolean {
        return oldItem.repoName.equals(newItem.repoName, true) and
                oldItem.repoUrl.equals(newItem.repoUrl, true)
    }

}
package com.example.githubrepos.ui.users.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.databinding.ActivityUserRepoListBinding
import com.example.githubrepos.di.injectViewModel
import com.example.githubrepos.ui.users.UserListAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class UserRepoActivity : AppCompatActivity() {

    lateinit var viewModel: UserRepoViewModel
    lateinit var binding: ActivityUserRepoListBinding
    lateinit var adapter: UserRepoAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_repo_list)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onResume() {
        super.onResume()
        setupUI()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserRepoAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

}
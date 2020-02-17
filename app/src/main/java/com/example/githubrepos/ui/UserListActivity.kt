package com.example.githubrepos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.R
import com.example.githubrepos.data.GithubUser
import com.example.githubrepos.databinding.ActivityUserListBinding
import com.example.githubrepos.di.injectViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class UserListActivity : AppCompatActivity() {

    lateinit var viewModel: UserViewModel
    lateinit var binding: ActivityUserListBinding
    lateinit var adapter: UserListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_list)
        viewModel = injectViewModel(viewModelFactory)
    }

    override fun onResume() {
        super.onResume()
        setupUI()
        observeList()
    }

    private fun observeList() {

        viewModel.userList.observe(this,
            Observer<PagedList<GithubUser>> { t -> adapter.submitList(t) })

    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }


}

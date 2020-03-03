package com.example.githubrepos.ui.users

import android.os.Bundle
import android.widget.Toast
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
        viewModel.getUsers()
        observeList()
    }

    private fun observeList() {

        viewModel.userList.observe(this,
            Observer<PagedList<GithubUser>> { t -> adapter.submitList(t) })

        viewModel.selectItemEvent.observe(this,
            Observer {
                Toast.makeText(this, "${it.userName} was clicked", Toast.LENGTH_SHORT).show()
            })

    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserListAdapter(viewModel)
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }

}

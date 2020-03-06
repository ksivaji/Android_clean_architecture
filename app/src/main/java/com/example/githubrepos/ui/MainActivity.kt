package com.example.githubrepos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amazonaws.mobile.client.UserState
import com.example.githubrepos.R
import com.example.githubrepos.api.Result
import com.example.githubrepos.databinding.ActivityMainBinding
import com.example.githubrepos.di.injectViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = injectViewModel(viewModelFactory)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.userState.observe(this, Observer<Result<UserState?>> { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.currentState.text = "Loading..."
                    //TODO: show spinner
                }
                Result.Status.SUCCESS -> {
                    binding.currentState.text = result.data!!.toString()
                    /**
                     * TODO:
                     * Hide Spinner
                     * Navigate to next screen
                     */

                }
                Result.Status.ERROR -> {
                    binding.currentState.text = "Unknown"
                    /**
                     * TODO:
                     * Hide Spinner
                     * Show error message
                     */
                }
            }
        })
    }
}

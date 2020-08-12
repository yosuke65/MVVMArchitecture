package com.example.mvvmarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmarchitecture.adapters.AdapterRepoList
import com.example.mvvmarchitecture.api.ApiClient
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.api.Endpoint
import com.example.mvvmarchitecture.base.BaseApplication
import com.example.mvvmarchitecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter:AdapterRepoList

    @Inject
    lateinit var mainRepository: MainRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        //DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Dagger
        val baseApplication = application as BaseApplication
        baseApplication.getAppComponent().inject(this)

        //ViewModel
        viewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository
        )
        ).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        observerData()

        //RecyclerView
        myAdapter = AdapterRepoList(this)
        recyclerview_main.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMain.apply {
            adapter = myAdapter
        }
        //SwipeRefresh
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.onButtonClicked()
        }
    }

    private fun observerData(){
        viewModel.getRepoObserver().observe(this, Observer {
            binding.swipeRefresh.isRefreshing = false
            if(it != null){
                 myAdapter.setData(it)
            }
        })
    }
}
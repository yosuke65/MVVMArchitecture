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
import com.example.mvvmarchitecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter:AdapterRepoList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //ViewModel
        viewModel = ViewModelProvider(this,MainViewModelFactory(MainRepository(ApiClient.getApiEndpoint()))
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
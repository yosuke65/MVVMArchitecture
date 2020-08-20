package com.example.mvvmarchitecture.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmarchitecture.adapters.AdapterRepoList
//import com.example.mvvmarchitecture.api.ApiClient
import com.example.mvvmarchitecture.R
import com.example.mvvmarchitecture.api.Endpoint
import com.example.mvvmarchitecture.base.BaseApplication
import com.example.mvvmarchitecture.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    companion object{
        private const val TAG = "MainActivity"
    }

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

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: MainActiviti destroyed")
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
            doTask()
        })
    }

    private fun doTask(){
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000L)
//            withContext(Dispatchers.Main){
//                binding.swipeRefresh.isRefreshing = true
//                ting("Operation Start")
//            }
            ting("Op Started")
            delay(5000L)
//            withContext(Dispatchers.Main){
//                binding.swipeRefresh.isRefreshing = false
//                ting("Operation End")
//            }
            ting("Op Ended")
        }
    }

    private suspend fun ting(msg:String) = withContext(Dispatchers.Main){
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

}
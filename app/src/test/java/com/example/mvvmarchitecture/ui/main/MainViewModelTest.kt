package com.example.mvvmarchitecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmarchitecture.models.RepositoryItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.runners.MockitoJUnitRunner
import javax.inject.Inject


/**
 * 1. No concurrent task can be tested using unit test case
 * 2. Unit test doesn't require any device(virtual or actual mobile)
 * 3. 80% of your code should be covered in Unit test case
 */
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var mainRepository: MainRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
//    @Spy
//    lateinit var mainRepository: MainRepository

    @Before
    fun setUp() {
        println("Before the test case")
//        MockitoAnnotations.initMocks(MainRepository::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun test_repository_data()  = runBlocking{
        var list = ArrayList<RepositoryItem>()
        list.add(
            RepositoryItem(
                author = "test1_author",
                description = "test1_desc",
                language = "en",
                name = "test1",
                avatar = "",
                url = "",
                stars = 5
            )
        )
        list.add(
            RepositoryItem(
                author = "test2_author",
                description = "test2_desc",
                language = "en",
                name = "test2",
                avatar = "",
                url = "",
                stars = 2
            )
        )
        println(list?.size)
        Mockito.doReturn(list).`when`(mainRepository).getDataFromApiSuspend()
        mainViewModel.getDataFromApi()
        val result = mainViewModel.getRepoObserver().value
        println(result?.size)
        assert(result?.size == 2)
    }

//    @Test
//    fun onButtonClicked() {
//        runBlocking {
//            Mockito.doNothing().`when`(mainRepository).getDataFromApiSuspend()
//            mainViewModel.onButtonClicked()
//            Mockito.verify(mainRepository).getDataFromApiSuspend()
//        }
//    }


    @After
    fun cleanUp() {
        println("After the test case")
    }
}
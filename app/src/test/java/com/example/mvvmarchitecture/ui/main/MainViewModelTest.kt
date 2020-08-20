package com.example.mvvmarchitecture.ui.main

import com.example.mvvmarchitecture.models.RepositoryItem
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner


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
//
//    @Spy
//    lateinit var mainRepositoryActual: MainRepository

    @Before
    fun setUp() {
        println("Before the test case")
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun test_repository_data()  = runBlocking{
        val list = ArrayList<RepositoryItem>()
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
        Mockito.doReturn(list).`when`(mainRepository).getDataFromApiSuspend()
        mainViewModel.getDataFromApi()
        val result = mainViewModel.getRepoObserver().value
        assert(result?.size == 2)
    }

    @Test
    fun onButtonClicked() {
        runBlocking {
            Mockito.doNothing().`when`(mainRepository).getDataFromApiSuspend()
            mainViewModel.onButtonClicked()
            Mockito.verify(mainRepository).getDataFromApiSuspend()
        }
    }


    @After
    fun cleanUp() {
        println("After the test case")
    }
}
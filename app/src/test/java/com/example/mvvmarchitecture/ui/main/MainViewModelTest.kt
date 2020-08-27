package com.example.mvvmarchitecture.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvmarchitecture.models.RepositoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner;


/**
 * 1. No concurrent task can be tested using unit test case
 * 2. Unit test doesn't require any device(virtual or actual mobile)
 * 3. 80% of your code should be covered in Unit test case
 */
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
//    @Spy
    @Mock
    lateinit var mainRepository: MainRepository


    //    //A JUnit Test Rule that swaps the background executor used by the Architecture Components with a different one which executes each task synchronously.
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()


    private val testCoroutineThread = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        println("Before the test case")
        Dispatchers.setMain(testCoroutineThread)
//        mainRepository = Mockito.mock(MainRepository::class.java)
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun test_repository_data() {

        runBlocking {
            println(getDummyData()?.size)

            `when`(mainRepository.getDataFromApiSuspend())
                .thenReturn(getDummyData())
            mainViewModel.getDataFromApi()
            val result = mainViewModel.getRepoObserver().value
            println(result?.size)
            assert(result?.size == 2)
        }

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
        Dispatchers.resetMain()
        testCoroutineThread.cleanupTestCoroutines()
    }

    private fun getDummyData(): List<RepositoryItem>? {
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
        return list
    }
}
package com.example.mvvmarchitecture.ui.main

import com.example.mvvmarchitecture.api.ApiClient
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
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
        val mainRepository = MainRepository(ApiClient.getApiEndpoint())
        mainViewModel = MainViewModel(mainRepository)
    }

    @Test
    fun onButtonClicked() {
        Mockito.doNothing().`when`(mainRepository)
        mainViewModel.onButtonClicked()
        Mockito.verify(mainRepository).getDataFromApi()
    }

    @Test
    fun welcomeMessage() {
        val myMsg = "Hello"
        Mockito.`when`(mainRepository.getWelcomeMsg()).thenReturn(myMsg)
        val msg = mainRepository.getWelcomeMsg()
        Assert.assertEquals(myMsg, msg)
    }

    @Test
    fun testIsNUmberOdd(){
        Assert.assertEquals(true, mainViewModel.isNumberOdd(35))
        Assert.assertEquals(true, mainViewModel.isNumberOdd(15))
        Assert.assertEquals(false, mainViewModel.isNumberOdd(2))
    }
    @After
    fun cleanUp(){
        println("After the test case")
    }
}
package com.mobilne.civ2077

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilne.civ2077.ui.board.views.buyGold.BuyGoldViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class BuyGoldVMUnitTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var buyGoldViewModel: BuyGoldViewModel

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        buyGoldViewModel = BuyGoldViewModel()
    }

    @Test
    fun onTextChangedTest() {
        runBlocking {
            buyGoldViewModel.onTextChanged("10")
            var goldToBuy = buyGoldViewModel.goldToBuy
            var euroToPay = buyGoldViewModel.euroToPay
            assertEquals("10", goldToBuy)
            assertEquals("20", euroToPay)

            buyGoldViewModel.onTextChanged("")
            goldToBuy = buyGoldViewModel.goldToBuy
            euroToPay = buyGoldViewModel.euroToPay
            assertEquals("", goldToBuy)
            assertEquals("0", euroToPay)

            buyGoldViewModel.onTextChanged("2000000")
            goldToBuy = buyGoldViewModel.goldToBuy
            euroToPay = buyGoldViewModel.euroToPay
            assertEquals("1000000", goldToBuy)
            assertEquals("2000000", euroToPay)
        }
    }
}
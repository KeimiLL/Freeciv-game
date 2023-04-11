package com.mobilne.civ2077

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilne.civ2077.ui.board.views.army.ArmyViewModel
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
class ArmyVMUnitTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var armyViewModel: ArmyViewModel

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        armyViewModel = ArmyViewModel()
    }

    @Test
    fun onUnitsChangedTest() {
        runBlocking {
            armyViewModel.onUnitsChanged("10")
            var unitsCount = armyViewModel.unitsCount
            var goldToPay = armyViewModel.goldToPay
            assertEquals("10", unitsCount)
            assertEquals("100", goldToPay)

            armyViewModel.onUnitsChanged("")
            unitsCount = armyViewModel.unitsCount
            goldToPay = armyViewModel.goldToPay
            assertEquals("", unitsCount)
            assertEquals("0", goldToPay)
        }
    }

    @Test
    fun onXChangeTest() {
        runBlocking {
            armyViewModel.onXChange("5")
            var destinationX = armyViewModel.destinationX
            assertEquals("5", destinationX)

            armyViewModel.onXChange("10")
            destinationX = armyViewModel.destinationX
            assertEquals("10", destinationX)

            armyViewModel.onXChange("15")
            destinationX = armyViewModel.destinationX
            assertEquals("10", destinationX)
        }
    }

    @Test
    fun onYChangeTest() {
        runBlocking {
            armyViewModel.onYChange("5")
            var destinationY = armyViewModel.destinationY
            assertEquals("5", destinationY)

            armyViewModel.onYChange("10")
            destinationY = armyViewModel.destinationY
            assertEquals("10", destinationY)

            armyViewModel.onYChange("15")
            destinationY = armyViewModel.destinationY
            assertEquals("10", destinationY)
        }
    }
}
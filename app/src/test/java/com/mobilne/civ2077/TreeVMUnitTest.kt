package com.mobilne.civ2077

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mobilne.civ2077.ui.board.views.tree.TreeViewModel
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
class TreeVMUnitTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var treeViewModel: TreeViewModel

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        treeViewModel = TreeViewModel()
    }

    @Test
    fun changeForEconomyPerkTest() {
        runBlocking {
            treeViewModel.changeForEconomyPerk()
            val buyButtonState = treeViewModel.buyButtonState
            val currentPerk = treeViewModel.currentPerk
            assertEquals(true, buyButtonState)
            assertEquals(treeViewModel.economyDescription, currentPerk)
        }
    }

    @Test
    fun changeForArmyPerkTest() {
        runBlocking {
            treeViewModel.changeForArmyPerk()
            val buyButtonState = treeViewModel.buyButtonState
            val currentPerk = treeViewModel.currentPerk
            assertEquals(true, buyButtonState)
            assertEquals(treeViewModel.armyPerkDescription, currentPerk)
        }
    }

    @Test
    fun buyTest() {
        runBlocking {
            treeViewModel.currentPerk = treeViewModel.armyPerkDescription
            treeViewModel.buy()
            val armyPerks = treeViewModel.armyPerks
            val economyPerksButtonsState = treeViewModel.economyPerksButtonsState
            assertEquals(1, armyPerks)
            assertEquals(true, economyPerksButtonsState[0])
            assertEquals(false, economyPerksButtonsState[1])
            assertEquals(false, economyPerksButtonsState[2])
            assertEquals(false, economyPerksButtonsState[3])

            treeViewModel.currentPerk = treeViewModel.economyDescription
            treeViewModel.buy()
            val economyPerks = treeViewModel.economyPerks
            val armyPerksButtonsState = treeViewModel.armyPerksButtonsState
            assertEquals(1, economyPerks)
            assertEquals(false, armyPerksButtonsState[0])
            assertEquals(true, armyPerksButtonsState[1])
            assertEquals(false, armyPerksButtonsState[2])
            assertEquals(false, armyPerksButtonsState[3])
        }
    }
}
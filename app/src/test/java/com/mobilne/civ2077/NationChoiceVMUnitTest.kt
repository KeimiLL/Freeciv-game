package com.mobilne.civ2077

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.graphics.Color
import com.mobilne.civ2077.ui.nation_choice.NationChoiceViewModel
import com.mobilne.civ2077.util.constants.Nations
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
class NationChoiceVMUnitTest {

    private val testDispatcher = StandardTestDispatcher()
    lateinit var nationChoiceViewModel: NationChoiceViewModel

    @get:Rule
    val instantTaskExecutionRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        nationChoiceViewModel = NationChoiceViewModel()
    }

    @Test
    fun onNationChangeTest() {
        runBlocking {
            nationChoiceViewModel.onNationChange("France", R.drawable.france)
            val nation = nationChoiceViewModel.currentNation
            val drawableId = nationChoiceViewModel.drawableId
            assertEquals("France", nation)
            assertEquals(R.drawable.france, drawableId)
        }
    }

    @Test
    fun getBorderForNationCardTest() {
        runBlocking {
            nationChoiceViewModel.onNationChange("Spain", R.drawable.spain)
            val borderNotSelected = nationChoiceViewModel.getBorderForNationCard(Nations.FRANCE)
            nationChoiceViewModel.onNationChange("France", R.drawable.france)
            val borderSelected = nationChoiceViewModel.getBorderForNationCard(Nations.FRANCE)
            assertEquals(borderSelected, Color.Blue)
            assertEquals(borderNotSelected, Color.White)
        }
    }
}
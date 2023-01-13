package com.mobilne.civ2077.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.auth.LoginScreen
import com.mobilne.civ2077.ui.auth.SignupScreen
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.ui.board.FullBoardView
import com.mobilne.civ2077.ui.home.HomeScreen
import com.mobilne.civ2077.ui.nation_choice.NationChoice
import com.mobilne.civ2077.ui.nation_choice.NationChoiceViewModel

@Composable
fun AppNavHost(
    authViewModel: AuthViewModel,
    boardViewModel: BoardViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_LOGIN
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ROUTE_LOGIN) {
            LoginScreen(authViewModel, boardViewModel, navController)
        }
        composable(ROUTE_SIGNUP) {
            SignupScreen(authViewModel, navController)
        }
        composable(ROUTE_HOME) {
            HomeScreen(authViewModel, navController)
        }
        composable(ROUTE_BOARD) {
            FullBoardView(viewModel, navController)
        }
        composable(ROUTE_NATIONS) {
            NationChoice(viewModel, navController, NationChoiceViewModel())
        }
    }
}
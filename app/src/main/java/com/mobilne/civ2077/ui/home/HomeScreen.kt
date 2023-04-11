package com.mobilne.civ2077.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_HOME
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.ui.theme.spacing


@Composable
fun HomeScreen(
    viewModel: AuthViewModel?,
    navController: NavHostController,
    boardViewModel: BoardViewModel
) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.medium)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(spacing.extraLarge))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = viewModel?.currentUser?.displayName ?: "",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xffe86d01)
            )
        }

        when (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation) {
            "France" -> {
                Image(
                    modifier = Modifier.width(120.dp),
                    painter = painterResource(id = R.drawable.france),
                    contentDescription = "Nation flag"
                )
            }
            "Spain" -> {
                Image(
                    modifier = Modifier.width(120.dp),
                    painter = painterResource(id = R.drawable.spain),
                    contentDescription = "Nation flag"
                )
            }
            "UK" -> {
                Image(
                    modifier = Modifier.width(120.dp),
                    painter = painterResource(id = R.drawable.uk),
                    contentDescription = "Nation flag"
                )
            }
            "USA" -> {
                Image(
                    modifier = Modifier.width(120.dp),
                    painter = painterResource(id = R.drawable.usa),
                    contentDescription = "Nation flag"
                )
            }
        }

        Column(
            modifier = Modifier
                .width(500.dp)
                .wrapContentHeight()
                .padding(spacing.medium),
            horizontalAlignment = Alignment.Start
        ) {
            // Email
            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Email: ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = viewModel?.currentUser?.email ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nation: ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Perk: ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))
                when (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation) {
                    "France" -> {
                        Text(
                            text = "Your army has 2 extra steps each turn",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "Spain" -> {
                        Text(
                            text = "Each turn gives additional 100 gold",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "UK" -> {
                        Text(
                            text = "Each perk costs 10% less",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "USA" -> {
                        Text(
                            text = "Your army deals 10% more damage",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

            }
        }

        Row(
            modifier = Modifier.wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Dark mode",
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background),
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = boardViewModel.isDarkModeOn.value,
                onCheckedChange = {
                    boardViewModel.toggleDarkMode()
                },
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    viewModel?.logout()
                    navController.navigate(ROUTE_LOGIN) {
                        popUpTo(ROUTE_HOME) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = spacing.extraLarge)
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text(text = stringResource(id = R.string.logout))
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(
                onClick = {
                    navController.navigate(ROUTE_BOARD) {
                        popUpTo(ROUTE_HOME) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(top = spacing.extraLarge)
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text(text = "Back")
            }
        }
    }
}
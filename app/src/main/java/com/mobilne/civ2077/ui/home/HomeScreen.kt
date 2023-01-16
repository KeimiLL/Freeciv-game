package com.mobilne.civ2077.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_HOME
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.ui.theme.spacing


@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController, boardViewModel: BoardViewModel) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.medium)
            .padding(top = spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(10.dp))
            // miejsce na łączneie z nazwą usera
//            Text(
//                text = "Nazwa-usera",
//                style = MaterialTheme.typography.headlineLarge,
//                color = Color(0xffe86d01)
//            )

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
            "Uk" -> {
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
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .wrapContentHeight(),
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.Center
//            ) {
//                Text(
//                    text = stringResource(id = R.string.name),
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//                Spacer(modifier = Modifier.width(20.dp))
//                Text(
//                    text = viewModel?.currentUser?.displayName ?: "",
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//            }

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

            // Nacja
            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Nation: " + (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = boardViewModel.currentNationChoice,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Perk
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
                    "Uk" -> {
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
        // Przyciski wyloguj i wróć
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
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
                Text(text = "back")
            }
        }
    }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun HomeScreenPreviewLight() {
//    AppTheme {
//        HomeScreen(null, rememberNavController())
//    }
//}
//
//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun HomeScreenPreviewDark() {
//    AppTheme {
//        HomeScreen(null, rememberNavController())
//    }
//}
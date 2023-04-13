package com.mobilne.civ2077.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            .padding(spacing.small)
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 0.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                "Dark mode",
                style = TextStyle(fontSize = 17.sp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface),
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = viewModel?.currentUser?.displayName ?: "",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xffe86d01)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
            when (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation) {
                "France" -> {
                    Image(
                        modifier = Modifier.width(100.dp),
                        painter = painterResource(id = R.drawable.france),
                        contentDescription = "Nation flag"
                    )
                }
                "Spain" -> {
                    Image(
                        modifier = Modifier.width(100.dp),
                        painter = painterResource(id = R.drawable.spain),
                        contentDescription = "Nation flag"
                    )
                }
                "UK" -> {
                    Image(
                        modifier = Modifier.width(100.dp),
                        painter = painterResource(id = R.drawable.uk),
                        contentDescription = "Nation flag"
                    )
                }
                "USA" -> {
                    Image(
                        modifier = Modifier.width(100.dp),
                        painter = painterResource(id = R.drawable.usa),
                        contentDescription = "Nation flag"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
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
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "gmail",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = viewModel?.currentUser?.email ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.flag),
                    contentDescription = "flag",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Row(
                modifier = Modifier
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.thunder),
                    contentDescription = "thunder",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                when (boardViewModel.getPlayerByIndex(boardViewModel.currentPlayerIndex.value).nation) {
                    "France" -> {
                        Text(
                            text = "Your army has 2 extra steps each turn",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "Spain" -> {
                        Text(
                            text = "Each turn gives additional 100 gold",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "UK" -> {
                        Text(
                            text = "Each perk costs 10% less",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    "USA" -> {
                        Text(
                            text = "Your army deals 10% more damage",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(15.dp))
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
                    .height(50.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff67385A)),
                contentPadding = PaddingValues(12.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(8.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.log_out),
                    contentDescription = "logout",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.logout),
                    style = androidx.compose.material.MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(150.dp))
            Button(
                onClick = {
                    navController.navigate(ROUTE_BOARD) {
                        popUpTo(ROUTE_HOME) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(120.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff386745)),
                contentPadding = PaddingValues(12.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(8.dp),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "back",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Home",
                    style = androidx.compose.material.MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color(255, 255, 255),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
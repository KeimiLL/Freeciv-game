package com.mobilne.civ2077.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_HOME
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.theme.AppTheme
import com.mobilne.civ2077.ui.theme.spacing


@Composable
fun HomeScreen(viewModel: AuthViewModel?, navController: NavHostController) {
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
            Text(
                text = "Nazwa-usera",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xffe86d01)
            )

            Text(
                text = viewModel?.currentUser?.displayName ?: "",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(spacing.medium)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.name),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = viewModel?.currentUser?.displayName ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.email),
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
                Row(verticalAlignment = Alignment.CenterVertically,
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
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun HomeScreenPreviewLight() {
    AppTheme {
        HomeScreen(null, rememberNavController())
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDark() {
    AppTheme {
        HomeScreen(null, rememberNavController())
    }
}
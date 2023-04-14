package com.mobilne.civ2077.ui.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.data.Resource
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.navigation.ROUTE_NATIONS
import com.mobilne.civ2077.navigation.ROUTE_SIGNUP
import com.mobilne.civ2077.ui.board.BoardViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    authViewModel: AuthViewModel?,
    boardViewModel: BoardViewModel,
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginFlow = authViewModel?.loginFlow?.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthHeader(boardViewModel)

        Surface(modifier = Modifier,shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier.padding(15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.email),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text(
                            text = stringResource(id = R.string.password),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = false,
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff67385A)),
                        contentPadding = PaddingValues(5.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(8.dp),
                        onClick = {
                            authViewModel?.login(email, password)
                        },
                    ) {
                        Text(
                            text = stringResource(id = R.string.login),
                            color = Color.White,
                            style = androidx.compose.material.MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(40.dp))

                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .width(220.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff386745)),
                        contentPadding = PaddingValues(5.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(8.dp),
                        onClick = {
                            navController.navigate(ROUTE_SIGNUP) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        },
                    ) {
                        Text(
                            text = stringResource(id = R.string.dont_have_account),
                            color = Color.White,
                            style = androidx.compose.material.MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

    }
        loginFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator()
                }
                is Resource.Success -> {
                    CircularProgressIndicator()
                    LaunchedEffect(Unit) {
                        delay(1000L)
                        boardViewModel.checkAddingPlayer()

                        if (boardViewModel.didPlayerMakeNationChoice()) {
                            navController.navigate(ROUTE_BOARD) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        } else {
                            navController.navigate(ROUTE_NATIONS) {
                                popUpTo(ROUTE_LOGIN) { inclusive = true }
                            }
                        }
                    }
                }
            }
        }
    }
//}

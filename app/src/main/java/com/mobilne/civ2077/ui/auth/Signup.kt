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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.data.Resource
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.navigation.ROUTE_NATIONS
import com.mobilne.civ2077.navigation.ROUTE_SIGNUP
import com.mobilne.civ2077.ui.board.BoardViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    authViewModel: AuthViewModel?,
    boardViewModel: BoardViewModel,
    navController: NavHostController
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val signupFlow = authViewModel?.signupFlow?.collectAsState()

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
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.name))
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
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text(text = stringResource(id = R.string.email))
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
                        Text(text = stringResource(id = R.string.password))
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    visualTransformation = PasswordVisualTransformation(),
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
                        onClick = {
                            authViewModel?.signup(name, email, password)
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(120.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff67385A)),
                        contentPadding = PaddingValues(5.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(8.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.signup),
                            color = Color.White,
                            style = androidx.compose.material.MaterialTheme.typography.body2,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Button(
                        onClick = {
                            navController.navigate(ROUTE_LOGIN) {
                                popUpTo(ROUTE_SIGNUP) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .height(50.dp)
                            .width(220.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff386745)),
                        contentPadding = PaddingValues(5.dp),
                        shape = RoundedCornerShape(20.dp),
                        elevation = ButtonDefaults.elevation(8.dp),
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_have_account),
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
        signupFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier)
                }
                is Resource.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(ROUTE_NATIONS) {
                            popUpTo(ROUTE_SIGNUP) { inclusive = true }
                        }
                    }
                }
            }
        }

    }
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.mobilne.civ2077.R
import com.mobilne.civ2077.data.Resource
import com.mobilne.civ2077.navigation.ROUTE_BOARD
import com.mobilne.civ2077.navigation.ROUTE_LOGIN
import com.mobilne.civ2077.navigation.ROUTE_NATIONS
import com.mobilne.civ2077.navigation.ROUTE_SIGNUP
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.ui.theme.spacing
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

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
    ) {

        val (refHeader, refEmail, refPassword, refButtonLogin, refTextSignup, refLoader) = createRefs()
        val spacing = MaterialTheme.spacing

        Box(
            modifier = Modifier
                .constrainAs(refHeader) {
                    top.linkTo(parent.top, spacing.medium)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                }
                .wrapContentSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            AuthHeader(boardViewModel)
        }

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
            modifier = Modifier
                .constrainAs(refEmail) {
                    top.linkTo(refHeader.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
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
            modifier = Modifier
                .constrainAs(refPassword) {
                    top.linkTo(refEmail.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(refButtonLogin) {
                    top.linkTo(refPassword.bottom, spacing.medium)
                    start.linkTo(parent.start, spacing.large)
                    end.linkTo(parent.end, spacing.large)
                    width = Dimension.fillToConstraints
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp),
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
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(100.dp))
            Button(
                modifier = Modifier
                    .height(60.dp)
                    .width(250.dp),
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
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        loginFlow?.value?.let {
            when (it) {
                is Resource.Failure -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.exception.message, Toast.LENGTH_LONG).show()
                }
                Resource.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
                }
                is Resource.Success -> {
                    CircularProgressIndicator(modifier = Modifier.constrainAs(refLoader) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
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
}

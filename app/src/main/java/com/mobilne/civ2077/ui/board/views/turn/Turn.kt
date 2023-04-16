package com.mobilne.civ2077.ui.board.views.turn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R

@Composable
fun Turn(viewModel: TurnViewModel) {
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            viewModel.usersState()

            TurnHeader()

            PlayersState(viewModel)

            Spacer(modifier = Modifier.height(15.dp))
            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffa9891c)),
                contentPadding = PaddingValues(12.dp),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.elevation(8.dp),
                onClick = { viewModel.pass() },
                enabled = viewModel.duringTurn
            ) {
                Text(
                    text = viewModel.buttonText,
                    style = androidx.compose.material.MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun TurnHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "Players state",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Image(
            painter = painterResource(id = R.drawable.stage),
            contentDescription = "Stage",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
fun PlayersState(
    viewModel: TurnViewModel
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = viewModel.user1 + viewModel.user1State, //Todo nazwa gracza
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = viewModel.user2 + viewModel.user2State, //Todo nazwa gracza
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = viewModel.user3 + viewModel.user3State, //Todo nazwa gracza
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
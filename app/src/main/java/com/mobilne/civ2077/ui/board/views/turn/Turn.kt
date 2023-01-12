package com.mobilne.civ2077.ui.board.views.turn

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun Turn(viewModel: TurnViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFFffffff))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TurnHeader()

        PlayersState(viewModel)

        Divider()

        Button(
            modifier = Modifier.padding(vertical = 50.dp)
                .height(50.dp)
                .width(200.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(16.dp),
            onClick = { viewModel.pass() },
            enabled = viewModel.duringTurn
        ) {
            Text(
                text = viewModel.buttonText,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color(255, 255, 255)
            )
        }

    }
}

@Composable
fun TurnHeader(){
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = "Players state",
        style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
    )
}

@Composable
fun PlayersState(
    viewModel: TurnViewModel
){
    Column(
        modifier = Modifier
            .background(Color(0xFFffffff))
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "user1: " + viewModel.user1State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "user2: " + viewModel.user2State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "user3: " + viewModel.user3State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurface
        )

    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun TurnPreview() {
    AppTheme {
        Turn(viewModel = TurnViewModel())
    }
}
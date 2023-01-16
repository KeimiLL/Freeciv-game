package com.mobilne.civ2077.ui.board.views.turn

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R

@Composable
fun Turn(viewModel: TurnViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFFffffff))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        viewModel.usersState()

        TurnHeader()

        PlayersState(viewModel)

//        Divider()

        Button(
            modifier = Modifier.padding(vertical = 20.dp)
                .height(50.dp)
                .width(200.dp),
            shape = RoundedCornerShape(50.dp),
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
    Row(verticalAlignment = Alignment.CenterVertically){
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "Players state",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Image(
            painter = painterResource(id = R.drawable.stage),
            contentDescription = "Stage",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(50.dp)
        )
    }

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
            text = viewModel.user1 + viewModel.user1State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = viewModel.user2  + viewModel.user2State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = viewModel.user3 + viewModel.user3State, //Todo nazwa gracza
            style = androidx.compose.material3.MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

    }
}




//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun TurnPreview() {
//    AppTheme {
//        Turn(viewModel = TurnViewModel())
//    }
//}
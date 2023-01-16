package com.mobilne.civ2077.ui.board.views.endOfTurn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.board.BoardViewModel

@Composable
fun EndOfTurn(boardViewModel: BoardViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Turn summary",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.summary),
                    contentDescription = "Summary",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(30.dp))

            //User 1
            Text(text = "User 1:",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Gold:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user1.gold)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Army:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user1.units)
            }
            Spacer(modifier = Modifier.width(30.dp))

            // User 2
            Text(text = "User 2:",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Gold:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user2.gold)

            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Army:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user2.units)
            }
            Spacer(modifier = Modifier.width(30.dp))

            // User 3
            Text(text = "User 3:",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black)
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Gold:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user3.gold)

            }
            Spacer(modifier = Modifier.width(5.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center) {
                Text(text = "Army:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black)
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user3.units)
            }
        }
    }
}

@Composable
fun DisplayValue(value: Int) {
    if (value > 0) {
        Text(text = value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Green)
    } else if (value == 0) {
        Text(text = value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black)
    } else {
        Text(text = value.toString(),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red)
    }
}
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
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.board.BoardViewModel

@Composable
fun EndOfTurn(boardViewModel: BoardViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Turn summary",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.summary),
                    contentDescription = "Summary",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Gold
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gold),
                    contentDescription = "Gold",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Gold Summary:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User1:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                var gold = 100
                if (boardViewModel.getPlayerByIndex(1).nation == "Spain")
                    gold = 200
                DisplayValue(boardViewModel.onWar.value.user1.gold + gold)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User2:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                var gold = 100
                if (boardViewModel.getPlayerByIndex(2).nation == "Spain")
                    gold = 200
                DisplayValue(boardViewModel.onWar.value.user2.gold + gold)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User3:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                var gold = 100
                if (boardViewModel.getPlayerByIndex(3).nation == "Spain")
                    gold = 200
                DisplayValue(boardViewModel.onWar.value.user3.gold + gold)
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Gold
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.army),
                    contentDescription = "War",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "War Summary:",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Black
                )

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User1:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user1.units)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User2:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user2.units)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "User3:",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(10.dp))
                DisplayValue(boardViewModel.onWar.value.user3.units)
            }
            Spacer(modifier = Modifier.height(10.dp))
            SummInfo(boardViewModel.wasWarLastTurn.value)
        }
    }
}

@Composable
fun SummInfo(wasWar: Boolean = true) {
    Row(verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center) {
        if(wasWar){
            Image(
                painter = painterResource(id = R.drawable.blood),
                contentDescription = "Blood",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "It was a bloody day in the world of Civilization 2077",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black)
        } else {
            Image(
                painter = painterResource(id = R.drawable.peace),
                contentDescription = "Peace",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "It was a peaceful day in the world of Civilization 2077",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black)
        }
    }
}

@Composable
fun DisplayValue(value: Int = 0) {
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
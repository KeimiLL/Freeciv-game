package com.mobilne.civ2077.ui.board.views.army

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R

@Composable
fun Army(viewModel: ArmyViewModel) {
    Column(
        modifier = Modifier
            .background(Color(0xFFffffff))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //BuyArmy
        viewModel.changeMaxRange()
        BuyArmyHeader()
        BuyArmy(
            valueGoldToPay = viewModel.goldToPay,
            valueUnits = viewModel.unitsCount,
            onValueChanged = { viewModel.onUnitsChanged(it) },
            viewModel = viewModel,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        //Send Army
        SendArmyHeader(
            step = viewModel.maxRange
        )
        Coordinates(
            valueX = viewModel.destinationX,
            onValueXChanged = { viewModel.onXChange(it) },
            valueY = viewModel.destinationY,
            onValueYChanged = { viewModel.onYChange(it) },
            viewModel = viewModel,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
    }
}

@Composable
fun BuyArmyHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = "Buy Units",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(width = 10.dp))
        Image(
            painter = painterResource(id = R.drawable.buyunits),
            contentDescription = "Buy units",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(30.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyArmy(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    valueUnits: String,
    valueGoldToPay: String,
    onValueChanged: (String) -> Unit,
    viewModel: ArmyViewModel
) {
    Row(
        modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.padding(vertical = 10.dp),
                value = valueUnits,
                onValueChange = onValueChanged,
                keyboardOptions = keyboardOptions
            )
        }
        Button(
            onClick = { viewModel.buy() },
        ) {
            Text("Buy for $valueGoldToPay")
        }
    }
}


@Composable
fun SendArmyHeader(
    step: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.padding(vertical = 3.dp),
                text = "Move Army",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Spacer(modifier = Modifier.width(width = 10.dp))
            Image(
                painter = painterResource(id = R.drawable.movearmy),
                contentDescription = "Move army",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(30.dp)
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 0.dp),
            text = "Max ${step} step(s) in each direction",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coordinates(
    modifier: Modifier = Modifier, keyboardOptions: KeyboardOptions = KeyboardOptions.Default,

    valueX: String, onValueXChanged: (String) -> Unit,

    valueY: String, onValueYChanged: (String) -> Unit,

    viewModel: ArmyViewModel
) {
    Row(
        modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.padding(vertical = 5.dp),
                value = valueX,
                onValueChange = onValueXChanged,
                keyboardOptions = keyboardOptions
            )
            OutlinedTextField(
                modifier = Modifier.padding(vertical = 5.dp),

                value = valueY, onValueChange = onValueYChanged, keyboardOptions = keyboardOptions
            )
        }
        Button(onClick = { viewModel.send() }) {
            Text("Send")
        }
    }
}

//@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
//@Composable
//fun SendArmyDialogLight() {
//    AppTheme {
//        Army(viewModel = ArmyViewModel(player = Player()))
//    }
//}
package com.mobilne.civ2077.ui.board.views.army

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.civ2077.R

@Composable
fun Army(viewModel: ArmyViewModel) {
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //BuyArmy
            Spacer(modifier = Modifier.height(20.dp))
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
            Spacer(modifier = Modifier.height(20.dp))
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
}

@Composable
fun BuyArmyHeader() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = "Buy Units",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
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
                value = valueUnits,
                onValueChange = onValueChanged,
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 0.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 15.sp),
            )

        }
        Button(
            modifier = Modifier
                .height(50.dp)
                .width(120.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffbe9d1d)),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(8.dp),
            onClick = { viewModel.buy() },

            ) {
            Text(
                text = "Buy for $valueGoldToPay",
                style = androidx.compose.material.MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
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
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
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
            text = "Max $step step(s) in each direction",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coordinates(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    valueX: String,
    onValueXChanged: (String) -> Unit,
    valueY: String,
    onValueYChanged: (String) -> Unit,
    viewModel: ArmyViewModel
) {
    Row(
        modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            OutlinedTextField(
                modifier = Modifier.height(50.dp),
                textStyle = TextStyle(fontSize = 15.sp),
                value = valueX,
                onValueChange = onValueXChanged,
                keyboardOptions = keyboardOptions
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier.height(50.dp), textStyle = TextStyle(fontSize = 15.sp),

                value = valueY, onValueChange = onValueYChanged, keyboardOptions = keyboardOptions
            )
        }
        Button(modifier = Modifier
            .height(50.dp)
            .width(120.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffbe9d1d)),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(8.dp),
            onClick = { viewModel.send() }) {
            Text(
                text = "Send",
                style = androidx.compose.material.MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}
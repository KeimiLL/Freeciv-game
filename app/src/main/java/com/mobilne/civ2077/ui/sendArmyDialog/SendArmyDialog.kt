package com.mobilne.civ2077.ui.sendArmyDialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun SendArmyDialog(viewModel: SendArmyDialogViewModel) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BuyArmyHeader()
        BuyArmy(
            valueGoldToPay = viewModel.goldToPay,
            valueUnits = viewModel.unitsCount,
            onValueChanged = { viewModel.onUnitsChanged(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        Divider()
        SendArmyHeader()

        Coordinates(
            valueX = viewModel.destinationX,
            onValueXChanged = { viewModel.onXChange(it) },
            valueY = viewModel.destinationY,
            onValueYChanged = { viewModel.onYChange(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )

        Divider()
        ExitButtons()
    }
}

@Composable
fun BuyArmyHeader() {
    Text(
        text = "Buy Units",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyArmy(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    valueUnits: String,
    valueGoldToPay: String,
    onValueChanged: (String) -> Unit,
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
                keyboardOptions = keyboardOptions
            )
        }
        Button(
            onClick = {},

            ) {
            Text("Buy for $valueGoldToPay")
        }
    }

}


@Composable
fun SendArmyHeader() {
    Text(
        text = "Move Army",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface
    )
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
) {
    Row(
        modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            OutlinedTextField(
                value = valueX,
                onValueChange = onValueXChanged,
                keyboardOptions = keyboardOptions
            )
            OutlinedTextField(
                value = valueY,
                onValueChange = onValueYChanged,
                keyboardOptions = keyboardOptions
            )
        }
        Button(onClick = {}) {
            Text("Send")
        }
    }

}


@Composable
fun ExitButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(0.35f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(onClick = {}) {
            Text("Ok")
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun SendArmyDialogLight() {
    AppTheme {
        SendArmyDialog(viewModel = SendArmyDialogViewModel())
    }
}
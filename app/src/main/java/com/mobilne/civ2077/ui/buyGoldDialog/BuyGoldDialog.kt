package com.mobilne.civ2077.ui.buyGoldDialog

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun BuyGoldDialog(viewModel: BuyGoldDialogViewModel){
    Box(modifier = Modifier
        .background(Color(0xFFffffff))
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BuyGoldHeader()

            BuyGold(
                label = "",
                value = viewModel.goldToBuy,
                onValueChanged = { viewModel.onTextChanged(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                )
            )
            Text(
                text = "Total: €\u200E ${viewModel.euroToPay}",
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                RadioButtonSample()
                ExitButtons()
            }

        }
    }
}

@Composable
fun RadioButtonSample() {
    val radioOptions = listOf("PayPal", "Bank transfer", "Credit card")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column {
        Text(text = "Select a payment method:", Modifier.padding(horizontal = 10.dp), style = MaterialTheme.typography.bodyMedium)
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
            }
        }
    }
}

@Composable
fun BuyGoldHeader(){
    Text(
        text = "Buy Gold",
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onSurface
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  BuyGold(
    modifier: Modifier = Modifier,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Row(modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "Gold"
        )
        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChanged,
                label = { Text(text = label) },
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions
            )


        }
        Button(
            onClick = {}
        ) {
            Text("Buy")
        }
    }

}



@Composable
fun ExitButtons() {
    Row(modifier = Modifier.fillMaxWidth(0.7f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround){
        Button(onClick = {}) {
            Text("Cancel")
        }
    }
}




@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BuyGoldDialogPreview() {
    AppTheme {
        BuyGoldDialog(viewModel = BuyGoldDialogViewModel())
    }
}
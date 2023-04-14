package com.mobilne.civ2077.ui.board.views.buyGold

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun BuyGold(viewModel: BuyGoldViewModel) {
    Surface(shadowElevation = 4.dp, shape = RoundedCornerShape(16.dp)) {
        Box(
            modifier = Modifier.fillMaxSize().padding(15.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                BuyGoldHeader()

                BuyGold(
                    value = viewModel.goldToBuy,
                    onValueChanged = { viewModel.onTextChanged(it) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    )
                )
                Text(
                    text = "Total: €\u200E ${viewModel.euroToPay}",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButtonSample()
                }
            }
        }
    }
}


@Composable
fun RadioButtonSample() {
    val radioOptions = listOf("PayPal", "Bank transfer", "Credit card")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1]) }
    Column {
        Text(
            text = "Select a payment method:",
            Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .selectable(selected = (text == selectedOption), onClick = {
                        onOptionSelected(text)
                    })
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (text == selectedOption),
                    onClick = { onOptionSelected(text) })
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                when (text) {
                    "PayPal" -> {
                        Image(
                            painter = painterResource(id = R.drawable.paypal),
                            contentDescription = "Paypal",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    "Bank transfer" -> {
                        Image(
                            painter = painterResource(id = R.drawable.banktransfer),
                            contentDescription = "Bank transfer",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    "Credit card" -> {
                        Image(
                            painter = painterResource(id = R.drawable.creditcard),
                            contentDescription = "Credit card",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BuyGoldHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = "Buy Gold",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(10.dp))
        Image(
            painter = painterResource(id = R.drawable.gold_icon),
            contentDescription = "Gold",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyGold(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Row(
        modifier
            .fillMaxWidth(0.9f)
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChanged,
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .height(50.dp)
                    .padding(horizontal = 0.dp, vertical = 0.dp),
                textStyle = TextStyle(fontSize = 15.sp),
            )
        }
        Button(modifier = Modifier
            .height(50.dp)
            .width(120.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xffa9891c)),
            contentPadding = PaddingValues(12.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = ButtonDefaults.elevation(8.dp),
            onClick = {}) {
            Text(
                text = "Buy",
                style = androidx.compose.material.MaterialTheme.typography.body2,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BuyGoldDialogPreview() {
    AppTheme {
        BuyGold(viewModel = BuyGoldViewModel())
    }
}
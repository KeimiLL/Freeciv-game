package com.mobilne.civ2077.ui.buyGoldDialog

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun BuyGoldDialog(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        BuyGoldHeader()
        Image(
            painter = painterResource(id = R.drawable.gold),
            contentDescription = "Gold"
        )
        BuyGold()
        Text(
            text = "Total: €\u200E 1400",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        ExitButtons()
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
    gold: MutableState<String> = remember {
        mutableStateOf("999")
    }
) {
    Row(modifier.fillMaxWidth(0.9f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {
        Column() {
            OutlinedTextField(
                value = gold.value,
                onValueChange = { gold.value = it },
                label = { Text("") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                ),
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
        Button(onClick = {}) {
            Text("Ok")
        }
    }
}


@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun BuyGoldDialogPreview() {
    AppTheme {
        BuyGoldDialog()
    }
}
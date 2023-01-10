package com.mobilne.civ2077.ui.sendArmyDialog

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.theme.AppTheme

@Composable
fun SendArmyDialog(){
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.sendArmyDialogHeader),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface
        )
        Divider()
        Coordinates()
        Divider()
        Buttons()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Coordinates(
    modifier: Modifier = Modifier,
    x: MutableState<String> = remember {
        mutableStateOf("")
    }, y: MutableState<String> = remember {
        mutableStateOf("")
    }
) {

    Column(
        modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            modifier= Modifier.fillMaxWidth(),
            value = x.value,
            onValueChange = { x.value = it },
            label = { Text("X") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = y.value,
            onValueChange = { y.value = it },
            label = { Text("Y") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
        )
    }
}


@Composable
fun Buttons() {
    Row{
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
fun SendArmyDialogLight() {
    AppTheme {
        SendArmyDialog()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SendArmyDialogDark() {
    AppTheme {
        SendArmyDialog()
    }
}
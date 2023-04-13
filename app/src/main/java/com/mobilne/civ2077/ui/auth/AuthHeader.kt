package com.mobilne.civ2077.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilne.civ2077.R
import com.mobilne.civ2077.ui.board.BoardViewModel
import com.mobilne.civ2077.ui.theme.spacing

@Composable
fun AuthHeader(boardViewModel: BoardViewModel) {
    val spacing = MaterialTheme.spacing

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(top = 0.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(0.2f)){ }

        Column(
            modifier = Modifier
                .padding(5.dp)
                .weight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Column(
            modifier = Modifier
                .padding(0.dp)
                .weight(0.2f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ){
            Text(
                "Dark mode",
                style = TextStyle(fontSize = 20.sp),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.width(8.dp))
            Switch(
                checked = boardViewModel.isDarkModeOn.value,
                onCheckedChange = {
                    boardViewModel.toggleDarkMode()
                },
            )
        }
    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = MaterialTheme.colorScheme.surface)
//            .padding(top = 5.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            modifier = Modifier.fillMaxWidth(),
//            text = stringResource(id = R.string.app_name),
//            style = MaterialTheme.typography.headlineLarge,
//            textAlign = TextAlign.Center,
//            color = MaterialTheme.colorScheme.onSurface
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 20.dp),
//            horizontalArrangement = Arrangement.End,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                "Dark mode",
//                style = TextStyle(fontSize = 20.sp),
//                color = MaterialTheme.colorScheme.onBackground
//            )
//            Spacer(Modifier.width(8.dp))
//            Switch(
//                checked = boardViewModel.isDarkModeOn.value,
//                onCheckedChange = {
//                    boardViewModel.toggleDarkMode()
//                },
//            )
//        }
//    }
}
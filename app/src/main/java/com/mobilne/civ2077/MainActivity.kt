package com.mobilne.civ2077

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mobilne.civ2077.navigation.AppNavHost
import com.mobilne.civ2077.ui.auth.AuthViewModel
import com.mobilne.civ2077.ui.theme.AppTheme


class MainActivity : AppCompatActivity() {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                AppNavHost(viewModel = authViewModel)
            }
        }
    }
}
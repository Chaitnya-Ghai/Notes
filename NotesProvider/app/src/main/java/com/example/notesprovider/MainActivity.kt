package com.example.notesprovider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notesprovider.presentation.MainScreen
import com.example.notesprovider.presentation.MainViewModel
import com.example.notesprovider.ui.theme.NotesProviderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NotesProviderTheme {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

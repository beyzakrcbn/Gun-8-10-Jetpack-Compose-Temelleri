package com.example.sayacapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import com.example.sayacapp.ui.theme.SayacAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var useDarkTheme by remember { mutableStateOf(false) }

            SayacAppTheme(darkTheme = useDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterScreen(
                        onToggleTheme = { useDarkTheme = !useDarkTheme },
                        isDarkThemeActive = useDarkTheme
                    )
                }
            }
        }
    }
}

@Composable
fun CounterScreen(
    onToggleTheme: () -> Unit,
    isDarkThemeActive: Boolean
) {
    var counter by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sayaç: $counter", fontSize = 32.sp)

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 32.dp)
        ) {
            Button(onClick = { counter-- }) {
                Text(text = "-")
            }

            Button(onClick = { counter++ }) {
                Text(text = "+")
            }

            Button(onClick = { counter = 0 }) {
                Text(text = "RESET")
            }

            Button(onClick = onToggleTheme) {
                Text(text = if (isDarkThemeActive) "Açık Tema" else "Koyu Tema")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CounterScreenPreview() {
    SayacAppTheme {
        CounterScreen(onToggleTheme = {}, isDarkThemeActive = false)
    }
}
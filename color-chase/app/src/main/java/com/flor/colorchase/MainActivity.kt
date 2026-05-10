package com.flor.colorchase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.flor.colorchase.ui.theme.ColorChaseTheme

import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorChaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ColorSequenceScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ColorSequenceScreen(modifier: Modifier = Modifier) {
    var isRunning by remember { mutableStateOf(false) }
    var isRandomMode by remember { mutableStateOf(false) }
    var activeIndex by remember { mutableIntStateOf(-1) }

    LaunchedEffect(isRunning) {
        var clockwiseIndex = 0
        while (isRunning) {
            if (isRandomMode) {
                activeIndex = (0..3).random()
            } else {
                val next = clockwiseIndex % 4
                clockwiseIndex++
                activeIndex = next
            }

            delay(400)
            activeIndex = -1
            delay(100)
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Box(Modifier.size(100.dp).background(if (activeIndex == 0 && isRunning) Color.White else Color.Red))
            Spacer(Modifier.width(16.dp))
            Box(Modifier.size(100.dp).background(if (activeIndex == 1 && isRunning) Color.White else Color.Green))
        }
        Spacer(Modifier.height(16.dp))
        Row {
            Box(Modifier.size(100.dp).background(if (activeIndex == 3 && isRunning) Color.White else Color.Blue))
            Spacer(Modifier.width(16.dp))
            Box(Modifier.size(100.dp).background(if (activeIndex == 2 && isRunning) Color.White else Color.Yellow))
        }

        Spacer(Modifier.height(48.dp))

        Button(onClick = { isRunning = !isRunning }) {
            Text(if (isRunning) "Stop" else "Start")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = isRandomMode,
                onCheckedChange = { isRandomMode = it },
                colors = CheckboxDefaults.colors(uncheckedColor = Color.White)
            )
            Text("Random Mode", color = Color.White)
        }
    }
}
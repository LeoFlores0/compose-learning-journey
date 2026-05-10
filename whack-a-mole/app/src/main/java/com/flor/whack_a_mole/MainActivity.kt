package com.flor.whack_a_mole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flor.whack_a_mole.ui.theme.WhackaMoleTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhackaMoleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WhackaMoleScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WhackaMoleScreen(modifier: Modifier = Modifier) {
    var isRunning by remember { mutableStateOf(false) }
    var isRandomMode by remember { mutableStateOf(false) }
    var activeIndex by remember { mutableIntStateOf(-1) }

    val score = remember { mutableIntStateOf(0) }
    val misses = remember { mutableIntStateOf(0) }
    val hasHitThisTurn = remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        var clockwiseIndex = 0
        while (isRunning) {
            hasHitThisTurn.value = false

            if (isRandomMode) {
                activeIndex = (0..3).random()
            } else {
                activeIndex = clockwiseIndex % 4
                clockwiseIndex++
            }

            delay(600)

            if (!hasHitThisTurn.value && activeIndex != -1) {
                misses.intValue += 1
            }

            activeIndex = -1
            delay(150)
        }
    }

    Column(
        modifier = modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Score: ${score.intValue}", color = Color.White, fontSize = 24.sp)
        Text("Misses: ${misses.intValue}", color = Color.Red, fontSize = 18.sp)

        Spacer(Modifier.height(32.dp))

        Row {
            GameBox(0, activeIndex, isRunning, Color.Red) {
                handleBoxClick(0, activeIndex, isRunning, hasHitThisTurn, score, misses)
            }
            Spacer(Modifier.width(16.dp))
            GameBox(1, activeIndex, isRunning, Color.Green) {
                handleBoxClick(1, activeIndex, isRunning, hasHitThisTurn, score, misses)
            }
        }

        Spacer(Modifier.height(16.dp))

        Row {
            GameBox(3, activeIndex, isRunning, Color.Blue) {
                handleBoxClick(3, activeIndex, isRunning, hasHitThisTurn, score, misses)
            }
            Spacer(Modifier.width(16.dp))
            GameBox(2, activeIndex, isRunning, Color.Yellow) {
                handleBoxClick(2, activeIndex, isRunning, hasHitThisTurn, score, misses)
            }
        }

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = {
                isRunning = !isRunning
                if (isRunning) {
                    score.intValue = 0
                    misses.intValue = 0
                }
            }
        ) {
            Text(if (isRunning) "Stop!" else "Start!")
        }

        Spacer(Modifier.height(16.dp))

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

@Composable
fun GameBox(
    index: Int,
    activeIndex: Int,
    isRunning: Boolean,
    baseColor: Color,
    onClick: () -> Unit
) {
    Box(
        Modifier
            .size(100.dp)
            .background(if (activeIndex == index && isRunning) Color.White else baseColor)
            .clickable { onClick() }
    )
}

fun handleBoxClick(
    clickedIndex: Int,
    activeIndex: Int,
    isRunning: Boolean,
    hasHitState: MutableState<Boolean>,
    scoreState: MutableIntState,
    missState: MutableIntState
) {
    if (!isRunning) return

    if (activeIndex == clickedIndex && !hasHitState.value) {
        scoreState.intValue += 1
        hasHitState.value = true
    } else if (activeIndex != clickedIndex) {
        missState.intValue += 1
    }
}
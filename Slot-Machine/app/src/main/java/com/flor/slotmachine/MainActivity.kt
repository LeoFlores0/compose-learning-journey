package com.flor.slotmachine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flor.slotmachine.ui.theme.SlotMachineTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlotMachineTheme {
                SlotMachine()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlotMachine(modifier: Modifier = Modifier) {
    var count1 by remember { mutableIntStateOf(0) }
    var count2 by remember { mutableIntStateOf(0) }
    var count3 by remember { mutableIntStateOf(0) }

    var isRunning1 by remember { mutableStateOf(false) }
    var isRunning2 by remember { mutableStateOf(false) }
    var isRunning3 by remember { mutableStateOf(false) }

    var job1 by remember { mutableStateOf<Job?>(null) }
    var job2 by remember { mutableStateOf<Job?>(null) }
    var job3 by remember { mutableStateOf<Job?>(null) }

    var stopClicks by remember { mutableIntStateOf(0) }

    val coroutine = rememberCoroutineScope()
    var message by remember { mutableStateOf("") }
    val speed = 500L

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(top = 50.dp)) {
            SlotDisplay(count1)
            SlotDisplay(count2)
            SlotDisplay(count3)
        }

        Text(text = message, fontSize = 24.sp, modifier = Modifier.padding(20.dp))

        Button(
            onClick = {
                stopClicks++
                when (stopClicks) {
                    1 -> { job1?.cancel(); isRunning1 = false }
                    2 -> { job2?.cancel(); isRunning2 = false }
                    3 -> {
                        job3?.cancel(); isRunning3 = false
                        checkWin(count1, count2, count3, isRunning1, isRunning2, false) { message = it }
                    }
                }
            },
            enabled = isRunning1 || isRunning2 || isRunning3,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("STOP")
        }

        Button(onClick = {
            stopClicks = 0
            message = ""

            isRunning1 = true
            job1 = coroutine.launch(Dispatchers.Default) { while (true) { delay(speed); count1 = (count1 + 1) % 4 } }

            isRunning2 = true
            job2 = coroutine.launch(Dispatchers.Default) { while (true) { delay(speed + 200); count2 = (count2 + 1) % 4 } }

            isRunning3 = true
            job3 = coroutine.launch(Dispatchers.Default) { while (true) { delay(speed + 150); count3 = (count3 + 1) % 4 } }
        }) { Text("START") }
    }
}

@Composable
fun SlotDisplay(count: Int) {
    val imageRes = when (count) {
        0 -> R.drawable.cherry
        1 -> R.drawable.grape
        2 -> R.drawable.pear
        3 -> R.drawable.strawberry
        else -> R.drawable.cherry
    }

    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "Slot value $count",
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
    )
}

fun checkWin(c1: Int, c2: Int, c3: Int, isRunning1: Boolean, isRunning2: Boolean, isRunning3: Boolean, onResult: (String) -> Unit) {
    if (!isRunning1 && !isRunning2 && !isRunning3) {
        if (c1 == c2 && c2 == c3) {
            onResult("You Won!")
        } else {
            onResult("Try again!")
        }
    } else {
        onResult("")
    }
}
package com.example.lazycolrows

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lazycolrows.ui.theme.LazyColRowsTheme
import java.lang.reflect.Modifier


class MainActivity : ComponentActivity() {
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColRowsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LazyScreen(modifier: Modifier = Modifier, lazyViewModel: LazyViewModel = viewModel()) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
        LazyScreenPortrait(modifier, lazyViewModel)
    } else {
        LazyScreenLandscape(modifier, lazyViewModel)
    }
}

@Composable
fun LazyScreenPortrait(modifier: Modifier = Modifier, lazyViewModel: LazyViewModel) {
    val images = lazyViewModel.imageResIds
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            Text(
                text = "Count to Five!",
                fontSize = 45.sp
            )
        }
        items(images) { resId ->
            ImageItemP(resId)
        }
    }
}

@Composable
fun LazyScreenLandscape(modifier: Modifier = Modifier, lazyViewModel: LazyViewModel) {
    val images = lazyViewModel.imageResIds

    LazyRow(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        stickyHeader {
            Text(
                text = "Count to Five!",
                fontSize = 45.sp
            )
        }
        items(images) { resId ->
            ImageItemL(resId)
        }
    }
}


@Composable
fun ImageItemP(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = "Current image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Spacer(Modifier.height(60.dp))
}

@Composable
fun ImageItemL(resId: Int) {
    Image(
        painter = painterResource(id = resId),
        contentDescription = "Current image",
        modifier = Modifier
            .fillMaxHeight()
            .width(500.dp),
    )
    Spacer(Modifier.width(40.dp))
}
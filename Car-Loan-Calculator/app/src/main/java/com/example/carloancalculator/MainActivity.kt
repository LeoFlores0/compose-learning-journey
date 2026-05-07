package com.example.carloancalculator

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carloancalculator.ui.theme.CarLoanCalculatorTheme
import kotlin.math.pow
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CarLoanCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CarLoanScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CarLoanScreen(modifier: Modifier = Modifier, carLoanViewModel: CarLoanViewModel = viewModel()) {
    if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT) {
        CarLoanPortrait(modifier, carLoanViewModel)
    } else {
        CarLoanLandscape(modifier, carLoanViewModel)
    }
}

@Composable
fun CarLoanLandscape(modifier: Modifier = Modifier, carLoanViewModel: CarLoanViewModel) {

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Car Loan Calculator",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(painter = painterResource(id = R.drawable.car_logo), contentDescription = "Car")

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = carLoanViewModel.purchasePrice,
                onValueChange = { carLoanViewModel.purchasePrice = it },
                label = {
                    Text(
                        text = "Purchase Price",
                        fontSize = 20.sp
                    ) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )

            TextField(
                value = carLoanViewModel.downPayment,
                onValueChange = { carLoanViewModel.downPayment = it },
                label = {
                    Text(
                        text = "Down Payment",
                        fontSize = 20.sp
                    ) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Annual Interest Rate: ${carLoanViewModel.interestRate}%",
                fontSize = 30.sp
            )
            Slider(
                modifier = Modifier.size(width = 350.dp, height = 100.dp),
                value = carLoanViewModel.interestRate,
                onValueChange = { newValue ->
                    carLoanViewModel.interestRate = (newValue * 100).roundToInt() / 100.0f
                },
                valueRange = 0f..15f
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Loan Length (Years)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.width(45.dp))

                Text(
                    text = "MONTHLY PAYMENT",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left
            ) {
                for (yearValue in carLoanViewModel.options) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.selectable(
                            selected = (yearValue == carLoanViewModel.selectedYears),
                            onClick = { carLoanViewModel.selectedYears = yearValue }
                        )
                    ) {
                        RadioButton(
                            selected = (yearValue == carLoanViewModel.selectedYears),
                            onClick = { carLoanViewModel.selectedYears = yearValue }
                        )
                        Text(
                            text = yearValue.toString(),
                            fontSize = 24.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(30.dp))

                Text(
                    text = "$${carLoanViewModel.monthlyPaymentResult}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val price = carLoanViewModel.purchasePrice.toDoubleOrNull() ?: 0.0
                        val down = carLoanViewModel.downPayment.toDoubleOrNull() ?: 0.0
                        val loanAmt = price - down
                        val mRate = (carLoanViewModel.interestRate.toDouble() / 100.0) / 12.0
                        val nMonths = (carLoanViewModel.selectedYears * 12).toDouble()

                        if (loanAmt > 0 && mRate > 0) {
                            val p = (mRate * loanAmt) / (1 - (1 + mRate).pow(-nMonths))
                            carLoanViewModel.monthlyPaymentResult = ((p * 100).roundToInt() / 100.0).toString()
                        } else if (loanAmt > 0 && mRate == 0.0) {
                            val p = loanAmt / nMonths
                            carLoanViewModel.monthlyPaymentResult = ((p * 100).roundToInt() / 100.0).toString()
                        } else {
                            carLoanViewModel.monthlyPaymentResult = "0.00"
                        }
                    },
                    modifier = Modifier.size(width = 270.dp, height = 50.dp)
                ) {
                    Text(
                        text = "Get Payment (Monthly)",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}


@Composable
fun CarLoanPortrait(modifier: Modifier = Modifier, carLoanViewModel: CarLoanViewModel) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Car Loan Calculator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Image(painter = painterResource(id = R.drawable.car_logo), contentDescription = "Car")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = carLoanViewModel.purchasePrice,
            onValueChange = { carLoanViewModel.purchasePrice = it },
            label = { Text("Purchase Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = carLoanViewModel.downPayment,
            onValueChange = { carLoanViewModel.downPayment = it },
            label = { Text("Down Payment") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Annual Interest Rate: ${carLoanViewModel.interestRate}%")
        Slider(
            value = carLoanViewModel.interestRate,
            onValueChange = { newValue ->
                carLoanViewModel.interestRate = (newValue * 100).roundToInt() / 100.0f
            },
            valueRange = 0f..15f
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Loan Length (Years)",
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for (yearValue in carLoanViewModel.options) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = (yearValue == carLoanViewModel.selectedYears),
                        onClick = { carLoanViewModel.selectedYears = yearValue }
                    )
                ) {
                    RadioButton(
                        selected = (yearValue == carLoanViewModel.selectedYears),
                        onClick = { carLoanViewModel.selectedYears = yearValue }
                    )
                    Text(text = yearValue.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val price = carLoanViewModel.purchasePrice.toDoubleOrNull() ?: 0.0
                val down = carLoanViewModel.downPayment.toDoubleOrNull() ?: 0.0
                val loanAmt = price - down
                val mRate = (carLoanViewModel.interestRate.toDouble() / 100.0) / 12.0
                val nMonths = (carLoanViewModel.selectedYears * 12).toDouble()

                if (loanAmt > 0 && mRate > 0) {
                    val p = (mRate * loanAmt) / (1 - (1 + mRate).pow(-nMonths))
                    carLoanViewModel.monthlyPaymentResult = ((p * 100).roundToInt() / 100.0).toString()
                } else if (loanAmt > 0 && mRate == 0.0) {
                    val p = loanAmt / nMonths
                    carLoanViewModel.monthlyPaymentResult = ((p * 100).roundToInt() / 100.0).toString()
                } else {
                    carLoanViewModel.monthlyPaymentResult = "0.00"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Calculate Monthly Payment")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "MONTHLY PAYMENT",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "$${carLoanViewModel.monthlyPaymentResult}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}




package com.example.simplecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalculator.ui.composables.CalcButton
import com.example.simplecalculator.ui.theme.Montserrat

@Composable
fun SimpleCalculator(
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf("") }
    var resultText by remember { mutableStateOf("0") }
    var num1 by remember { mutableStateOf<Double?>(null) }
    var operator by remember { mutableStateOf<String?>(null) }

    fun onNumberClick(digit: String) {
        inputText += digit
    }

    fun onDotClick() {
        if (!inputText.contains(".")) inputText += "."
    }

    fun onClear() {
        inputText = ""
        resultText = "0"
        num1 = null
        operator = null
    }

    fun onOperatorClick(op: String) {
        if (inputText.isNotEmpty()) {
            num1 = inputText.toDoubleOrNull()
            operator = op
            inputText = ""
        }
    }

    fun onEqualClick() {
        val num2 = inputText.toDoubleOrNull()
        if (num1 != null && num2 != null && operator != null) {
            val result = when (operator) {
                "+" -> num1!! + num2
                "-" -> num1!! - num2
                "x" -> num1!! * num2
                "/" -> if (num2 != 0.0) num1!! / num2 else null
                else -> null
            }

            resultText = result?.toString() ?: "Error"
            num1 = null
            operator = null
            inputText = ""
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(341.dp)
                .height(469.dp),
            verticalArrangement = Arrangement.spacedBy(35.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = inputText.ifEmpty { "Enter The Value" },
                modifier = Modifier
                    .width(300.dp)
                    .height(46.dp),
                style = TextStyle(
                    fontSize = 38.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF000000)
                )
            )

            Text(
                text = "Result: $resultText",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 37.dp),
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = Montserrat,
                    fontWeight = FontWeight.W400,
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                )
            )

            Column(
                modifier = modifier
                    .width(341.dp)
                    .height(221.dp),
                verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = modifier
                        .width(341.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CalcButton(label = "1", onClick = { onNumberClick("1") })
                    CalcButton(label = "2", onClick = { onNumberClick("2") })
                    CalcButton(label = "3", onClick = { onNumberClick("3") })
                    CalcButton(label = "+", onClick = { onOperatorClick("+") }, backgroundColor = Color(0xFF999999))
                }

                Row(
                    modifier = modifier
                        .width(341.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CalcButton(label = "4", onClick = { onNumberClick("4") })
                    CalcButton(label = "5", onClick = { onNumberClick("5") })
                    CalcButton(label = "6", onClick = { onNumberClick("6") })
                    CalcButton(label = "-", onClick = { onOperatorClick("-") }, backgroundColor = Color(0xFF999999))
                }

                Row(
                    modifier = modifier
                        .width(341.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CalcButton(label = "7", onClick = { onNumberClick("7") })
                    CalcButton(label = "8", onClick = { onNumberClick("8") })
                    CalcButton(label = "9", onClick = { onNumberClick("9") })
                    CalcButton(label = "x", onClick = { onOperatorClick("x") }, backgroundColor = Color(0xFF999999))
                }

                Row(
                    modifier = modifier
                        .width(341.dp)
                        .height(50.dp),
                    horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CalcButton(label = ".", onClick = { onDotClick() }, backgroundColor = Color(0xFF999999))
                    CalcButton(label = "0", onClick = { onNumberClick("0") })
                    CalcButton(label = "CE", onClick = { onClear() }, backgroundColor = Color(0xFF999999))
                    CalcButton(label = "/", onClick = { onOperatorClick("/") }, backgroundColor = Color(0xFF999999))
                }
            }

            Button(
                onClick = { onEqualClick() },
                modifier = modifier
                    .width(120.dp)
                    .height(60.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF6F696)),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Submit",
                    modifier = Modifier
                        .width(75.dp)
                        .height(24.dp),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF000000),
                    )
                )
            }
        }
    }
}

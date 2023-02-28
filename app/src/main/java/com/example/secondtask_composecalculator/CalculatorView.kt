package com.example.secondtask_composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondtask_composecalculator.ui.theme.*

@Composable
fun CalculatorView(expression: MutableState<String>) {
    Column(modifier = Modifier.background(DisplayColor))
    {
        Text(
            text = stringResource(id = R.string.test),
            fontSize = 28.sp,
            color = LabelColor,
            fontFamily = GoogleSans,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = expression.value,
            fontSize = 50.sp,
            color = FontColor,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            fontFamily = GoogleSans,
            maxLines = 1,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DisplayColor)
                    .padding(
                        start = 16.dp, end = 16.dp, top = 12.dp, bottom = 16.dp
                    )
            )
            {
                Row( modifier = Modifier
                    .background(DisplayColor)
                    .fillMaxWidth()
                    .padding(end = 16.dp, bottom = 16.dp, top = 16.dp),
                    horizontalArrangement = Arrangement.End)
                {
                    DeleteButton(expression = expression)
                }

                Row(
                    modifier = Modifier
                        .background(DisplayColor),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ClearButton(symbol = "AC", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ChangeSignButton(symbol = "±", NumberButtonColor, expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { PrecentageButton(expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ActionButton(symbol = "÷", ActionButtonColor, expression) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(DisplayColor)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "7", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "8", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "9", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ActionButton(symbol = "×", ActionButtonColor, expression) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(DisplayColor)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "4", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "5", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "6",expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ActionButton(symbol = "-", ActionButtonColor, expression) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(DisplayColor)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "1", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "2", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { NumberButton(symbol = "3", expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { ActionButton(symbol = "+", ActionButtonColor, expression) }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(DisplayColor)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier
                        .weight(2.2f)
                        .aspectRatio(2.1f))
                    { ZeroButton(expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { DoubleButton(expression) }
                    Spacer(modifier = Modifier.width(16.dp))
                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f))
                    { CalculateButton(ActionButtonColor, expression) }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
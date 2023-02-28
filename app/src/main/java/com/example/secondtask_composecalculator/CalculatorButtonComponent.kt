package com.example.secondtask_composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.secondtask_composecalculator.ui.theme.DisplayColor
import com.example.secondtask_composecalculator.ui.theme.FontColor
import com.example.secondtask_composecalculator.ui.theme.GoogleSans
import com.example.secondtask_composecalculator.ui.theme.NumberButtonColor





var numberIsClicked = false
var actionIsClicked = false

@Composable
fun NumberButton(symbol: String, expression: MutableState<String>){
    Button(onClick = { expression.value += symbol; numberIsClicked = true },
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = NumberButtonColor),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()
    )
    {
        Text(text = symbol, fontFamily = GoogleSans, fontSize = 32.sp, color = FontColor)
    }
}

@Composable
fun ZeroButton(expression: MutableState<String>){
    Button(onClick = {expression.value += "0"}
        ,shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = NumberButtonColor),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()
    ){
        Text(text = "0", fontFamily = GoogleSans, fontSize = 28.sp, color = FontColor)
    }
}

@Composable
fun ChangeSignButton(symbol: String, color: Color, expression: MutableState<String>){
    Button(onClick = { signChange(expression) },
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = symbol, fontFamily = GoogleSans, fontSize = 28.sp, color = FontColor)
    }
}

@Composable
fun DoubleButton(expression: MutableState<String>){
    Button(onClick = {expression.value += "."},
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = NumberButtonColor),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = ",", fontFamily = GoogleSans, fontSize = 28.sp, color = FontColor)
    }
}

@Composable
fun PrecentageButton(expression: MutableState<String>){
    Button(onClick = { Calculate(expression);Precentage(expression) },
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = NumberButtonColor),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = "%", fontFamily = GoogleSans, fontSize = 28.sp, color = FontColor)
    }
}

@Composable
fun ActionButton(symbol: String, color: Color, expression: MutableState<String>){
    val symbolCopy: String
    when(symbol){
            "÷" -> symbolCopy = "/"
            "×" -> symbolCopy = "*"
            "," -> symbolCopy = "."
        else -> {
            symbolCopy = symbol
        }
    }
    Button(onClick = { if(numberIsClicked && !actionIsClicked){expression.value += symbolCopy
    actionIsClicked = true}},
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = symbol, fontFamily = GoogleSans, fontSize = 28.sp, color = FontColor)
    }
}

@Composable
fun CalculateButton(color: Color, expression: MutableState<String>){
    Button(onClick = { if(actionIsClicked){Calculate(expression)
        numberIsClicked = true
        actionIsClicked = false}},
        shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = "=", fontFamily = GoogleSans, fontSize = 32.sp, color = FontColor)
    }
}


@Composable
fun ClearButton(symbol: String, expression: MutableState<String>){
    Button(onClick = {expression.value = ""
        numberIsClicked = true
        actionIsClicked = false}, shape = RoundedCornerShape(28.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = NumberButtonColor),
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize()){
        Text(text = symbol, fontFamily = GoogleSans, fontSize = 24.sp, color = FontColor)
    }
}

@Composable
fun DeleteButton(expression: MutableState<String>){
    IconButton(modifier = Modifier
        .width(24.dp)
        .height(18.dp),
        onClick =
        {
        if (expression.value.length > 0) {
            expression.value = expression.value.substring(0, expression.value.length - 1) }
        }
    )
    {
        Icon(
            painter = painterResource(id = R.drawable.delete_button_background),
            contentDescription = "Delete button",
            tint = Color.DarkGray,
        )
    }
}

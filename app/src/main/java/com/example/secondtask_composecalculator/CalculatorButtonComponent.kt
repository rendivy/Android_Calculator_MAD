package com.example.secondtask_composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.secondtask_composecalculator.data.ActionEnum
import com.example.secondtask_composecalculator.ui.theme.*


@Composable
fun getButtonColor(buttonSymbol: ActionEnum): Color{
    when(buttonSymbol){
        ActionEnum.PLUS, ActionEnum.DIVIDE, ActionEnum.MINUS,
        ActionEnum.CALCULATE, ActionEnum.MULTIPLY-> {
            return ActionButtonColor
        }
        else -> {
            return NumberButtonColor
        }
    }
}

@Composable
fun changeDelColor(expression: MutableState<String>): Color{
    val color: Color
    if (expression.value == "") {
        color = Color.DarkGray
    }
    else{
        color = Color.White
    }
    return color
}

@Composable
fun DeleteButton(expression: MutableState<String>){
    IconButton(
        modifier = Modifier.width(24.dp).height(18.dp),
        onClick = { oneCharDelete(expression) })
    { Icon(
            painter = painterResource(id = R.drawable.delete_button_background),
            contentDescription = "Delete button",
            tint = changeDelColor(expression = expression))
    }
}

@Composable
fun ButtonModel(symbol: String, onClick: (String) -> Unit, color: Color, fontSize: TextUnit){
    Button(
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        shape = RoundedCornerShape(28.dp),
        onClick = { onClick.invoke(symbol) },
    )
    {
        Text(
            text = symbol,
            fontFamily = GoogleSansMedium,
            fontSize = fontSize,
            color = FontColor)
    }
}



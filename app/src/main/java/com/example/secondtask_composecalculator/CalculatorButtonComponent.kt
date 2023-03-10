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
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.secondtask_composecalculator.data.ActionEnum
import com.example.secondtask_composecalculator.ui.theme.*


@Composable
fun DeleteButton(charDelete: () -> Unit, buttonColor: Color) {
    IconButton(modifier = Modifier
        .width(24.dp)
        .height(18.dp), onClick = { charDelete() }) {
        Icon(
            painter = painterResource(id = R.drawable.delete_button_background),
            contentDescription = "Delete button",
            tint = buttonColor
        )
    }
}

@Composable
fun ButtonModel(
    buttonSymbol: ActionEnum, onClick: (String) -> Unit, color: Color, fontSize: TextUnit
) {
    Button(
        modifier = Modifier
            .background(DisplayColor)
            .fillMaxSize(),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        shape = RoundedCornerShape(28.dp),
        onClick = { onClick.invoke(buttonSymbol.symbol) },
    ) {
        Text(
            text = buttonSymbol.symbol,
            fontFamily = GoogleSansMedium,
            fontSize = fontSize,
            color = FontColor
        )
    }
}



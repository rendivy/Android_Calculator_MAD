package com.example.secondtask_composecalculator.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.secondtask_composecalculator.R


val GoogleSansMedium = FontFamily(
    Font(R.font.googlesans_medium)
)


val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    h1 = TextStyle(
        fontFamily = GoogleSansMedium,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        color = Color.White
    )
)
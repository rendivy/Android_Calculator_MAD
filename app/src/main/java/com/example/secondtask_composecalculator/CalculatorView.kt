package com.example.secondtask_composecalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.example.secondtask_composecalculator.data.ActionEnum
import com.example.secondtask_composecalculator.data.CalculatorAction
import com.example.secondtask_composecalculator.data.expression
import com.example.secondtask_composecalculator.ui.theme.*


val buttonMatrix: List<List<ActionEnum>> = listOf(
    listOf(ActionEnum.CLEAR, ActionEnum.SIGN, ActionEnum.PERCENT, ActionEnum.DIVIDE),
    listOf(ActionEnum.SEVEN, ActionEnum.EIGHT, ActionEnum.NINE, ActionEnum.MULTIPLY),
    listOf(ActionEnum.FOUR, ActionEnum.FIVE, ActionEnum.SIX, ActionEnum.MINUS),
    listOf(ActionEnum.ONE, ActionEnum.TWO, ActionEnum.THREE, ActionEnum.PLUS),
    listOf(ActionEnum.ZERO, ActionEnum.DOUBLE, ActionEnum.CALCULATE)
)
val action = CalculatorAction()

@Preview(showBackground = true)
@Composable
fun CalculatorView() {
    Column(modifier = Modifier.background(DisplayColor)) {
        TextView()
        KeyboardView()
    }

}


@Composable
fun TextView() {
    Column(modifier = Modifier.background(DisplayColor))
    {
        Text(
            text = MainLabel,
            fontSize = LabelSize,
            color = LabelColor,
            fontFamily = GoogleSansMedium,
            modifier = Modifier.padding(
                start = MainExpressionPadding,
                top = MainExpressionPadding
            )
        )
        Text(
            text = expression.value,
            fontSize = MainExpressionSize,
            color = FontColor,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(
                start = MainExpressionPadding,
                top = MainExpressionPadding
            ),
            fontFamily = GoogleSansMedium,
            maxLines = 2,
        )
    }
}

@Composable
fun KeyboardView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(DisplayColor)
            .padding(
                start = ColumnPadding,
                top = ColumnPadding,
            ),
        verticalArrangement = Arrangement.Bottom
    )
    {
        Row(
            modifier = Modifier
                .background(DisplayColor)
                .fillMaxWidth()
                .padding(
                    end = DeleteEndPadding,
                    bottom = DeleteBottomPadding,
                    top = DeleteTopPadding
                ),
            horizontalArrangement = Arrangement.End
        )
        {
            DeleteButton(
                { action.oneCharDelete() },
                action.changeDelColor()
            )
        }
        Divider(
            modifier = Modifier.padding(bottom = DividerPadding, end = DividerPadding),
            thickness = DividerThickness,
            color = Color.DarkGray
        )
        buttonMatrix.forEach {
                buttons -> KeyboardRow(buttons = buttons)
        }
    }
}

@Composable
fun KeyboardRow(buttons: List<ActionEnum>){
    var modifier: Modifier
    var fontSize: TextUnit
    Row(
        modifier = Modifier
            .background(DisplayColor),
        horizontalArrangement = Arrangement.SpaceAround
    )
    {
        buttons.forEach { buttonSymbol ->
            modifier = if (buttonSymbol == ActionEnum.ZERO) {
                Modifier
                    .weight(2.2f)
                    .aspectRatio(2.2f)
            } else {
                Modifier
                    .weight(1f)
                    .aspectRatio(1f)
            }
            fontSize = if (buttonSymbol.symbol == ActionEnum.CLEAR.symbol) {
                ClearButtonSize
            } else {
                DefaultButtonSize
            }
            Box(
                modifier = modifier
            )
            {
                ButtonModel(
                    buttonSymbol = buttonSymbol,
                    onClick = { action.handleButtonClick(buttonSymbol) },
                    color = action.getButtonColor(buttonSymbol),
                    fontSize = fontSize
                )
            }
            Spacer(modifier = Modifier.width(SpacerPadding))
        }
    }
    Spacer(modifier = Modifier.height(SpacerPadding))
}

package com.example.secondtask_composecalculator.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.secondtask_composecalculator.ui.theme.ActionButtonColor
import com.example.secondtask_composecalculator.ui.theme.NumberButtonColor


var numberIsClicked: Boolean = false
var actionIsClicked: Boolean = false
var errorInString: Boolean = false
var doubleInExpression: Boolean = false
val operationsArray: List<Char> = listOf('-', '+', '*', '/')
val expression: MutableState<String> = mutableStateOf("")

class CalculatorAction {

    fun handleButtonClick(buttonSymbol: ActionEnum) {
        when (buttonSymbol) {
            ActionEnum.PLUS -> {
                addPlusOnExpression()
            }
            ActionEnum.DIVIDE -> {
                addDivideOnExpression()
            }
            ActionEnum.MULTIPLY -> {
                addMultiplyOnExpression()
            }
            ActionEnum.MINUS -> {
                addMinusOnExpression()
            }
            ActionEnum.SIGN -> {
                signChange()
            }
            ActionEnum.CALCULATE -> {
                calculate()
            }
            ActionEnum.PERCENT -> {
                toPercent()
            }
            ActionEnum.CLEAR -> {
                clearExpression()
            }
            ActionEnum.DOUBLE -> {
                toDouble()
            }
            else -> {
                addNumberOnExpression(buttonSymbol)
            }
        }
    }

    fun getButtonColor(buttonSymbol: ActionEnum): Color {
        when (buttonSymbol) {
            ActionEnum.PLUS, ActionEnum.DIVIDE, ActionEnum.MINUS, ActionEnum.CALCULATE, ActionEnum.MULTIPLY -> {
                return ActionButtonColor
            }
            else -> {
                return NumberButtonColor
            }
        }
    }

    fun changeDelColor(): Color {
        val color: Color
        if (expression.value == "") {
            color = Color.DarkGray
        } else {
            color = Color.White
        }
        return color
    }

    fun oneCharDelete() {
        if (expression.value.isNotEmpty()) {
            expression.value = expression.value.substring(0, expression.value.length - 1)
            actionIsClicked = false
            errorInString = false
            doubleInExpression = false
        }
    }


    private fun addNumberOnExpression(buttonSymbol: ActionEnum) {
        expression.value += buttonSymbol.symbol
        numberIsClicked = true
    }

    private fun addMinusOnExpression() {
        if (!actionIsClicked and !errorInString) {
            expression.value += ActionEnum.MINUS.symbol
            actionIsClicked = true
            doubleInExpression = false
        }
    }

    private fun addPlusOnExpression() {
        if (!actionIsClicked and !errorInString) {
            expression.value += ActionEnum.PLUS.symbol
            actionIsClicked = true
            doubleInExpression = false
        }
    }

    private fun addDivideOnExpression() {
        if (!actionIsClicked and !errorInString) {
            expression.value += "/"
            actionIsClicked = true
            doubleInExpression = false
        }
    }


    private fun addMultiplyOnExpression() {
        if (!actionIsClicked and !errorInString) {
            expression.value += "*"
            actionIsClicked = true
            doubleInExpression = false
        }
    }


    private fun isOperatorInExpression(): Boolean {
        var flag = true
        for (i in 0 until expression.value.length) {
            if (operationsArray.contains(expression.value[i])) {
                flag = false
                break
            }
        }
        return flag
    }

    private fun clearExpression() {
        actionIsClicked = false
        numberIsClicked = false
        doubleInExpression = false
        expression.value = ""
    }


    private fun toDouble() {
        if (!doubleInExpression) {
            expression.value += "."
            doubleInExpression = true
        }
    }


    private fun calculate() {
        actionIsClicked = false
        numberIsClicked = false
        doubleInExpression = false
        var result = ""
        if (!isOperatorInExpression()) {
            val exp = expression.value
            var i = 1
            var firstNumber = ""
            firstNumber += exp[0]
            while (!operationsArray.contains(exp[i])) {
                firstNumber += exp[i]
                i += 1
                if (i > exp.length - 1) {
                    expression.value = firstNumber
                }
            }
            var secondNumber = ""
            var j = i + 1
            while (j <= exp.length - 1) {
                secondNumber += exp[j]
                j += 1
            }
            if (exp[i] == '+') {
                result = (firstNumber.toDouble() + secondNumber.toDouble()).toString()
            } else if (exp[i] == '-') {
                result = (firstNumber.toDouble() - secondNumber.toDouble()).toString()
            } else if (exp[i] == '*') {
                result = (firstNumber.toDouble() * secondNumber.toDouble()).toString()
            } else if (exp[i] == '/') {
                if (secondNumber.toInt() == 0) {
                    result = "Error"
                    errorInString = true
                } else {
                    result = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
                }
            }
            expression.value = if (result.endsWith(".0")) {
                result.substring(0, result.length - 2)
            } else {
                result
            }
        }
    }

    private fun toPercent() {
        if (!errorInString) {
            calculate()
            var result: String = expression.value
            result = (result.toDouble() * 0.01).toString()
            expression.value = result
        }
    }

    private fun toPositive() {
        var newExpression = ""
        for (i in 1 until expression.value.length) {
            newExpression += expression.value[i]
        }
        expression.value = newExpression
    }

    private fun toNegative() {
        var newExpression = "-"
        expression.value.forEach { newExpression += it }
        expression.value = newExpression
    }

    private fun signChange() {
        if (expression.value[0] == '-') {
            toPositive()
        } else {
            toNegative()
        }
    }
}


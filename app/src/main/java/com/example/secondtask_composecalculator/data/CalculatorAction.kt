package com.example.secondtask_composecalculator.data

import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import com.example.secondtask_composecalculator.ui.theme.ActionButtonColor
import com.example.secondtask_composecalculator.ui.theme.NumberButtonColor


var numberIsClicked = false
var actionIsClicked = false
var errorInString = false
var doubleInExpression = false
val operationsArray = listOf('-', '+', '*', '/')

class CalculatorAction {

    fun handleButtonClick(buttonSymbol: ActionEnum, expression: MutableState<String>) {
        when (buttonSymbol) {
            ActionEnum.PLUS -> {
                addPlusOnExpression(expression = expression)
            }
            ActionEnum.DIVIDE -> {
                addDivideOnExpression(expression = expression)
            }
            ActionEnum.MULTIPLY -> {
                addMultiplyOnExpression(expression = expression)
            }
            ActionEnum.MINUS -> {
                addMinusOnExpression(expression = expression)
            }
            ActionEnum.SIGN -> {
                signChange(expression)
            }
            ActionEnum.CALCULATE -> {
                calculate(expression)
            }
            ActionEnum.PERCENT -> {
                toPercent(expression)
            }
            ActionEnum.CLEAR -> {
                clearExpression(expression)
            }
            ActionEnum.DOUBLE -> {
                toDouble(expression)
            }
            else -> {
                addNumberOnExpression(buttonSymbol, expression)
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

    fun changeDelColor(expression: MutableState<String>): Color {
        val color: Color
        if (expression.value == "") {
            color = Color.DarkGray
        } else {
            color = Color.White
        }
        return color
    }

    fun oneCharDelete(expression: MutableState<String>) {
        if (expression.value.isNotEmpty()) {
            expression.value = expression.value.substring(0, expression.value.length - 1)
        }
    }


    private fun addNumberOnExpression(buttonSymbol: ActionEnum, expression: MutableState<String>) {
        expression.value += buttonSymbol.symbol
        numberIsClicked = true
    }

    private fun addMinusOnExpression(expression: MutableState<String>) {
        if (!actionIsClicked and !errorInString) {
            expression.value += ActionEnum.MINUS.symbol
            actionIsClicked = true
            doubleInExpression = false
        }
    }

    private fun addPlusOnExpression(expression: MutableState<String>) {
        if (!actionIsClicked and !errorInString) {
            expression.value += ActionEnum.PLUS.symbol
            actionIsClicked = true
            doubleInExpression = false
        }
    }

    private fun addDivideOnExpression(expression: MutableState<String>) {
        if (!actionIsClicked and !errorInString) {
            expression.value += "/"
            actionIsClicked = true
            doubleInExpression = false
        }
    }


    private fun addMultiplyOnExpression(expression: MutableState<String>) {
        if (!actionIsClicked and !errorInString) {
            expression.value += "*"
            actionIsClicked = true
            doubleInExpression = false
        }
    }


    private fun isOperatorInExpression(expression: MutableState<String>): Boolean {
        var flag = true
        for (i in 0 until expression.value.length) {
            if (operationsArray.contains(expression.value[i])) {
                flag = false
                break
            }
        }
        return flag
    }

    private fun clearExpression(expression: MutableState<String>) {
        actionIsClicked = false
        numberIsClicked = false
        errorInString = false
        expression.value = ""
    }


    private fun toDouble(expression: MutableState<String>) {
        if (numberIsClicked and !doubleInExpression) {
            expression.value += "."
            doubleInExpression = true
        }
    }

    fun calculate(expression: MutableState<String>){
        actionIsClicked = false
        numberIsClicked = false
        doubleInExpression = false
        var result = ""
        if (!isOperatorInExpression(expression)){
            val exp  = expression.value
            var i = 1
            var firstNumber = ""
            firstNumber += exp[0]
            while (!operationsArray.contains(exp[i])){
                firstNumber += exp[i]
                i += 1
                if (i > exp.length - 1){
                    expression.value = firstNumber
                }
            }
            var secondNumber = ""
            var j = i + 1
            while(j <= exp.length - 1){
                secondNumber += exp[j]
                j += 1
            }
            if (exp[i] == '+'){
                result = (firstNumber.toDouble() + secondNumber.toDouble()).toString()
            }
            else if (exp[i] == '-'){
                result = (firstNumber.toDouble() - secondNumber.toDouble()).toString()
            }
            else if (exp[i] == '*'){
                result = (firstNumber.toDouble() * secondNumber.toDouble()).toString()
            }
            else if (exp[i] == '/'){
                if (secondNumber.toInt() == 0){
                    result = "Error"
                    errorInString = true
                }
                else{
                    result = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
                }
            }
            expression.value = if (result.endsWith(".0")) {
                result.substring(0, result.length - 2)
            }
            else{
                result
            }
        }
    }


    private fun toPercent(expression: MutableState<String>) {
        var result: String = expression.value
        result = (result.toDouble() * 0.01).toString()
        expression.value = result
    }

    private fun toPositive(expression: MutableState<String>) {
        expression.value.drop(1)
    }

    private fun toNegative(expression: MutableState<String>) {
        var newExpression = "-"
        expression.value.forEach { newExpression += it }
        expression.value = newExpression
    }

    private fun signChange(expression: MutableState<String>) {
        if (expression.value[0] == '-') {
            toPositive(expression)
        } else {
            toNegative(expression)
        }
    }
}


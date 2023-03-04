package com.example.secondtask_composecalculator

import androidx.compose.runtime.MutableState
import com.example.secondtask_composecalculator.data.ActionEnum


val operationsArray = listOf('-', '+', '*', '/')
var numberIsClicked = false
var actionIsClicked = false
var ErrorInString = false
var doubleInExpression = false

fun String.isNumber(): Boolean {
    return this.toIntOrNull() != null
}

fun handleButtonClick(buttonSymbol: ActionEnum, expression: MutableState<String>) {
    when (buttonSymbol) {
        ActionEnum.PLUS -> {
            addPlusOnExpression(expression = expression)
        }
        ActionEnum.DIVIDE -> {
            addDivideOnExpression(expression = expression)
        }
        ActionEnum.MULTIPLY ->{
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
             if (buttonSymbol.symbol.isNumber()){
                 addNumberOnExpression(buttonSymbol, expression)
             }
        }
    }
}

fun addNumberOnExpression(buttonSymbol: ActionEnum, expression: MutableState<String>){
    expression.value += buttonSymbol.symbol
    numberIsClicked = true
}


fun addMinusOnExpression(expression: MutableState<String>){
    if (!actionIsClicked and !ErrorInString){
        expression.value += ActionEnum.MINUS.symbol
        actionIsClicked = true
        doubleInExpression = false
    }
}

fun addPlusOnExpression(expression:MutableState<String>){
    if (!actionIsClicked and !ErrorInString){
        expression.value += ActionEnum.PLUS.symbol
        actionIsClicked = true
        doubleInExpression = false
    }
}

fun addDivideOnExpression(expression: MutableState<String>){
    if (!actionIsClicked and !ErrorInString){
        expression.value += "/"
        actionIsClicked = true
        doubleInExpression = false
    }
}

fun addMultiplyOnExpression(expression: MutableState<String>){
    if (!actionIsClicked and !ErrorInString){
        expression.value += "*"
        actionIsClicked = true
        doubleInExpression = false
    }
}


fun isOperatorInExpression(expression: MutableState<String>): Boolean{
    var flag = true
    for (i in 0 until expression.value.length){
        if (operationsArray.contains(expression.value[i])){
            flag = false
            break
        }
    }
    return flag
}


fun clearExpression(expression: MutableState<String>){
    actionIsClicked = false
    numberIsClicked = false
    expression.value = ""
    ErrorInString = false
}

fun toDouble(expression: MutableState<String>){
    if (numberIsClicked and !doubleInExpression){
        expression.value += "."
        doubleInExpression = true
    }
}


fun oneCharDelete(expression: MutableState<String>){
    if (expression.value.isNotEmpty()) {
        expression.value = expression.value.substring(0, expression.value.length - 1) }
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
                ErrorInString = true
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


fun toPercent(expression: MutableState<String>){
    var result: String = expression.value
    result = (result.toDouble() * 0.01).toString()
    expression.value = result
}

fun toPositive(expression: MutableState<String>) {
    var newExpression = ""
    for (i in 1 until expression.value.length){
        newExpression += expression.value[i]
    }
    expression.value = newExpression
}

fun toNegative(expression: MutableState<String>){
    var newExpression = "-"
    for (i in 0 until expression.value.length){
        newExpression += expression.value[i]
    }
    expression.value = newExpression
}

fun signChange(expression: MutableState<String>){
    if (expression.value[0] == '-'){
        toPositive(expression)
    }
    else{
        toNegative(expression)
    }
}


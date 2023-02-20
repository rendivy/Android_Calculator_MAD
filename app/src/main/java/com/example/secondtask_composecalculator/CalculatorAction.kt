package com.example.secondtask_composecalculator

import androidx.compose.runtime.MutableState


val operationsArray = listOf('-', '+', '*', '/')

fun isOperatorInExpression(expression: MutableState<String>): Boolean{
    var flag = true
    for (i in 0..expression.value.length - 1){
        if (operationsArray.contains(expression.value[i])){
            flag = false
            break
        }
    }
    return flag
}

fun Calculate(expression: MutableState<String>){
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
            expression.value = (firstNumber.toDouble() + secondNumber.toDouble()).toString()
        }
        else if (exp[i] == '-'){
            expression.value = (firstNumber.toDouble() - secondNumber.toDouble()).toString()
        }
        else if (exp[i] == '*'){
            expression.value = (firstNumber.toDouble() * secondNumber.toDouble()).toString()
        }
        else if (exp[i] == '/'){
            if (secondNumber.toInt() == 0){
                expression.value = "Error"
            }
            else{
                expression.value = (firstNumber.toDouble() / secondNumber.toDouble()).toString()
            }
        }
    }
}

fun Precentage(expression: MutableState<String>){
    var result: String = expression.value
    result = (result.toDouble() * 0.01).toString()
    expression.value = result
}

fun toPostitive(expression: MutableState<String>) {
    var newExpression: String = ""
    for (i in 1..expression.value.length - 1){
        newExpression += expression.value[i]
    }
    expression.value = newExpression
}

fun toNegative(expression: MutableState<String>){
    var newExpression: String = "-"
    for (i in 0..expression.value.length - 1){
        newExpression += expression.value[i]
    }
    expression.value = newExpression
}

fun signChange(expression: MutableState<String>){
    if (expression.value[0] == '-'){
        toPostitive(expression)
    }
    else{
        toNegative(expression)
    }
}


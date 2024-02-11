package com.example.calculatorapp

/*
* Utility function for String to check if a character is an operator.
*
* @param  token  The given token for inspection
*
* @return  Boolean  Indication of whether the character is an operator
*/
fun isBinaryOperator(token: String): Boolean { return token in setOf( "+", "-", "×", "÷", "^" ) }
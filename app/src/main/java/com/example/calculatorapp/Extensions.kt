package com.example.calculatorapp

/*
* Extension function for String to check if it contains any operator.
*
* @return  Boolean  Indication of whether the string contains any operator
*/
fun String.containsAnyOperator(): Boolean {

    return this.contains('+') || this.contains('-') || this.contains('×')
            || this.contains('÷') || this.contains('^') || this.contains('√')

}
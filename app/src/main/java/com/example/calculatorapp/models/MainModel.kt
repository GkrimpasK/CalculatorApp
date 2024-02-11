package com.example.calculatorapp.models

import android.util.Log
import com.example.calculatorapp.Calculator
import com.example.calculatorapp.Formatter
import com.example.calculatorapp.containsAnyOperator
import com.example.calculatorapp.isBinaryOperator
import java.lang.StringBuilder

class MainModel{

    var currentInput = StringBuilder("0")

    val formatter = Formatter()
    val calculator = Calculator()

    /*
    * Checks if our input is in a valid format,
    * when a digit is pressed. If it's valid,
    * returns True, otherwise it returns False.
    *
    * @param  digit  The pressed digit
    */
    fun isValidDigit(digit: String): Boolean{

        // Split numbers and operators to handle special cases
        val lastOperand = currentInput.split( Regex("[+\\-×÷^]") ).last()

        // Check for unwanted leading zeros
        if ( digit == "0" && currentInput.toString() == "0" ) return false
        if ( digit == "0" && lastOperand.startsWith("0" ) && !lastOperand.contains(".") ) return false

        // Allow only one decimal '.' per number
        if ( digit == "." && isBinaryOperator(currentInput.last().toString() )
            || digit == "." && lastOperand.contains(".") ) return false

        return true

    }

    /*
    * Checks if our input is in a valid format,
    * when an operator is pressed. If it's valid,
    * returns True, otherwise it returns False.
    */
    fun isValidOperator(): Boolean{

        if ( currentInput.last().toString() == "." ) return false

        return true

    }

    /*
    * Checks if our input is in a valid format,
    * to perform the % operation. If it's valid,
    * returns True, otherwise it returns False.
    */
    fun isValidPercent(): Boolean{

        if ( currentInput.toString() == "0" || currentInput.last() == '.' || isBinaryOperator( currentInput.last().toString() ) ) return false

        return true

    }

    /*
    * Checks if our input is in a valid format,
    * to move on with the calculation. If it's valid,
    * returns True, otherwise it returns False.
    */
    fun isValidEqual(): Boolean{

        // Only let a specific format pass through
        if ( currentInput.toString() == "0" || currentInput.last() == '.') return false

        else if ( isBinaryOperator( currentInput.last().toString() )
            || !currentInput.toString().containsAnyOperator() ) return false

        return true

    }


    /*
    * Handles some special cases like, deleting unwanted zeros
    * and fixes the current input
    *
    * @param  digit  The pressed digit
    */
    fun fixCurrentInputDigit(digit: String){

        // Split numbers and operators to handle special cases
        val lastOperand = currentInput.split( Regex("[+\\-×÷^]") ).last()

        // If the first number after an operator is 0, deletes that 0
        if ( digit != "." && lastOperand.startsWith("0" ) && !lastOperand.contains(".") ) currentInput.deleteCharAt(currentInput.length - 1 )

        else if ( digit != "." && currentInput.toString() == "0" ) currentInput.deleteCharAt(0)

    }

    /*
    * Replaces the last operator and fixes the current input
    */
    fun fixCurrentInputOperator(){

        // Deletes operator, if it's the last character on our input
        if( isBinaryOperator( currentInput.last().toString() ) ) currentInput.deleteCharAt(currentInput.length - 1)

    }

    /*
    * Appends a value into our current input
    *
    * @param  value  Either a digit or an operator
    */
    fun appendToCurrentInput(value: String){

        currentInput.append(value)

    }

    /*
    * Deletes a single character from our input,
    * at the specified index.
    *
    * @param  index  The desired position of the string
    */
    fun deleteCharFromCurrentInputAt(index: Int){

        currentInput.deleteCharAt(index)

    }

    /*
    * Deletes all of the given characters from our input.
    *
    * @param  char  The desired character for deletion
    */
    fun deleteCharsFromCurrentInput(char: Char) {

        if ( currentInput.contains(char) ){

            while (currentInput.contains(char)){

                val index = currentInput.indexOf(char)

                Log.d("check", "Character index: $index")
                Log.d("check", "Input: $currentInput")

                currentInput.deleteCharAt(index)

            }

        }

    }

    /*
    * Performs and saves various results from other functions
    * at the specified index.
    *
    * @param  index  The desired position of the string
    */
    fun evaluateExpression(expression: String): String{

        // Turn the whole expression into individual tokens,
        val tokens = formatter.tokenizeExpression(expression)

        // Calculate those tokens with precedence
        val calculation = calculator.calculateWithPrecedence(tokens)

        // Beautify the result
        val result = formatter.beautify(calculation)

        return result

    }


}
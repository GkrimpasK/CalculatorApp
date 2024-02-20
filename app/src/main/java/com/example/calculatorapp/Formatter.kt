package com.example.calculatorapp

import android.util.Log
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class Formatter {

    /*
    * Splits the given expression into individual tokens,
    * separating numbers and operators.
    *
    * @param  expression  The expression to be tokenized
    *
    * @return  MutableList<String>  A list of tokens
    */
    fun tokenizeExpression(expression: String): MutableList<String> {

        val tokens = mutableListOf<String>()
        val sb = StringBuilder()

        var digitNegativeFlag = 0

        for (char in expression) {

            // Append a negative number and raise a flag
            if (char == expression.first() && expression.first() == '-' && digitNegativeFlag == 0) {
                sb.append("-")
                digitNegativeFlag++
                continue
            }
            // Allow decimal points to be added along with numbers
            if (char.isDigit() || char == '.') sb.append(char)

            // Skip any commas
            else if(char == ',') continue

            // Operator
            else {

                // Add last whole number to tokens and clear builder
                if (sb.isNotEmpty()) {
                    tokens.add(sb.toString())
                    sb.clear()
                }

                // Finally, add operator
                tokens.add(char.toString())

            }

        }
        if (sb.isNotEmpty()) tokens.add(sb.toString())

        Log.d("check", "Completed Tokenization: $tokens")
        return tokens

    }

    /*
    * Beautifies the string representation of a calculation result,
    * making it more readable.
    *
    * @param  calculation  The string representation of the calculation result
    *
    * @return String  The beautified string
    */
    fun beautify(calculation: String): String{

        // Allows only 10 decimal points
        var formattedResult = "%.10f".format( calculation.toDouble() )

        // Handles special decimal cases
        if (formattedResult.contains(",") ) formattedResult = formattedResult.replace(",", ".")

        // Remove leading zeros
        while ( formattedResult.last() == '0' ) formattedResult = formattedResult.dropLast(1)
        if ( formattedResult.last() == '.' || formattedResult.last() == ',' ) formattedResult = formattedResult.dropLast(1)

        // Handles special number cases
        if (formattedResult.length > 16 || formattedResult == "-0") { return "error" }

        // Handles division by zero
        else if(formattedResult == "Infinity" || formattedResult == "NaN") { return "infinity_error" }

        Log.d("check", "Formatted result before commas: $formattedResult")

        formattedResult = addCommas(formattedResult)

        Log.d("check", "Formatted result after commas: $formattedResult")

        return formattedResult

    }

    /*
    * Splits the given input into two parts, integer and decimal,
    * and adds commas for better readability.
    *
    * @param  input  The input string
    *
    * @return  String  The formatted string with commas
    */
    private fun addCommas(input: String): String {

        val parts = input.split(".")

        if(parts[0].length < 4) return input

        val integerPart = addCommasToInteger(parts[0])

        val decimalPart = if (parts.size > 1) "." + parts[1] else ""

        return integerPart + decimalPart

    }

    /*
    * Adds commas to the integer part of a number for better readability.
    *
    * @param  integer  The integer part of a number
    *
    * @return  String  The formatted string with commas
    */
    private fun addCommasToInteger(integer: String): String {

        val formatter = DecimalFormat("#,###", DecimalFormatSymbols(Locale.US))
        return formatter.format(integer.toLong())

    }

}
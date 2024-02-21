package com.example.calculatorapp.controllers

import com.example.calculatorapp.MainActivity
import com.example.calculatorapp.models.MainModel

class MainController(mainActivity: MainActivity) {

    var mainView: MainActivity = mainActivity
    var mainModel: MainModel = MainModel()

    /*
    * Handles the view as a button click for digits and updates
    * the display, after appending it to the current input.
    *
    * @param  view  The view representing the pressed digit button
    */
    fun onDigitClick(digit: String){

        val isValid = mainModel.isValidDigit(digit)

        if (isValid) {

            mainModel.fixCurrentInputDigit(digit)
            mainModel.appendToCurrentInput(digit)

            mainView.resetPreview()

            mainModel.deleteCharsFromCurrentInput(',')
            mainView.updateDisplay( mainModel.currentInput.toString() )

        }

        mainView.changeDisplaySize( calculateDisplayChange() )

    }

    /*
    * Handles the view as a button click for operators and updates
    * the display, after appending it to the current input.
    *
    * @param  view  The view representing the pressed operator button
    */
    fun onOperatorClick(operator: String) {

        val isValid = mainModel.isValidOperator()

        if (isValid) {

            mainModel.fixCurrentInputOperator()
            mainModel.appendToCurrentInput(operator)

            mainView.resetPreview()

            mainModel.deleteCharsFromCurrentInput(',')
            mainView.updateDisplay( mainModel.currentInput.toString() )

        }

        mainView.changeDisplaySize( calculateDisplayChange() )

    }

    /*
    * Handles the click event for the percent button and updates
    * the display, after appending the correct operation
    * to the current input.
    */
    fun onPercentClick() {

        val isValid = mainModel.isValidPercent()

        if (isValid){

            mainModel.deleteCharsFromCurrentInput(',')

            mainModel.appendToCurrentInput("÷")
            mainModel.appendToCurrentInput("100")

            val expression = mainModel.currentInput.toString()

            val result = mainModel.evaluateExpression(expression)

            if (result == "error"){

                mainView.displayError()
                mainView.resetAll()
                return

            }

            mainModel.currentInput.clear()
            mainModel.appendToCurrentInput(result)

            mainView.resetPreview()
            mainView.updateDisplay(result)

        }

    }

    /*
    * Handles the click event for the equal button and updates
    * the display, after calculating the operations.
    */
    fun onEqualClick() {

        val isValid = mainModel.isValidEqual()

        if(isValid) {

            mainView.updatePreview(mainModel.currentInput.toString() + "=")

            val expression = mainModel.currentInput.toString()

            val result = mainModel.evaluateExpression(expression)

            if (result == "error") {

                mainView.displayError()
                mainView.resetAll()
                return

            }
            else if (result == "infinity_error") {

                mainView.updateDisplay("∞")
                mainModel.currentInput.clear()
                mainModel.currentInput.append(0)
                return

            }

            mainModel.currentInput.clear()
            mainModel.appendToCurrentInput(result)

            mainView.updateDisplay(result)

        }

        mainView.changeDisplaySize( calculateDisplayChange() )

    }

    /*
    * Handles the click event for the backspace button and updates
    * the display, after deleting the last character
    * from the current input.
    */
    fun onBackspaceClick() {

        mainView.resetPreview()

        // Reset everything, when deleting a one digit number
        if ( mainModel.currentInput.length == 1 || ( mainModel.currentInput.length == 2 && mainModel.currentInput.first() == '-' ) ){

            mainView.resetAll()

            mainModel.deleteCharsFromCurrentInput(',')
            mainView.updateDisplay( mainModel.currentInput.toString() )

        }
        // Else, just delete the last character
        else {

            mainModel.deleteCharFromCurrentInputAt(mainModel.currentInput.length - 1)

            mainModel.deleteCharsFromCurrentInput(',')
            mainView.updateDisplay( mainModel.currentInput.toString() )

        }

        mainView.changeDisplaySize( calculateDisplayChange() )

    }

    /*
    * Handles the click event for the clear button and updates
    * the display, after resetting the current input.
    */
    fun onClearClick() {

        mainView.resetAll()
        mainView.updateDisplay( mainModel.currentInput.toString() )

        mainView.changeDisplaySize( calculateDisplayChange() )

    }

    /*
    * Resets the current input to "0".
    */
    fun resetCurrentInput(){

        mainModel.currentInput.clear()
        mainModel.currentInput.append("0")

    }

    /*
    * Checks the length of the current input and
    * returns a float value accordingly.
    *
    * @return  Float  Returns the
    */
    private fun calculateDisplayChange(): Float{

        if(mainModel.currentInput.length > 18) return 28f

        else if(mainModel.currentInput.length > 16) return 30f

        else if(mainModel.currentInput.length > 12) return 35f

        return 40f

    }

}
package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import com.example.calculatorapp.databinding.ActivityMainBinding
import android.widget.Toast
import com.example.calculatorapp.controllers.MainController

class MainActivity : AppCompatActivity() {

    private val controller = MainController()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Interactive Buttons Listeners
        binding.imgBtnScientific.setOnClickListener{

            Toast.makeText(applicationContext, "Soon...", Toast.LENGTH_SHORT).show()

        }

        binding.imgBtnThemes.setOnClickListener{

            Toast.makeText(applicationContext, "Soon...", Toast.LENGTH_SHORT).show()

        }

        binding.imgBtnHistory.setOnClickListener{

            Toast.makeText(applicationContext, "Soon...", Toast.LENGTH_SHORT).show()

        }

        //Number Buttons Listeners
        binding.btnNum0.setOnClickListener{

            controller.onDigitClick("0")

        }

        binding.btnNum1.setOnClickListener{

            controller.onDigitClick("1")

        }

        binding.btnNum2.setOnClickListener{

            controller.onDigitClick("2")

        }

        binding.btnNum3.setOnClickListener{

            controller.onDigitClick("3")

        }

        binding.btnNum4.setOnClickListener{

            controller.onDigitClick("4")

        }

        binding.btnNum5.setOnClickListener{

            controller.onDigitClick("5")

        }

        binding.btnNum6.setOnClickListener{

            controller.onDigitClick("6")

        }

        binding.btnNum7.setOnClickListener{

            controller.onDigitClick("7")

        }

        binding.btnNum8.setOnClickListener{

            controller.onDigitClick("8")

        }

        binding.btnNum9.setOnClickListener{

            controller.onDigitClick("9")

        }

        binding.btnSymDecimal.setOnClickListener {

            controller.onDigitClick(".")

        }

        //Symbol Buttons Listeners
        binding.btnSymPlus.setOnClickListener {

            controller.onOperatorClick("+")

        }

        binding.btnSymMinus.setOnClickListener {

            controller.onOperatorClick("-")

        }

        binding.btnSymMultiply.setOnClickListener {

            controller.onOperatorClick("×")

        }

        binding.btnSymDivide.setOnClickListener {

            controller.onOperatorClick("÷")

        }

        binding.btnSymEqual.setOnClickListener {

            controller.onEqualClick()

        }

        binding.btnSymPercent.setOnClickListener {

            controller.onPercentClick()

        }

        binding.btnSymDel.setOnClickListener {

            controller.onBackspaceClick()

        }

        binding.btnSymClear.setOnClickListener {

            controller.onClearClick()

        }

    }

    /*
    * Updates the display with the provided value.
    *
    * @param  value  The value to be displayed
    */
    fun updateDisplay(value: String) {

        binding.displayText.text = value

    }

    /*
    * Updates the preview text with the provided value.
    *
    * @param  value  The value to be displayed in the preview
    */
    fun updatePreview(value : String) {

        binding.textPreview.text = value

    }

    /*
    * Displays "Error" on the main display.
    */
    fun displayError() { updateDisplay("Error") }

    /*
    * Resets all views and clears the preview text.
    */
    fun resetAll() {

        controller.resetCurrentInput()

        binding.textPreview.text = ""

    }

    /*
    * Clears the preview text.
    */
    fun resetPreview() { binding.textPreview.text = "" }

    /*
    * Changes the main text's size, to make it fit into the screen.
    *
    * @param  value  The desired value size
    */
    fun changeDisplaySize(value: Float){

        binding.displayText.setTextSize(TypedValue.COMPLEX_UNIT_SP, value)

    }

}
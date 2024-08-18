package com.practice.calculator.utils

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import com.practice.calculator.errorLogs

fun debugLogs(message: String?){
    Log.d("SGDETECTIVE",message?:"nothing")
}

fun showToast(context: Context, message: String){
    Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
}

fun disableSoftInputFromAppearing(editText: EditText) {
    try {
        editText.apply{
            setOnFocusChangeListener { _, hasFocus ->
                if(!hasFocus){
                    requestFocus()
                }
            }
            showSoftInputOnFocus = false
            isActivated = true
            isPressed = true
        }
    }catch (e: Exception){
        e.errorLogs()
    }
}

fun setEditTextAtCursorPosition(editText: EditText, textToBeAdded: String?=null, operator: Int = 1) {

    /**
     * 0 -> backspace
     * 1 -> add character or operator
     */
    debugLogs(textToBeAdded)
    try {
        val cursorPosition = editText.selectionStart
        val enteredText = editText.text.toString()
        if(cursorPosition != -1){
            debugLogs("is this happening??")
            val startToCursor = enteredText.subSequence(0, cursorPosition)
            var cursorToEnd: CharSequence = ""
            when (operator) {
                0 -> {
                    val cursorPositionEnd = editText.selectionEnd
                    cursorToEnd = enteredText.subSequence(cursorPositionEnd + 1, enteredText.length)
                }

                1 -> {
                    debugLogs("${enteredText[cursorPosition-1]} && ${textToBeAdded!!} for ${checkWhichOperator(enteredText[cursorPosition], textToBeAdded!!).toString()}")
                    cursorToEnd =
                        if (checkWhichOperator(enteredText[cursorPosition-1], textToBeAdded!!)) {
                            enteredText.subSequence(
                                cursorPosition,
                                enteredText.length
                            )
                        } else {
                            enteredText.subSequence(
                                cursorPosition + 1,
                                enteredText.length
                            )
                        }
                }
            }
            val finalString: CharSequence = "$startToCursor$cursorToEnd"
            editText.setText(finalString)
        }else{
            val appendedString = editText.text.toString() + textToBeAdded
            editText.setText(appendedString)
        }
    } catch (e: Exception) {
        e.errorLogs()
    }
}

fun checkWhichOperator(existingCharacter: Char, newCharacter: String): Boolean{
    try {
        if(existingCharacter.digitToIntOrNull() != null || newCharacter.isDigitsOnly()){
            return true
        }else if((existingCharacter == '+' || existingCharacter == '-') && (newCharacter == "%" )){
            return true
        }else if((existingCharacter == '*' || existingCharacter == '/') && (newCharacter == "-" || newCharacter == "%")){
            return true
        }else if((existingCharacter == '%')){
            return true
        }
    }catch (e: Exception){
        e.errorLogs()
    }
    return false
}

fun getFormattedValue(value: Double?):String?{
    val originalValue = value ?: return null
    return if(originalValue%1 == 0.0){
        originalValue.toInt().toString()
    }else{
        originalValue.toString()
    }
}
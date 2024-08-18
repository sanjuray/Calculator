package com.practice.calculator

import android.util.Log

fun Exception.errorLogs(){
    val e: Exception = this
    Log.e("SGERROR","$e")
    e.printStackTrace()
}
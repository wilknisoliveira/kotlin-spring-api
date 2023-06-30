package com.springkotlin.converters

object NumberConverter {
    fun isNumeric(number: String?): Boolean{
        if(number.isNullOrBlank())
            return false

        val result = number.replace(",".toRegex(), ".")

        return result.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    fun convertToDouble(number: String?): Double{
        if(number.isNullOrBlank())
            return 0.0

        val result = number.replace(",".toRegex(), ".")

        if(isNumeric(result))
            return number.toDouble()
        else
            return 0.0
    }
}
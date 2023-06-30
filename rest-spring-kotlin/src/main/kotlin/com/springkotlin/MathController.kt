package com.springkotlin

import com.springkotlin.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/sum/{numberOne}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a valid number")
        return convertToDouble(numberOne) + convertToDouble(numberTwo)
    }

    private fun isNumeric(number: String?): Boolean{
        if(number.isNullOrBlank())
            return false

        val result = number.replace(",".toRegex(), ".")

        return result.matches("""[-+]?[0-9]*\.?[0-9]+""".toRegex())
    }

    private fun convertToDouble(number: String?): Double{
        if(number.isNullOrBlank())
            return 0.0

        val result = number.replace(",".toRegex(), ".")

        if(isNumeric(result))
            return number.toDouble()
        else
            return 0.0
    }

}
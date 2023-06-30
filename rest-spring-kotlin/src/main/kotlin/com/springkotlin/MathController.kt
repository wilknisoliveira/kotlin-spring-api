package com.springkotlin

import com.springkotlin.exceptions.UnsupportedMathOperationException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.sqrt

@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    @RequestMapping(value = ["/math/{numberOne}/{mathOperation}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value= "mathOperation") mathOperation: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double{
        if(!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a valid number")

        when(mathOperation){
            "+" -> return convertToDouble(numberOne) + convertToDouble(numberTwo)
            "-" -> return convertToDouble(numberOne) - convertToDouble(numberTwo)
            "*" -> return convertToDouble(numberOne) * convertToDouble(numberTwo)
            "x" -> return convertToDouble(numberOne) * convertToDouble(numberTwo)
            "X" -> return convertToDouble(numberOne) * convertToDouble(numberTwo)
            "/" -> return convertToDouble(numberOne) / convertToDouble(numberTwo)
            "avg" -> return (convertToDouble(numberOne) + convertToDouble(numberTwo))/2
            else -> throw UnsupportedMathOperationException("Please set a valid math operation")
        }
    }

    @RequestMapping(value = ["/math/sqrt/{number}"])
    fun squareRoot(@PathVariable(value="number") number: String?): Double{
        if(!isNumeric(number))
            throw UnsupportedMathOperationException("Please set a valid number")
        return sqrt(convertToDouble(number))
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
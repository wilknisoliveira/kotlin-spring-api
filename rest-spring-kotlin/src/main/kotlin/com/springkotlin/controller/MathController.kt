package com.springkotlin.controller

import com.springkotlin.converters.NumberConverter
import com.springkotlin.exceptions.UnsupportedMathOperationException
import com.springkotlin.math.SimpleMath
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import kotlin.math.sqrt

@RestController
class MathController {
    val counter: AtomicLong = AtomicLong()

    private val math: SimpleMath = SimpleMath()

    @RequestMapping(value = ["/math/{numberOne}/{mathOperation}/{numberTwo}"])
    fun sum(@PathVariable(value = "numberOne") numberOne: String?,
            @PathVariable(value= "mathOperation") mathOperation: String?,
            @PathVariable(value = "numberTwo") numberTwo: String?
    ): Double{
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a valid number")

        when(mathOperation){
            "+" -> return math.sum(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
            "-" -> return math.subtraction(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
            "*" -> return math.multiplication(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
            "/" -> return math.division(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
            else -> throw UnsupportedMathOperationException("Please set a valid math operation")
        }
    }

    @RequestMapping(value=["/math/avg/{numberOne}/{numberTwo}"])
    fun average(@PathVariable(value="numberOne") numberOne: String?,
                @PathVariable(value="numberTwo") numberTwo: String?
    ): Double{
        if(!NumberConverter.isNumeric(numberOne) || !NumberConverter.isNumeric(numberTwo))
            throw UnsupportedMathOperationException("Please set a valid number")
        return math.average(NumberConverter.convertToDouble(numberOne), NumberConverter.convertToDouble(numberTwo))
    }

    @RequestMapping(value = ["/math/sqrt/{number}"])
    fun squareRoot(@PathVariable(value="number") number: String?): Double{
        if(!NumberConverter.isNumeric(number))
            throw UnsupportedMathOperationException("Please set a valid number")
        return math.squareRoot(NumberConverter.convertToDouble(number))
    }


}
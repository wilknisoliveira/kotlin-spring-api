package com.springkotlin.exceptions

import java.util.Date


class ExceptionResponse(
    val timesTamp: Date,
    val message: String?,
    val details: String
)
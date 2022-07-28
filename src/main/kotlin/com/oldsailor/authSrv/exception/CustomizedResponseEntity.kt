package com.oldsailor.authSrv.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
@RestController
class CustomizedResponseEntity : ResponseEntityExceptionHandler() {

    @Override
    fun handleHttpMediaTypeNotAcceptable(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders?,
        status: HttpStatus?,
        request: WebRequest?
    ): ResponseEntity<Any?>? {
        val exceptionResponse = ExceptionResponse(
            Date(), "Internal server error",
            ex.bindingResult.toString()
        )
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }


    @ExceptionHandler(UserException::class)
    fun userExist(ex: Exception, request: WebRequest): ResponseEntity<Any?> {
        val exceptionResponse = ExceptionResponse(
            Date(), ex.message!!,
            request.getDescription(false)
        )
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }


}
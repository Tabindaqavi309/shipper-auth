package com.oldsailor.authSrv.exception

import java.util.*

class ExceptionResponse(var timeStamp: Date, var message: String, var details: String) {

    override fun toString(): String {
        return "ExceptionResponse{" +
                "timeStamp=" + timeStamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}'
    }
}
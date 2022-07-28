package com.oldsailor.authSrv.exception

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import java.io.IOException
import java.util.*
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@ControllerAdvice
@RestController
class CustomAuthenticationFailureHandler : AuthenticationEntryPoint {
    @Throws(IOException::class, ServletException::class)
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authenticationException: AuthenticationException
    ) {

        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        response.outputStream.println(  "{ "
                + "\"timestamp\": \""+ Date() + "\","
                + "\"message\": \""+ authenticationException.message + "\","
                + "\"status\": \""+ response.status + "\""
                + "}")
    }
}
package com.oldsailor.authSrv.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
class UserException(message: String?) : RuntimeException(message)

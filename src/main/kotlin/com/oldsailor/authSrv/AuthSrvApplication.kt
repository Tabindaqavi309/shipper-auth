package com.oldsailor.authSrv

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@SpringBootApplication
class AuthSrvApplication

fun main(args: Array<String>) {
	runApplication<AuthSrvApplication>(*args)

}

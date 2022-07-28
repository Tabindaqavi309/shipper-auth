package com.oldsailor.authSrv.utils

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

fun encryptPassword(password: String): String {
    val strength = 10
    return BCryptPasswordEncoder(strength, SecureRandom()).encode(password)
}
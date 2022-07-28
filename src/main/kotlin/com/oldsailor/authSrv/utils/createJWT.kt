package com.oldsailor.authSrv.utils

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import java.util.*
import java.util.stream.Collectors

fun createJWT(value: String): String {
    val secretKey = "oldsailorsdbSecretKey"
    val jwtExpirationMs = 840000000
    val grantedAuthorities: List<GrantedAuthority> = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER")

    return Jwts.builder()
        .setId("softtekJWT")
        .setSubject(value)
        .claim(
            "authorities",
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList())
        )
        .setIssuedAt(Date(System.currentTimeMillis()))
        .setExpiration(Date(System.currentTimeMillis() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, secretKey.toByteArray())
        .compact()

}
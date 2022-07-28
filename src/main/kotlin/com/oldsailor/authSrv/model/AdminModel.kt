package com.oldsailor.authSrv.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.*


@Table(name = "admins")
@Entity
data class AdminModel(
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val id: Int? = 0,
    val email: String = "",
    var password: String = "",
    var admin_role_id: Int = 0
)



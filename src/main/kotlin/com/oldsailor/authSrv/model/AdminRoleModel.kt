package com.oldsailor.authSrv.model

import javax.persistence.*


@Table(name = "admin_roles")
@Entity
data class AdminRoleModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val sub: String = ""
)


@Table(name = "admin_roles")
@Entity
data class UpdateAdminRoleModel(
    @Id
    val id: Int,
    var sub: String
)
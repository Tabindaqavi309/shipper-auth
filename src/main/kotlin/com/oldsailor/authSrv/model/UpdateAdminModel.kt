package com.oldsailor.authSrv.model



import javax.persistence.*

@Table(name = "admins")
@Entity
data class UpdateAdminModel(
    @Id
    val id: Int,
    var email: String?,
    var password: String?,
    var admin_role_id: Int?
)
package com.oldsailor.authSrv.repository

import com.oldsailor.authSrv.model.AdminModel
 import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface AdminRepository: CrudRepository<AdminModel, Int> {

    @Query( value = "SELECT * FROM admins", nativeQuery = true)
    fun findAdmins(): List<AdminModel>

    @Query(value = "SELECT * FROM admins WHERE email = ?1", nativeQuery = true)
    fun findByEmail(email: String): AdminModel?

}



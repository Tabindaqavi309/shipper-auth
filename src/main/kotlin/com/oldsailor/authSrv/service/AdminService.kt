package com.oldsailor.authSrv.service;

import com.oldsailor.authSrv.model.AdminModel
import com.oldsailor.authSrv.model.UpdateAdminModel
import com.oldsailor.authSrv.repository.AdminRepository
import com.oldsailor.authSrv.repository.UpdateAdminRepository
import com.oldsailor.authSrv.utils.createJWT
import com.oldsailor.authSrv.utils.encryptPassword
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom

@Service
class AdminService(
    val db: AdminRepository,
    val dbUpdate: UpdateAdminRepository,
) {

    fun findAdmins(): List<AdminModel> = db.findAdmins()

    fun findByEmail(email: String) = db.findByEmail(email)


    fun addAdmin(admin: AdminModel): Unit {
        var result = findByEmail(admin.email)
        when (result) {
            null -> {
                admin.password = encryptPassword(admin.password)
                db.save(admin)
            }
            else -> {
                throw Exception("User exist")
            }
        }
    }

    fun updateAdmin(admin: UpdateAdminModel): Unit {
        var result: UpdateAdminModel = dbUpdate.findById(admin.id).get()
        result.password = admin.password?.let { encryptPassword(it) }
        result.email = admin.email
        result.admin_role_id = admin.admin_role_id
        dbUpdate.save(result)
    }

    fun singIn(email: String, password: String): String {
        var result = findByEmail(email)
        when (result) {
            null -> throw Exception("Invalid email or password")
            else -> {
                //compare password
                val encoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
                if (encoder.matches(password, result.password)) {
                    return "${createJWT(result.id.toString())}";
                } else {
                    throw Exception("Invalid user or password")
                }
            }
        }
    }
}

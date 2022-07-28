package com.oldsailor.authSrv.service

import com.oldsailor.authSrv.model.AdminRoleModel
import com.oldsailor.authSrv.model.UpdateAdminModel
import com.oldsailor.authSrv.model.UpdateAdminRoleModel
import com.oldsailor.authSrv.repository.AdminRoleRepository
import com.oldsailor.authSrv.repository.UpdateAdminRoleRepository
import org.springframework.stereotype.Service

@Service
class AdminRoleService(val db: AdminRoleRepository, val dbUpdate: UpdateAdminRoleRepository) {

    fun addAdminRole(adminRole: AdminRoleModel): Unit {
       db.save(adminRole)
    }

    fun updateAdminRole(adminRole: UpdateAdminRoleModel): Unit {
        var result: UpdateAdminRoleModel = dbUpdate.findById(adminRole.id).get()
        result.sub = adminRole.sub
        dbUpdate.save(adminRole)
    }
}
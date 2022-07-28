package com.oldsailor.authSrv.controller

import com.oldsailor.authSrv.model.AdminRoleModel
import com.oldsailor.authSrv.model.UpdateAdminRoleModel
import com.oldsailor.authSrv.service.AdminRoleService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/auth-srv/admin-role")
class AdminRoleController(val adminRoleService: AdminRoleService) {

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun index(@RequestBody adminRole: AdminRoleModel) =  adminRoleService.addAdminRole(adminRole)

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    fun updateRole(@RequestBody adminRole: UpdateAdminRoleModel) =  adminRoleService.updateAdminRole(adminRole)

}
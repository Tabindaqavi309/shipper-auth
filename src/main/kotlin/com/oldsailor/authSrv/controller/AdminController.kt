package com.oldsailor.authSrv.controller

import com.oldsailor.authSrv.exception.UserException
import com.oldsailor.authSrv.model.AdminModel
import com.oldsailor.authSrv.model.UpdateAdminModel
import com.oldsailor.authSrv.model.dto.UpdatePasswordModel
import com.oldsailor.authSrv.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth-srv/admin")
class AdminController {

    @Autowired
    lateinit var adminService: AdminService


    @GetMapping("/")
    fun index(): List<AdminModel> {

        return   adminService.findAdmins()
    }

    @PostMapping("/", produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(code = HttpStatus.CREATED)
    fun post(@RequestBody admin: AdminModel) {
        try {
            adminService.addAdmin(admin)
        } catch (e: Exception) {
            throw UserException(e.message)
        }
    }

    @PutMapping("/")
    @ResponseStatus(code = HttpStatus.OK)
    fun updateAdmin(@RequestBody admin: UpdateAdminModel) = adminService.updateAdmin(admin)

    @PostMapping("/sign-in")
    @ResponseStatus(code = HttpStatus.OK)
    fun singIn(@RequestBody admin: UpdateAdminModel): String {
        try {
            return adminService.singIn(admin.email!!, admin.password!!)
        } catch (e: Exception) {
            throw UserException(e.message)
        }
    }

    @PostMapping("/change-password")
    @ResponseStatus(code = HttpStatus.OK)
    fun changePassword(@RequestHeader("Authorization") header: String, @RequestBody admin: UpdatePasswordModel): Unit {
        try {
            return adminService.updatePassword(header, admin)
        } catch (e: Exception) {
            throw UserException(e.message)
        }
    }
}
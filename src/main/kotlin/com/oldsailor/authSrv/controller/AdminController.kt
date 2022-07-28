package com.oldsailor.authSrv.controller

import com.oldsailor.authSrv.exception.UserException
import com.oldsailor.authSrv.model.AdminModel
import com.oldsailor.authSrv.model.UpdateAdminModel
import com.oldsailor.authSrv.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth-srv/admin")
class AdminController {

    @Autowired
    lateinit var adminService: AdminService


    @GetMapping("/")
    fun index(): List<AdminModel> = adminService.findAdmins()

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
}
package com.oldsailor.authSrv.repository

import com.oldsailor.authSrv.model.AdminRoleModel
import com.oldsailor.authSrv.model.UpdateAdminRoleModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRoleRepository: CrudRepository<AdminRoleModel, String>


@Repository
interface UpdateAdminRoleRepository: CrudRepository<UpdateAdminRoleModel, Int>
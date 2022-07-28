package com.oldsailor.authSrv.repository

import com.oldsailor.authSrv.model.UpdateAdminModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UpdateAdminRepository: CrudRepository<UpdateAdminModel, Int>
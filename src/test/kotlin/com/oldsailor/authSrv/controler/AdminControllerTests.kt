package com.oldsailor.authSrv.controler

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.oldsailor.authSrv.model.AdminModel
import com.oldsailor.authSrv.repository.AdminRepository
import com.oldsailor.authSrv.utils.createJWT
import com.oldsailor.authSrv.utils.encryptPassword
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@AutoConfigureMockMvc // auto-magically configures and enables an instance of MockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// Why configure Mockito manually when a JUnit 5 test extension already exists for that very purpose?
@ExtendWith(SpringExtension::class, MockitoExtension::class)
class AdminControllerTests {

    @Autowired
    lateinit var mockMvc: MockMvc

    @MockBean
    lateinit var adminRepository: AdminRepository

    @BeforeEach
    fun setup() {
        whenever(adminRepository.save(AdminModel(1, "Test", "test", 15))).thenAnswer {
            it.arguments.first()
        }
    }

    @Test
    fun `api should return 403 if jwt is not present`() {
        mockMvc.perform(
            get("/api/auth-srv/admin/")
        ).andExpect(status().isForbidden).andReturn()
    }

    @Test
    fun `controller should return list of admins`() {
        mockMvc.perform(
            get("/api/auth-srv/admin/").header("Authorization", "Bearer ${createJWT("email")}")
        ).andExpect(status().isOk).andReturn()

        verify(adminRepository, times(1)).findAdmins()
    }

    @Test
    fun `post controller should create admin`() {
        var password: String = encryptPassword("test")
        val admin = AdminModel(1, "Test", password, 15)

        mockMvc.post("/api/auth-srv/admin/") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(admin)
            accept = MediaType.APPLICATION_JSON
            header("Authorization", "Bearer ${createJWT("email")}")
        }.andExpect {
            status { isCreated() }
        }
    }

    @Test
    fun `post controller should fail while creating admin`() {
        mockMvc.post("/api/auth-srv/admin/") {
            contentType = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString("admin")
            accept = MediaType.APPLICATION_JSON
            header("Authorization", "Bearer ${createJWT("email")}")
        }.andDo { print() }
            .andExpect { status { is4xxClientError() } }
            .andReturn()
    }

    @Test
    fun `post api should return 403 if jwt is not present`() {
        mockMvc.perform(
            post("/api/auth-srv/admin/")
        ).andExpect(status().isForbidden).andReturn()
    }
}
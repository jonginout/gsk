package com.jonginout.gsk.application.api.controller

import com.jonginout.gsk.application.api.base.BaseControllerTest
import com.jonginout.gsk.model.domain.gsk.dto.account.AccountRequestBody
import org.junit.Test
import org.springframework.hateoas.MediaTypes
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AccountControllerTest : BaseControllerTest() {

    @Test
    fun `account 만들기 성공`() {
        val body = AccountRequestBody("test@test.co.kr", "123123")

        this.mockMvc.perform(
            post("/api/account/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(body))
        )
            .andDo(print())
            .andExpect(status().isCreated)
    }
}

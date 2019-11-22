package com.jonginout.gsk.application.api.controller

import com.jonginout.gsk.application.api.base.BaseControllerTest
import com.jonginout.gsk.model.domain.gsk.domain.account.Account
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import org.junit.Test
import org.springframework.hateoas.MediaTypes
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class EventControllerTest : BaseControllerTest() {

    @Test
    fun `event 목록 30개의 이벤트를 10개씩 두번째 페이지 조회`() {
        this.generateBasicData(30)

        this.mockMvc.perform(
            get("/api/event")
                .param("page", "1")
                .param("size", "10")
                .param("sort", "id,DESC")
        )
            .andDo(print())
            .andExpect(status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("page").exists())
    }

    @Test
    fun `event 상세 조회`() {
        val data = this.generateBasicData()
        val event = data["event"] as Event

        this.mockMvc.perform(
            get("/api/event/{id}", event.id)
        )
            .andExpect(status().isOk)
            .andDo(print())
            .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
    }

    @Test
    fun `event 만들기 성공`() {
        val body = this.generateEventRequestBody()

        this.mockMvc.perform(
            post("/api/event/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(body))
        )
            .andDo(print())
            .andExpect(status().isCreated)
    }

    @Test
    fun `event 수정하기`() {
        val data = this.generateBasicData()
        val event = data["event"] as Event
        val account = data["account"] as Account

        val body = EventRequestBody(
            name = "change_test_event_name",
            description = event.description!!,
            location = event.location!!,
            startAt = event.startAt!!,
            endAt = event.endAt!!,
            state = event.state!!,
            creatorId = account.id!!
        )

        this.mockMvc.perform(
            put("/api/event/{id}", event.id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(body))
        )

            .andExpect(status().isOk)
            .andDo(print())
            .andExpect(MockMvcResultMatchers.jsonPath("id").exists())
    }
}

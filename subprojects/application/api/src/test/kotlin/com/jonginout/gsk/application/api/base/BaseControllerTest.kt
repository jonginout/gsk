package com.jonginout.gsk.application.api.base

import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc

@AutoConfigureMockMvc
@Ignore
class BaseControllerTest : BaseTest() {

    @Autowired
    protected lateinit var mockMvc: MockMvc
}

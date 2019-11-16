package com.jonginout.gsk.domain.gsk.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
open class BadRequestException(message: String? = "요청 정보가 잘못되었습니다.") : RuntimeException(message)

package com.jonginout.gsk.model.domain.gsk.component

import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.exception.BadRequestException
import org.springframework.stereotype.Component

@Component
class EventValidator {
    fun validate(body: EventRequestBody) {
        if (body.endAt.isBefore(body.startAt)) {
            throw BadRequestException("이벤트 종료 시점이 시작 시점보다 이전일 수 없습니다.")
        }
    }
}

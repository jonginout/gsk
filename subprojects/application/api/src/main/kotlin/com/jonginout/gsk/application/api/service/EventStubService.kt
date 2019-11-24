package com.jonginout.gsk.application.api.service

import com.jonginout.gsk.common.protocol.extension.event.toEventRequestProto
import com.jonginout.gsk.common.protocol.extension.event.toEventUpdateRequestProto
import com.jonginout.gsk.model.domain.gsk.component.EventValidator
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.dto.event.EventUpdateRequestBody
import com.jonginout.gsk.model.domain.gsk.service.EventService
import com.jonginout.proto.gsk.event.EventDetailRequestProto
import com.jonginout.proto.gsk.event.EventGrpc.EventBlockingStub
import org.springframework.stereotype.Service

@Service
class EventStubService(
    private val eventService: EventService,
    private val eventValidator: EventValidator,
    private val eventStub: EventBlockingStub
) {

    fun create(body: EventRequestBody): Event {
        this.eventValidator.validate(body)
        val response = eventStub.createEvent(body.toEventRequestProto())
        return this.eventService.detail(response.id)
    }

    fun update(body: EventUpdateRequestBody): Event {
        this.eventValidator.validate(body)
        val response = eventStub.updateEvent(body.toEventUpdateRequestProto())
        return this.eventService.detail(response.id)
    }

    fun detail(id: Long): Event {
        val response = eventStub.detailEvent(
            EventDetailRequestProto.newBuilder().setId(id).build()
        )
        return this.eventService.detail(response.id)
    }
}

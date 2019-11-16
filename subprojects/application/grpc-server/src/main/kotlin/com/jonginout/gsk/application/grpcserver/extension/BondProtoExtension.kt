package com.jonginout.gsk.application.grpcserver.extension

import com.jonginout.gsk.common.protocol.extension.toLocalDateTime
import com.jonginout.gsk.common.protocol.extension.toTimestamp
import com.jonginout.gsk.domain.gsk.domain.account.Account
import com.jonginout.gsk.domain.gsk.domain.event.Event
import com.jonginout.gsk.domain.gsk.domain.event.EventState
import com.jonginout.gsk.domain.gsk.dto.event.EventRequestBody
import com.jonginout.proto.gsk.event.EventRequestProto
import com.jonginout.proto.gsk.event.EventResponseProto
import com.jonginout.proto.gsk.event.EventStateProto

fun EventRequestProto.toEvent(creator: Account): Event {
    return Event.newOf(
        name = this.name,
        description = this.description,
        location = this.location,
        startAt = this.startAt.toLocalDateTime(),
        endAt = this.endAt.toLocalDateTime(),
        state = EventState.valueOf(this.state.name),
        creator = creator
    )
}

fun Event.toEventRequestBody(): EventRequestBody {
    return EventRequestBody(
        name = this.name!!,
        description = this.description!!,
        location = this.location!!,
        startAt = this.startAt!!,
        endAt = this.endAt!!,
        state = this.state!!,
        creatorId = this.creator!!.id!!
    )
}

fun Event.toEventRequestProto(): EventRequestProto {
    return EventRequestProto.newBuilder()
        .setName(this.name)
        .setDescription(this.description)
        .setLocation(this.location)
        .setStartAt(this.startAt!!.toTimestamp())
        .setEndAt(this.endAt!!.toTimestamp())
        .setState(EventStateProto.valueOf(this.state!!.name))
        .setCreatorId(this.creator!!.id!!)
        .build()
}

fun Event.toEventResponseProto(): EventResponseProto {
    return EventResponseProto.newBuilder()
        .setName(this.name)
        .setDescription(this.description)
        .setLocation(this.location)
        .setStartAt(this.startAt!!.toTimestamp())
        .setEndAt(this.endAt!!.toTimestamp())
        .setState(EventStateProto.valueOf(this.state!!.name))
        .setCreatorId(this.creator!!.id!!)
        .setCreatorEmail(this.creator!!.email)
        .build()
}

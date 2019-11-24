package com.jonginout.gsk.common.protocol.extension.event

import com.jonginout.gsk.common.protocol.extension.toLocalDateTime
import com.jonginout.gsk.common.protocol.extension.toTimestamp
import com.jonginout.gsk.model.domain.gsk.domain.account.Account
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.domain.event.EventState
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.dto.event.EventUpdateRequestBody
import com.jonginout.proto.gsk.event.EventRequestProto
import com.jonginout.proto.gsk.event.EventResponseProto
import com.jonginout.proto.gsk.event.EventStateProto
import com.jonginout.proto.gsk.event.EventUpdateRequestProto

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

fun EventRequestBody.toEventRequestProto(): EventRequestProto {
    return EventRequestProto.newBuilder()
        .setName(this.name)
        .setDescription(this.description)
        .setLocation(this.location)
        .setStartAt(this.startAt.toTimestamp())
        .setEndAt(this.endAt.toTimestamp())
        .setState(EventStateProto.valueOf(this.state.name))
        .setCreatorId(this.creatorId)
        .build()
}

fun EventUpdateRequestBody.toEventUpdateRequestProto(): EventUpdateRequestProto {
    return EventUpdateRequestProto.newBuilder()
        .setId(this.id)
        .setName(this.name)
        .setName(this.name)
        .setDescription(this.description)
        .setLocation(this.location)
        .setStartAt(this.startAt.toTimestamp())
        .setEndAt(this.endAt.toTimestamp())
        .setState(EventStateProto.valueOf(this.state.name))
        .setCreatorId(this.creatorId)
        .build()
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
        .setId(this.id!!)
        .setName(this.name)
        .setDescription(this.description)
        .setLocation(this.location)
        .setStartAt(this.startAt!!.toTimestamp())
        .setEndAt(this.endAt!!.toTimestamp())
        .setState(EventStateProto.valueOf(this.state!!.name))
        .setCreatorId(this.creator!!.id!!)
        .setCreatorEmail(this.creator!!.email)
        .setCreatedAt(this.createdAt.toTimestamp())
        .setCreatedAt(this.updatedAt.toTimestamp())
        .build()
}

package com.jonginout.gsk.model.domain.gsk.extension

import com.jonginout.gsk.model.domain.gsk.domain.account.Account
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody

fun EventRequestBody.toEvent(creator: Account): Event {
    return Event.newOf(
        this.name,
        this.description,
        this.location,
        this.startAt,
        this.endAt,
        this.state,
        creator
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

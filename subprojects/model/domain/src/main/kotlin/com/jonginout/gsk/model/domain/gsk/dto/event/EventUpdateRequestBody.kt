package com.jonginout.gsk.model.domain.gsk.dto.event

import com.jonginout.gsk.model.domain.gsk.domain.event.EventState
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class EventUpdateRequestBody(
    @field:Min(0)
    var id: Long,
    @field:NotEmpty
    var name: String,
    @field:NotEmpty
    var description: String,
    @field:NotEmpty
    var location: String,
    @field:NotNull
    var startAt: LocalDateTime,
    @field:NotNull
    var endAt: LocalDateTime,
    var state: EventState,
    @field:Min(0)
    var creatorId: Long
)

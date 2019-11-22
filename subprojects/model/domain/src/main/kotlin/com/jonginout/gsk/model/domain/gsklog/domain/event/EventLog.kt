package com.jonginout.gsk.model.domain.gsklog.domain.event

import com.jonginout.gsk.common.jpa.base.BaseEntity
import com.jonginout.gsk.model.domain.gsk.domain.event.EventState
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "event")
class EventLog(
    val eventId: Long?,
    val name: String?,
    val description: String?,
    val location: String?,
    val startAt: LocalDateTime?,
    val endAt: LocalDateTime?,
    val state: EventState,
    val creatorId: Long?
) : BaseEntity()

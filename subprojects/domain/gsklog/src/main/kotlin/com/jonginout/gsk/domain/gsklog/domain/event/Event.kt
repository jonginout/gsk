package com.jonginout.gsk.domain.gsklog.domain.event

import com.jonginout.gsk.common.jpa.base.BaseEntity
import com.jonginout.gsk.domain.gsklog.domain.account.Account
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
class Event(
    val name: String?,
    val description: String?,
    val location: String?,
    val startAt: LocalDateTime?,
    val endAt: LocalDateTime?,
    val state: EventState,
    val creator: Account?
) : BaseEntity()

package com.jonginout.gsk.domain.gsk.domain.event

import com.jonginout.gsk.common.jpa.base.BaseEntity
import com.jonginout.gsk.domain.gsk.domain.account.Account
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.ManyToOne

@Entity
class Event : BaseEntity() {
    var name: String? = null
        private set
    var description: String? = null
        private set
    var location: String? = null
        private set
    var startAt: LocalDateTime? = null
        private set
    var endAt: LocalDateTime? = null
        private set
    @Enumerated(EnumType.STRING)
    var state: EventState? = EventState.ACTIVE
        private set
    @ManyToOne
    var creator: Account? = null

    fun update(
        name: String?,
        description: String?,
        location: String?,
        startAt: LocalDateTime?,
        endAt: LocalDateTime?,
        state: EventState?,
        creator: Account?
    ) {
        this.name = name
        this.description = description
        this.location = location
        this.startAt = startAt
        this.endAt = endAt
        this.state = state
        this.creator = creator
    }

    override fun toString(): String {
        return "Event(name=$name, description=$description, location=$location, startAt=$startAt, endAt=$endAt, state=$state, creator=$creator)"
    }

    companion object {
        fun empty() = Event()

        fun newOf(
            name: String?,
            description: String?,
            location: String?,
            startAt: LocalDateTime?,
            endAt: LocalDateTime?,
            state: EventState?,
            creator: Account?
        ): Event {
            return Event().apply {
                this.name = name
                this.description = description
                this.location = location
                this.startAt = startAt
                this.endAt = endAt
                this.state = state
                this.creator = creator
            }
        }
    }
}

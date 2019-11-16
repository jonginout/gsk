package com.jonginout.gsk.domain.gsk.service

import com.jonginout.gsk.domain.gsk.component.EventValidator
import com.jonginout.gsk.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.domain.gsk.domain.event.Event
import com.jonginout.gsk.domain.gsk.domain.event.EventRepository
import com.jonginout.gsk.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.domain.gsk.exception.AccountNotFoundException
import com.jonginout.gsk.domain.gsk.exception.EventNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val accountRepository: AccountRepository,
    private val eventValidator: EventValidator
) {

    fun list(pageable: Pageable): Page<Event> {
        return this.eventRepository.findAll(pageable)
    }

    fun detail(id: Long): Event {
        return this.eventRepository.findById(id).orElseThrow {
            throw EventNotFoundException(id)
        }
    }

    fun update(id: Long, body: EventRequestBody): Event {
        this.eventValidator.validate(body)

        val event = this.eventRepository.findById(id).orElseThrow {
            throw EventNotFoundException(id)
        }
        event.update(
            body.name,
            body.description,
            body.location,
            body.startAt,
            body.endAt,
            body.state,
            this.accountRepository.findById(body.creatorId).orElseThrow {
                throw AccountNotFoundException()
            }
        )
        return this.eventRepository.save(event)
    }

    fun create(body: EventRequestBody): Event {
        this.eventValidator.validate(body)

        val event = Event.newOf(
            body.name,
            body.description,
            body.location,
            body.startAt,
            body.endAt,
            body.state,
            this.accountRepository.findById(body.creatorId).orElseThrow {
                throw AccountNotFoundException()
            }
        )

        return this.eventRepository.save(event)
    }
}

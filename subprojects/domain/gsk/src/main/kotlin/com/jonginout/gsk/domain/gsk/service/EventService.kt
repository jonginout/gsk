package com.jonginout.gsk.domain.gsk.service

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
    private val accountRepository: AccountRepository
) {

    fun list(pageable: Pageable): Page<Event> {
        return this.eventRepository.findAll(pageable)
    }

    fun detail(id: Long): Event {
        return this.eventRepository.findById(id).orElseThrow {
            throw EventNotFoundException(id)
        }
    }

    fun create(body: EventRequestBody): Event {

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

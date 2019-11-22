package com.jonginout.gsk.model.domain.gsk.service

import com.jonginout.gsk.model.domain.core.config.ChainedTransactionManagerConfig
import com.jonginout.gsk.model.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.domain.event.EventRepository
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.exception.AccountNotFoundException
import com.jonginout.gsk.model.domain.gsk.exception.EventNotFoundException
import com.jonginout.gsk.model.domain.gsklog.domain.service.EventLogService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val accountRepository: AccountRepository,
    private val eventLogService: EventLogService
) {

    fun list(pageable: Pageable): Page<Event> {
        return this.eventRepository.findAll(pageable)
    }

    fun detail(id: Long): Event {
        return this.eventRepository.findById(id).orElseThrow {
            throw EventNotFoundException(id)
        }
    }

    @Transactional(ChainedTransactionManagerConfig.TRANSACTION_MANAGER_BEAN_NAME)
    fun update(id: Long, body: EventRequestBody): Event {
        val event = this.eventRepository.findById(id).orElseThrow {
            throw EventNotFoundException(id)
        }
        this.eventLogService.addLog(event.id!!)

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

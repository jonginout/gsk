package com.jonginout.gsk.domain.core.service

import com.jonginout.gsk.domain.core.config.ChainedTransactionManagerConfig
import com.jonginout.gsk.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.domain.gsk.domain.event.Event
import com.jonginout.gsk.domain.gsk.domain.event.EventRepository
import com.jonginout.gsk.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.domain.gsk.exception.AccountNotFoundException
import com.jonginout.gsk.domain.gsk.exception.EventNotFoundException
import com.jonginout.gsk.domain.gsklog.domain.service.EventLogService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(ChainedTransactionManagerConfig.TRANSACTION_MANAGER_BEAN_NAME)
class EventUpdaterService(
    private val eventRepository: EventRepository,
    private val accountRepository: AccountRepository,
    private val eventLogService: EventLogService
) {

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
}

package com.jonginout.gsk.domain.gsklog.domain.service

import com.jonginout.gsk.domain.gsk.domain.event.EventRepository
import com.jonginout.gsk.domain.gsklog.domain.event.EventLog
import com.jonginout.gsk.domain.gsklog.domain.event.EventLogRepository
import com.jonginout.gsk.domain.gsklog.domain.event.EventLogState
import com.jonginout.gsk.domain.gsklog.exception.LogFailedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EventLogService(
    private val eventRepository: EventRepository,
    private val eventLogRepository: EventLogRepository
) {

    fun addLog(eventId: Long) {
        val event = this.eventRepository.findById(eventId).orElseThrow {
            throw LogFailedException(eventId)
        }
        this.eventLogRepository.save(
            EventLog(
                event.id,
                event.name,
                event.description,
                event.location,
                event.startAt,
                event.endAt,
                EventLogState.valueOf(event.state!!.name),
                event.creator!!.id
            )
        )
    }
}

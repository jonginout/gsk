package com.jonginout.gsk.model.domain.gsklog.domain.event

import org.springframework.data.jpa.repository.JpaRepository

interface EventLogRepository : JpaRepository<EventLog, Long>

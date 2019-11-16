package com.jonginout.gsk.domain.gsklog.domain.event

import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long>

package com.jonginout.gsk.domain.gsk.domain.event

import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event, Long>

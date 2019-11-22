package com.jonginout.gsk.model.domain.gsk.exception

class EventNotFoundException(id: Long) : RuntimeException("이벤트 데이터가 존재하지 않습니다. ($id)")

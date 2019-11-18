package com.jonginout.gsk.domain.gsklog.exception

class LogFailedException(id: Long) : RuntimeException("이전 데이터가 존재하지 않아 로깅에 실패했습니다. ($id)")

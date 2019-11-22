package com.jonginout.gsk.model.domain.gsk.exception

class AccountDuplicationException(id: Long) : RuntimeException("이미 존재하는 계정입니다. ($id)")

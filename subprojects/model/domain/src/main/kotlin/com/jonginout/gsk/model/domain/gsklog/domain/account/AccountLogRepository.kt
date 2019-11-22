package com.jonginout.gsk.model.domain.gsklog.domain.account

import org.springframework.data.jpa.repository.JpaRepository

interface AccountLogRepository : JpaRepository<AccountLog, Long>

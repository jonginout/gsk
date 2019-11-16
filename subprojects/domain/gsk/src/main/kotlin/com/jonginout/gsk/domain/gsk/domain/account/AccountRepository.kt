package com.jonginout.gsk.domain.gsk.domain.account

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {
    fun findByEmail(email: String): Optional<Account>
}

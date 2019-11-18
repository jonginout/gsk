package com.jonginout.gsk.domain.gsklog.domain.service

import com.jonginout.gsk.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.domain.gsklog.domain.account.AccountLog
import com.jonginout.gsk.domain.gsklog.domain.account.AccountLogRepository
import com.jonginout.gsk.domain.gsklog.exception.LogFailedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountLogService(
    private val accountRepository: AccountRepository,
    private val accountLogRepository: AccountLogRepository
) {

    fun addLog(accountId: Long) {
        val account = this.accountRepository.findById(accountId).orElseThrow {
            throw LogFailedException(accountId)
        }
        this.accountLogRepository.save(
            AccountLog(
                account.id,
                account.email,
                account.password
            )
        )
    }
}

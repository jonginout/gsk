package com.jonginout.gsk.model.domain.gsklog.domain.service

import com.jonginout.gsk.model.domain.gsklog.exception.LogFailedException
import com.jonginout.gsk.model.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.model.domain.gsklog.domain.account.AccountLog
import com.jonginout.gsk.model.domain.gsklog.domain.account.AccountLogRepository
import org.springframework.stereotype.Service

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

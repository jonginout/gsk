package com.jonginout.gsk.domain.gsk.service

import com.jonginout.gsk.domain.gsk.domain.account.Account
import com.jonginout.gsk.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.domain.gsk.dto.account.AccountRequestBody
import com.jonginout.gsk.domain.gsk.exception.AccountDuplicationException
import com.jonginout.gsk.domain.gsk.exception.AccountNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
) {

    fun loadUserByUsername(username: String): Account {
        return this.accountRepository.findByEmail(username).orElseThrow {
            throw AccountNotFoundException()
        }
    }

    fun create(body: AccountRequestBody): Account {
        var account = this.accountRepository.findByEmail(body.email)

        if (!account.isEmpty) {
            throw AccountDuplicationException(account.get().id!!)
        }

        val newAccount = Account.newOf(
            body.email,
            body.password
        )

        return this.accountRepository.save(newAccount)
    }
}

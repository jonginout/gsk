package com.jonginout.gsk.model.domain.gsklog.domain.account

import com.jonginout.gsk.common.jpa.base.BaseEntity
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "account")
class AccountLog(
    val accountId: Long?,
    val email: String?,
    val password: String?
) : BaseEntity()

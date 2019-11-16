package com.jonginout.gsk.domain.gsklog.domain.account

import com.jonginout.gsk.common.jpa.base.BaseEntity
import javax.persistence.Entity

@Entity
class Account(
    val email: String?,
    val password: String?
) : BaseEntity()

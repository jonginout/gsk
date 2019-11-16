package com.jonginout.gsk.domain.gsk.domain.account

import com.jonginout.gsk.common.jpa.base.BaseEntity
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class Account : BaseEntity() {

    @Column(unique = true)
    var email: String? = null
        private set
    var password: String? = null
        private set

    override fun toString(): String {
        return "Account(email=$email, password=$password)"
    }

    fun update(
        email: String?,
        password: String?
    ) {
        this.email = email
        this.password = password
    }

    companion object {
        fun empty() = Account()

        fun newOf(
            email: String?,
            password: String?
        ): Account {
            return Account().apply {
                this.email = email
                this.password = password
            }
        }
    }
}

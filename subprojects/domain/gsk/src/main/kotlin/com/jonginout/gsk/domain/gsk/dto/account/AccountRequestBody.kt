package com.jonginout.gsk.domain.gsk.dto.account

import javax.validation.constraints.NotEmpty

class AccountRequestBody(
    @field:NotEmpty
    var email: String,
    @field:NotEmpty
    var password: String
)

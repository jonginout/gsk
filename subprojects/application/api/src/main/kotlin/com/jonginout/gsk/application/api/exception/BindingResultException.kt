package com.jonginout.gsk.application.api.exception

import com.jonginout.gsk.domain.gsk.exception.BadRequestException
import org.springframework.validation.BindingResult

class BindingResultException(result: BindingResult) :
    BadRequestException("${result.fieldError?.field} : ${result.fieldError?.defaultMessage}")

package com.jonginout.gsk.application.api.exception

import org.springframework.validation.BindingResult

class BindingResultException(result: BindingResult) :
    BadRequestException("${result.fieldError?.field} : ${result.fieldError?.defaultMessage}")

package com.jonginout.gsk.common.protocol.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun String.toLocalDateTime(): LocalDateTime {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this, formatter)
}

fun LocalDateTime.toTimestamp(): String {
    return this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

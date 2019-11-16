package com.jonginout.gsk.application.grpcserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.jonginout.gsk"])
class GRpcServerApplication

fun main(args: Array<String>) {
    runApplication<GRpcServerApplication>(*args)
}

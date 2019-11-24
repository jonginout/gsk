package com.jonginout.gsk.application.api.config

import com.jonginout.proto.gsk.event.EventGrpc
import com.jonginout.proto.gsk.event.EventGrpc.EventBlockingStub
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RpcClientConfig {

    @Bean
    fun rpcChannel(): ManagedChannel {
        return ManagedChannelBuilder
            .forAddress("localhost", 8000)
            .usePlaintext()
            .build()
    }

    @Bean
    fun eventStub(@Qualifier("rpcChannel") rpcChannel: ManagedChannel): EventBlockingStub {
        return EventGrpc.newBlockingStub(rpcChannel)
    }
}

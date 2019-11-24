package com.jonginout.gsk.application.grpcserver.grpcservice

import com.jonginout.gsk.application.grpcserver.base.BaseTest
import com.jonginout.gsk.common.protocol.extension.event.toEventRequestProto
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.proto.gsk.event.EventGrpc
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import org.junit.Test
import org.springframework.beans.factory.annotation.Value

class EventGrpcServiceTest : BaseTest() {

    @Value("\${grpc.port}")
    private var grpcPort: Int = 15959

    @Test
    fun `event 만들기 성공`() {
        val data = this.generateBasicData()
        val event = data["event"] as Event

        val request = event.toEventRequestProto()

        val response = EventGrpc.newBlockingStub(this.makeChannel())

        print(response)
    }

    private fun makeChannel(): ManagedChannel {
        return ManagedChannelBuilder
            .forAddress("localhost", this.grpcPort)
            .usePlaintext()
            .build()
    }
}

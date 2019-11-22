package com.jonginout.gsk.application.grpcserver.grpcservice

import com.jonginout.gsk.application.grpcserver.extension.toEvent
import com.jonginout.gsk.application.grpcserver.extension.toEventRequestBody
import com.jonginout.gsk.application.grpcserver.extension.toEventResponseProto
import com.jonginout.gsk.model.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.model.domain.gsk.service.EventService
import com.jonginout.proto.gsk.event.EventGrpc
import com.jonginout.proto.gsk.event.EventRequestProto
import com.jonginout.proto.gsk.event.EventResponseProto
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import javax.security.auth.login.AccountNotFoundException

@GRpcService
class EventGrpcService(
    private val eventService: EventService,
    private val accountRepository: AccountRepository
) : EventGrpc.EventImplBase() {

    override fun makeEvent(request: EventRequestProto, responseObserver: StreamObserver<EventResponseProto>) {
        val creator = this.accountRepository.findById(request.creatorId).orElseThrow {
            throw AccountNotFoundException()
        }
        val event = request.toEvent(creator)
        val result = this.eventService.create(event.toEventRequestBody())

        responseObserver.onNext(result.toEventResponseProto())
        responseObserver.onCompleted()
    }
}

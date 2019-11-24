package com.jonginout.gsk.application.grpcserver.grpcservice

import com.jonginout.gsk.common.protocol.extension.event.toEvent
import com.jonginout.gsk.common.protocol.extension.event.toEventResponseProto
import com.jonginout.gsk.common.protocol.extension.toLocalDateTime
import com.jonginout.gsk.model.domain.gsk.domain.account.Account
import com.jonginout.gsk.model.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.model.domain.gsk.domain.event.EventState
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.exception.AccountNotFoundException
import com.jonginout.gsk.model.domain.gsk.extension.toEventRequestBody
import com.jonginout.gsk.model.domain.gsk.service.EventService
import com.jonginout.proto.gsk.event.EventDetailRequestProto
import com.jonginout.proto.gsk.event.EventGrpc
import com.jonginout.proto.gsk.event.EventRequestProto
import com.jonginout.proto.gsk.event.EventResponseProto
import com.jonginout.proto.gsk.event.EventUpdateRequestProto
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService

@GRpcService
class EventGrpcService(
    private val eventService: EventService,
    private val accountRepository: AccountRepository
) : EventGrpc.EventImplBase() {

    override fun createEvent(request: EventRequestProto, responseObserver: StreamObserver<EventResponseProto>) {
        val event = request.toEvent(this.creator(request.creatorId))
        val result = this.eventService.create(event.toEventRequestBody())

        responseObserver.onNext(result.toEventResponseProto())
        responseObserver.onCompleted()
    }

    override fun updateEvent(request: EventUpdateRequestProto, responseObserver: StreamObserver<EventResponseProto>) {
        val eventBody = EventRequestBody(
            request.name,
            request.description,
            request.location,
            request.startAt.toLocalDateTime(),
            request.endAt.toLocalDateTime(),
            EventState.valueOf(request.state.name),
            request.creatorId
        )
        val result = this.eventService.update(request.id, eventBody)

        responseObserver.onNext(result.toEventResponseProto())
        responseObserver.onCompleted()
    }

    override fun detailEvent(request: EventDetailRequestProto, responseObserver: StreamObserver<EventResponseProto>) {
        val result = this.eventService.detail(request.id)

        responseObserver.onNext(result.toEventResponseProto())
        responseObserver.onCompleted()
    }

    private fun creator(creatorId: Long): Account {
        return this.accountRepository.findById(creatorId).orElseThrow {
            throw AccountNotFoundException()
        }
    }
}

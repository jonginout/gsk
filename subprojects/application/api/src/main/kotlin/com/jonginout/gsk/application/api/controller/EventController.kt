package com.jonginout.gsk.application.api.controller

import com.jonginout.gsk.application.api.exception.BindingResultException
import com.jonginout.gsk.application.api.service.EventStubService
import com.jonginout.gsk.model.domain.gsk.component.EventValidator
import com.jonginout.gsk.model.domain.gsk.domain.event.Event
import com.jonginout.gsk.model.domain.gsk.dto.event.EventRequestBody
import com.jonginout.gsk.model.domain.gsk.dto.event.EventUpdateRequestBody
import com.jonginout.gsk.model.domain.gsk.service.EventService
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/event")
class EventController(
    private val eventService: EventService,
    private val eventStubService: EventStubService
) {

    @GetMapping
    fun list(
        pageable: Pageable,
        assembler: PagedResourcesAssembler<Event>
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            assembler.toResource(this.eventService.list(pageable))
        )
    }

    @GetMapping("{id}")
    fun detail(
        @PathVariable id: Long
    ): ResponseEntity<*> {
        return ResponseEntity.ok(
            this.eventStubService.detail(id)
        )
    }

    @PostMapping
    fun create(
        @RequestBody @Valid body: EventRequestBody,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        if (bindingResult.hasErrors()) {
            throw BindingResultException(bindingResult)
        }
        val event = this.eventStubService.create(body)
        return ResponseEntity.created(
            linkTo(EventController::class.java).slash(event.id).toUri()
        ).body(event)
    }

    @PutMapping("{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody @Valid body: EventUpdateRequestBody,
        bindingResult: BindingResult
    ): ResponseEntity<*> {
        if (bindingResult.hasErrors()) {
            throw BindingResultException(bindingResult)
        }

        val evnet = this.eventStubService.update(body)
        return ResponseEntity.ok(evnet)
    }
}

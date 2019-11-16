package com.jonginout.gsk.application.api.base

import com.fasterxml.jackson.databind.ObjectMapper
import com.jonginout.gsk.domain.gsk.domain.account.Account
import com.jonginout.gsk.domain.gsk.domain.account.AccountRepository
import com.jonginout.gsk.domain.gsk.domain.event.Event
import com.jonginout.gsk.domain.gsk.domain.event.EventRepository
import com.jonginout.gsk.domain.gsk.domain.event.EventState
import com.jonginout.gsk.domain.gsk.dto.event.EventRequestBody
import org.junit.Ignore
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
@Ignore
class BaseTest {

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var accountRepository: AccountRepository

    @Autowired
    protected lateinit var eventRepository: EventRepository

    protected fun generateAccount(
        email: String,
        password: String
    ): Account {
        return this.accountRepository.save(
            Account.newOf(
                email = this.makeValue(email),
                password = password
            )
        )
    }

    protected fun generateEvent(
        name: String,
        description: String,
        location: String,
        state: EventState,
        creator: Account
    ): Event {
        val datetime = this.makeDatetime()
        return this.eventRepository.save(
            Event.newOf(
                name = this.makeValue(name),
                description = this.makeValue(description),
                location = this.makeValue(location),
                startAt = datetime.minusDays(4),
                endAt = datetime,
                state = state,
                creator = creator
            )
        )
    }

    protected fun generateEventRequestBody(): EventRequestBody {
        return EventRequestBody(
            name = "test_event",
            description = "test_event_description",
            location = "seoul",
            startAt = LocalDateTime.now(),
            endAt = LocalDateTime.now().plusDays(4),
            state = EventState.ACTIVE,
            creatorId = this.generateAccount("test@test.com", "1111").id!!
        )
    }

    protected fun generateBasicData(count: Int? = null): Map<String, Any> {
        if (count == null) {
            val newAccount = this.generateAccount("test@test.com", "1111")
            val newEvent = this.generateEvent(
                "test_event",
                "test_description",
                "test_location",
                EventState.ACTIVE,
                newAccount
            )
            return mapOf(
                "account" to newAccount,
                "event" to newEvent
            )
        }
        val newAccounts = mutableListOf<Account>()
        val newEvents = mutableListOf<Event>()

        repeat(count) {
            val newAccount = this.generateAccount("test@test.com", "1111")
            newAccounts.add(newAccount)
            newEvents.add(this.generateEvent(
                "test_event",
                "test_description",
                "test_location",
                EventState.ACTIVE,
                newAccount
            ))
        }

        return mapOf(
            "accounts" to newAccounts,
            "events" to newEvents
        )
    }

    private fun makeDatetime(): LocalDateTime {
        val month = (Math.random() * 12).toLong()
        val day = (Math.random() * 30).toLong()
        val hour = (Math.random() * 60).toLong()
        val minute = (Math.random() * 60).toLong()
        return LocalDateTime.now().minusMonths(month).minusDays(day).minusHours(hour).minusMinutes(minute)
    }

    protected fun makeValue(value: String, index: Int? = null, dup: Int? = null): String {
        if (index == null) {
            val max = 300
            var result = (Math.random() * max).toInt()
            if (dup != null) {
                while (true) {
                    if (result == dup) {
                        result = (Math.random() * max).toInt()
                        continue
                    }
                    break
                }
            }
            return "${result}_${value}_${result * (Math.random() * 3)}"
        }
        return "${index}_${value}_${index * (Math.random() * 3)}"
    }
}

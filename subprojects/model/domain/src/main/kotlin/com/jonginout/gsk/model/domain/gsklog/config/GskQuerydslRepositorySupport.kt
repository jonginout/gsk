package com.jonginout.gsk.model.domain.gsklog.config

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import kotlin.reflect.KClass

class GskQuerydslRepositorySupport(entity: KClass<*>) : QuerydslRepositorySupport(entity.java) {
    @PersistenceContext(unitName = "gsk-log")
    override fun setEntityManager(entityManager: EntityManager) {
        super.setEntityManager(entityManager)
    }
}

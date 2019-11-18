package com.jonginout.gsk.domain.core.config

import com.jonginout.gsk.domain.gsk.config.GskJpaConfig
import com.jonginout.gsk.domain.gsklog.config.GskLogJpaConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.data.transaction.ChainedTransactionManager
import org.springframework.transaction.PlatformTransactionManager

open class ChainedTransactionManagerConfig {

    companion object {
        const val TRANSACTION_MANAGER_BEAN_NAME = "chainedTransactionManager"
    }

    /**
     * 런타임에러 트랜잭션 롤백을 위한
     */
    @Bean
    open fun chainedTransactionManager(
        @Qualifier(GskJpaConfig.TRANSACTION_MANAGER_BEAN_NAME) gskTransactionManager: PlatformTransactionManager,
        @Qualifier(GskLogJpaConfig.TRANSACTION_MANAGER_BEAN_NAME) gskLogTransactionManager: PlatformTransactionManager
    ): ChainedTransactionManager {
        return ChainedTransactionManager(
            gskTransactionManager,
            gskLogTransactionManager
        )
    }
}

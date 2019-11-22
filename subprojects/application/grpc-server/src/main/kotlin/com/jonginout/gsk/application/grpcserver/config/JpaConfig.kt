package com.jonginout.gsk.application.grpcserver.config

import com.jonginout.gsk.model.domain.core.config.ChainedTransactionManagerConfig
import com.jonginout.gsk.model.domain.gsk.config.GskJpaConfig
import com.jonginout.gsk.model.domain.gsklog.config.GskLogJpaConfig
import org.springframework.context.annotation.Configuration

@Configuration
class GskJpaConfig : GskJpaConfig()

@Configuration
class GskLogJpaConfig : GskLogJpaConfig()

@Configuration
class ChainedTransactionManagerConfig : ChainedTransactionManagerConfig()

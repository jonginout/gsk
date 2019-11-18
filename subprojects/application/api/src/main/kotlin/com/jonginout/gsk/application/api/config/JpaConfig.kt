package com.jonginout.gsk.application.api.config

import com.jonginout.gsk.domain.core.config.ChainedTransactionManagerConfig
import com.jonginout.gsk.domain.gsk.config.GskJpaConfig
import com.jonginout.gsk.domain.gsklog.config.GskLogJpaConfig
import org.springframework.context.annotation.Configuration

@Configuration
class GskJpaConfig : GskJpaConfig()

@Configuration
class GskLogJpaConfig : GskLogJpaConfig()

@Configuration
class ChainedTransactionManagerConfig : ChainedTransactionManagerConfig()

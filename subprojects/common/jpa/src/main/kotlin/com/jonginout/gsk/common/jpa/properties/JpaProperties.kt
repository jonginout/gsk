package com.jonginout.gsk.common.jpa.properties

open class JpaProperties {

    lateinit var dialect: String
    lateinit var entityPackage: String
    lateinit var persistenceUnit: String
    lateinit var ddlAuto: DdlAuto

    enum class DdlAuto(val value: String) {
        CREATE("create"),
        CREATE_DROP("create-drop"),
        UPDATE("update"),
        VALIDATE("validate"),
        NONE("none")
    }
}

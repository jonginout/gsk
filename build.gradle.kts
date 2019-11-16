plugins {
    val kotlinVersion = "1.3.41"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
    kotlin("plugin.noarg") version kotlinVersion apply false
    kotlin("plugin.allopen") version kotlinVersion apply false

    id("org.springframework.boot") version "2.1.6.RELEASE" apply false
}

allprojects {
    repositories {
        mavenCentral()
    }
}

val querydslVersion by extra("4.2.1")
val grpcStarterVersion by extra("3.3.0")
val protobufVersion by extra("3.7.1")
val grpcVersion by extra("1.21.0")
val ktlintVersion by extra("0.33.0")

subprojects {
    if (project.childProjects.isNotEmpty()) {
        return@subprojects
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("kotlin-spring")
        plugin("kotlin-jpa")
        plugin("kotlin-noarg")
        plugin("kotlin-allopen")
        plugin("idea")
    }

    group = "com.jonginout.gsk"
    version = "1.0"

    val ktLintDependency by configurations.creating

    dependencies {
        compileOnly("org.springframework.boot:spring-boot-configuration-processor")
        kapt("org.springframework.boot:spring-boot-configuration-processor")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.springframework.boot:spring-boot-starter-hateoas")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        ktLintDependency("com.pinterest:ktlint:$ktlintVersion")
    }

    tasks {
        val ktLint by registering(JavaExec::class) {
            classpath = ktLintDependency
            main = "com.pinterest.ktlint.Main"
            args = listOf("src/**/*.kt")
            description = "Check Kotlin code style."
        }

        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }

            dependsOn(ktLint)
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }
}

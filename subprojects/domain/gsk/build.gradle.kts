val querydslVersion: String by rootProject.extra

dependencies {
    api(project(":common:jpa"))
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
    runtimeOnly("mysql:mysql-connector-java")
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}

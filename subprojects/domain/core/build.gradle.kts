val querydslVersion: String by rootProject.extra

dependencies {
    api(project(":domain:gsk"))
    api(project(":domain:gsklog"))
}

tasks {
    bootJar {
        enabled = false
    }

    jar {
        enabled = true
    }
}

val querydslVersion: String by rootProject.extra

dependencies {
//    implementation(project(":util:extension"))
//    implementation(project(":util:crypto"))
    api(project(":common:jpa"))
    api(project(":application:api"))
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

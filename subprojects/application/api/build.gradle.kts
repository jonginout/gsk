dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":domain:gsk"))
    testRuntimeOnly("com.h2database:h2")
}

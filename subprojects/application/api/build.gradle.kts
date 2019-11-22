dependencies {
    implementation(project(":model:domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testRuntimeOnly("com.h2database:h2")
}

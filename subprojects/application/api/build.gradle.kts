val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":common:protocol"))
    implementation(project(":model:domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
    testRuntimeOnly("com.h2database:h2")
}

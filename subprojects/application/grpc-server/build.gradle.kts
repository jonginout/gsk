val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":common:protocol"))
    implementation(project(":model:domain"))
    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
    testRuntimeOnly("com.h2database:h2")
}

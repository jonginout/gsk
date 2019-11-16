val grpcStarterVersion: String by rootProject.extra

dependencies {
    implementation(project(":common:protocol"))
    implementation(project(":domain:gsk"))

    implementation("io.github.lognet:grpc-spring-boot-starter:$grpcStarterVersion")
    testRuntimeOnly("com.h2database:h2")
}

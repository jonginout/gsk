rootProject.name = "gsk"

include(
    "application:api",
    "application:grpc-server",
    "domain:core",
    "domain:gsk",
    "domain:gsklog",
    "common:jpa",
    "common:protocol"
)

rootProject.children.forEach { group ->
    println("project group : ${group.name}")
    group.projectDir = file("subprojects/${group.name}")

    group.children.forEach { project ->
        println("${group.name} -> ${project.name}")
        project.projectDir = file("subprojects/${group.name}/${project.name}")
    }
}

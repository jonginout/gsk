rootProject.name = "gsk"

include(
    "application:api",
    "application:grpc-server",
    "common:jpa",
    "common:protocol",
    "model:domain"
)

rootProject.children.forEach { group ->
    println("project group : ${group.name}")
    group.projectDir = file("subprojects/${group.name}")

    group.children.forEach { project ->
        println("${group.name} -> ${project.name}")
        project.projectDir = file("subprojects/${group.name}/${project.name}")
    }
}

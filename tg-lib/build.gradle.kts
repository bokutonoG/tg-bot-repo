plugins {
    id("java-library")
}

group = "ru.water.telegram"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // --- один стартер, чтобы закрыть WebClient + Reactor Netty + Jackson ---
    compileOnly("org.springframework.boot:spring-boot-starter-webflux:3.3.2")

    // --- проперти + валидация пропертей (аннотации @ConfigurationProperties/@Validated) ---
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:3.3.2")
    compileOnly("org.springframework.boot:spring-boot-starter-validation:3.3.2")

    // --- Lombok ---
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    // --- метаданные для @ConfigurationProperties (чтобы IDE резолвила YAML-ключи) ---
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.3.2")
}

java {
    withSourcesJar()
    withJavadocJar()
}
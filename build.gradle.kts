plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.5"
    java
}

group = "ru.water"
version = "0.1.0"

repositories { mavenCentral() }

dependencies {
    implementation(project(":tg-lib"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

tasks.test { useJUnitPlatform() }
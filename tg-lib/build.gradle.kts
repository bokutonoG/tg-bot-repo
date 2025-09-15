plugins {
    `java-library`
    id("io.spring.dependency-management") version "1.1.5"
}

group = "ru.water"
version = "0.1.0"

repositories { mavenCentral() }

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.3.2"))

    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    api("org.slf4j:slf4j-api")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly("ch.qos.logback:logback-classic")
    api("org.springframework:spring-webflux")          // WebClient
    implementation("io.projectreactor.netty:reactor-netty")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testImplementation("org.mockito:mockito-core:5.12.0")
}

java { toolchain { languageVersion.set(JavaLanguageVersion.of(17)) } }

tasks.test { useJUnitPlatform() }
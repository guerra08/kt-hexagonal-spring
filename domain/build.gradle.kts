import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.1.0"
    id("org.springframework.boot") version "3.0.2" apply false
    kotlin("plugin.spring") version "1.7.22"
    kotlin("jvm") version "1.7.22"
}

repositories {
    mavenCentral()
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

apply(plugin = "io.spring.dependency-management")

the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("io.github.serpro69:kotlin-faker:1.13.0")
}

tasks.test {
    useJUnitPlatform()
}

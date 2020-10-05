import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "no.kartveit"
version = "1.0.0-SNAPSHOT"

val ktorVersion = "1.4.1"


plugins {
    java
    kotlin("jvm") version "1.4.10"
}

repositories {
    mavenCentral()
}


dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-mustache:$ktorVersion")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "14"
    }
}
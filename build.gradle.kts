import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "no.kartveit"
version = "1.0.0-SNAPSHOT"

val ktorVersion = "1.6.7"
val junitJupiterVersion = "5.8.2"
val logbackVersion = "1.2.8"
val logstashEncoderVersion = "7.0.1"
val kotlinVersion = "1.6.0"


plugins {
    java
    kotlin("jvm") version "1.6.0"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")

    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("io.ktor:ktor-mustache:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion") {
        exclude(group = "org.eclipse.jetty")
    }
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "16"
    }

    withType<ShadowJar> {
        transform(ServiceFileTransformer::class.java) {
            setPath("META-INF/cxf")
            include("bus-extensions.txt")
        }
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            showStandardStreams = true
        }
    }

}

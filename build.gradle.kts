import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.github.jengelman.gradle.plugins.shadow.transformers.ServiceFileTransformer
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "no.kartveit"
version = "1.0.0-SNAPSHOT"


val jvmTargetVersion = "17"

val ktorVersion = "2.3.1"
val junitJupiterVersion = "5.9.3"
val logbackVersion = "1.4.8"
val logstashEncoderVersion = "7.3"
val kotlinVersion = "1.8.22"


plugins {
    java
    kotlin("jvm") version "1.8.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()

}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")
    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-cio-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-mustache-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("io.ktor:ktor-server-test-host-jvm:$ktorVersion")
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = jvmTargetVersion
    }

    named<KotlinCompile>("compileTestKotlin") {
        kotlinOptions.jvmTarget = jvmTargetVersion
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

group = "no.kartveit"
version = "1.0.0-SNAPSHOT"


val ktorVersion = "2.3.5"
val junitJupiterVersion = "5.10.0"
val logbackVersion = "1.4.11"
val logstashEncoderVersion = "7.4"
val kotlinVersion = "1.9.10"

val commonsCodecVersion = "1.16.0"

plugins {
    id("application")
    kotlin("jvm") version "1.9.20"
}

application {
    mainClass.set("no.kartveit.ApplicationKt")
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

    implementation("io.ktor:ktor-server-status-pages:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-mustache-jvm:$ktorVersion")
    implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-jackson:$ktorVersion")

    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("io.ktor:ktor-server-test-host-jvm:$ktorVersion") {
        exclude(group = "org.eclipse.jetty")
    }
    constraints {
        implementation("commons-codec:commons-codec:$commonsCodecVersion") {
            because("override transient from io.ktor:ktor-server-test-host-jvm")
        }
    }
}


tasks {

    test {
        useJUnitPlatform {}
        testLogging {
            events("skipped", "failed")
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }

}

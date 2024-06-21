plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.22"
    id("io.micronaut.application") version "4.3.3"
    id("io.micronaut.aot") version "4.3.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("plugin.lombok") version "1.9.23"
    kotlin("kapt") version "1.7.10"
}

version = "0.1"
group = "cz.teddy.matcher"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

kapt {
    javacOptions {
        option("--target", 17)
    }
}

val mapstructVersion = "1.5.5.Final"


dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
    implementation ("org.mapstruct:mapstruct:$mapstructVersion")
    kapt ("org.mapstruct:mapstruct-processor:$mapstructVersion")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    compileOnly("org.projectlombok:lombok:1.18.20")
    kapt("org.projectlombok:lombok:1.18.20")
    implementation("io.micronaut.cache:micronaut-cache-core")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("org.postgresql:postgresql:42.7.2")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0-M1")
    implementation("io.micronaut.openapi:micronaut-openapi")
    implementation("io.micronaut.openapi:micronaut-openapi-annotations")
    implementation("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("io.micronaut:micronaut-http-server-netty:4.3.8")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
    implementation("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("cz.teddy.matcher.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")

    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("cz.teddy.matcher.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading.set(false)
        convertYamlToJava.set(false)
        precomputeOperations.set(true)
        cacheEnvironment.set(true)
        optimizeClassLoading.set(true)
        deduceEnvironment.set(true)
        optimizeNetty.set(true)
    }
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = "cz.teddy.matcher.ApplicationKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}



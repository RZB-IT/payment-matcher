plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.22"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.22"
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.3.3"
    id("io.micronaut.aot") version "4.3.3"
    kotlin("plugin.lombok") version "1.9.23"
    id("io.freefair.lombok") version "8.1.0"
}

version = "0.1"
group = "cz.teddy.matcher"

val kotlinVersion = project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

val fabrikt: Configuration by configurations.creating

val generationDir = "${layout.projectDirectory}/generated"
val apiFile = "${layout.projectDirectory}/openApi/MatcherAPI.yaml"

sourceSets {
    main { java.srcDirs("$generationDir/src/main/kotlin") }
    test { java.srcDirs("$generationDir/src/test/kotlin") }
}




dependencies {
    implementation("org.mapstruct:mapstruct:1.4.2.Final")

    compileOnly("org.projectlombok:lombok:1.18.20")
    annotationProcessor("org.projectlombok:lombok:1.18.20")
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


    compileOnly("io.micronaut:micronaut-http-client")
    compileOnly("io.micronaut.openapi:micronaut-openapi-annotations")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("io.micronaut:micronaut-http-server-netty:4.3.8")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.yaml:snakeyaml")
    testImplementation("io.micronaut:micronaut-http-client")
}


application {
    mainClass.set("cz.teddy.matcher.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
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

tasks.named<io.micronaut.gradle.docker.MicronautDockerfile>("dockerfile") {
    baseImage("eclipse-temurin:21-jre-jammy")
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion.set("21")
}

tasks {
    val generateCode by creating(JavaExec::class) {
        inputs.files(apiFile)
        outputs.dir(generationDir)
        outputs.cacheIf { true }
        classpath(fabrikt)
        mainClass.set("com.cjbooms.fabrikt.cli.CodeGen")
        args = listOf(
            "--output-directory", generationDir,
            "--base-package", "cz.teddy.matcher.dto",
            "--api-file", apiFile,
            "--targets", "http_models",
            "--targets", "controllers",
            "--http-controller-target", "micronaut",
            "--http-client-target", "OPEN_FEIGN",
            "--validation-library", "JAKARTA_VALIDATION",

        )
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
        dependsOn(generateCode)
    }
    dependencies {
        runtimeOnly("com.cjbooms:fabrikt:+")
        fabrikt("com.cjbooms:fabrikt:+")
    }
}



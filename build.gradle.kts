import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

group = "me.oleg"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val coroutineVer = "1.4.2"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:$coroutineVer")
    implementation(kotlin("reflect"))

    testImplementation(kotlin("test-junit"))
    testImplementation("io.kotlintest:kotlintest-runner-junit4:3.4.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVer")
    testImplementation("io.mockk:mockk:1.10.5")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}
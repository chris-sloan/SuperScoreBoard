plugins {
    id("java-library")
    kotlin("plugin.serialization") version "2.0.0"
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    implementation(project(":data:network"))
    implementation(project(":domain:common:model"))
    implementation(project(":domain:feature:match"))

    implementation(libs.ktor.serialization.kotlinx.json)

    testImplementation(libs.turbine)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}

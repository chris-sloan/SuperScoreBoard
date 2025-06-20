plugins {
    id("java-library")
    kotlin("plugin.serialization") version "2.1.21"
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("com.autonomousapps.dependency-analysis")
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
    implementation(project(":domain:common:model"))
    implementation(libs.kotlinx.coroutines.android)
}

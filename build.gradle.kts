// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply(from = "./gradle/config/installPrePush.gradle.kts")

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    kotlin("plugin.serialization") version "2.1.21"
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.android.library) apply false
    id("com.autonomousapps.dependency-analysis") version "2.18.0"
    id("io.gitlab.arturbosch.detekt") version "1.23.8"
}

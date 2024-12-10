import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.kotlin.dsl.detektPlugins

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    kotlin("plugin.serialization") version "2.0.0"
    id("com.autonomousapps.dependency-analysis")
    id("io.gitlab.arturbosch.detekt") version "1.23.7"
}

android {
    namespace = "com.chrissloan.superscoreboard"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.chrissloan.superscoreboard"
        minSdk = 32
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    lint {
        baseline = file("lint-baseline.xml")
    }
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    allRules = true
    source.setFrom(files(rootDir))
    config.setFrom(
        "$rootDir/config/detekt/detekt.yml"
    )
    baseline = file("$rootDir/config/detekt/baseline.xml")
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "1.8"
    reports {
        html.required.set(true)
        xml.required.set(true)
        sarif.required.set(
            true
        )
        md.required.set(true)
        include("**/*.kt")
        include("**/*.kts")
        exclude("**/resources/**")
        exclude("**/build/**")
    }
}

tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "1.8"
}

dependencies {

    detektPlugins(libs.detekt.formatting)

    implementation(project(":data:network"))
    implementation(project(":data:feature:fixtures"))
    implementation(project(":data:feature:match"))

    implementation(project(":domain:common:model"))
    implementation(project(":domain:common:useraction"))
    implementation(project(":domain:feature:fixtures"))
    implementation(project(":domain:feature:match"))

    implementation(project(":ui:common"))
    implementation(project(":ui:theme"))
    implementation(project(":ui:mvi"))
    implementation(project(":ui:feature:fixtures"))
    implementation(project(":ui:feature:match"))

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    runtimeOnly(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.foundation.layout)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.coil.network.okhttp)

    androidTestImplementation(libs.androidx.monitor)
    androidTestImplementation(libs.junit)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    debugImplementation(libs.androidx.ui.tooling)
    debugRuntimeOnly(libs.androidx.ui.test.manifest)
}

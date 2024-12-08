import com.android.tools.r8.internal.md
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import org.gradle.internal.impldep.org.jsoup.nodes.Document.OutputSettings.Syntax.html
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.include
import org.gradle.kotlin.dsl.implementation

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
}

detekt {
    buildUponDefaultConfig = true
    allRules = true
    autoCorrect = true
    baseline = file("$rootDir/baseline.xml")
    reports {
        txt.enabled = true
        html.enabled = true
        md.enabled = true
    }
}

val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    buildUponDefaultConfig.set(true)
    ignoreFailures.set(true)
    parallel.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$rootDir/detekt.yml"))
    baseline.set(file("$rootDir/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}

dependencies {

    implementation(project(":data:network"))
    implementation(project(":data:feature:fixtures"))
    implementation(project(":data:feature:match"))

    implementation(project(":domain:common:model"))
    implementation(project(":domain:feature:fixtures"))
    implementation(project(":domain:feature:match"))

    implementation(project(":ui:common"))
    implementation(project(":ui:theme"))
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
plugins {
    kotlin("multiplatform")
//    alias(libs.plugins.kmm.example.library)
//    kotlin("jvm") version libs.versions.kotlin
    id("com.android.library")
    kotlin("plugin.serialization")
    alias(libs.plugins.google.ksp) version libs.versions.kspVersion
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "network"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.bundles.network)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinx.serialization)
                implementation(libs.bundles.di.kotlin)
//                ksp(libs.di.koin.ksp)
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.example.network"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
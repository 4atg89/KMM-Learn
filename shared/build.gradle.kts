plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
    id("com.android.library")
    id("kmm.compose")
    kotlin("plugin.serialization")
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
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "17"
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val androidMain by getting
        val iosMain by getting
        val desktopMain by getting

        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":ui:games"))
                implementation(project(":data:network"))
                implementation(libs.coroutines.core)
                implementation(libs.kamel.image)
                implementation(libs.bundles.di.kotlin)

            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.di.koin.ksp)
}

android {
    namespace = "com.example.kmmproject"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

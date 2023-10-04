import org.jetbrains.kotlin.gradle.dsl.KotlinCompile

plugins {
    kotlin("multiplatform")
//    id("com.google.devtools.ksp")
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
            baseName = "network"
        }
    }

    sourceSets {
        val commonMain by getting {
//            getByName(name) { kotlin.srcDir("build/generated/ksp/$name/kotlin") }
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
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
//                implementation(kotlin("test"))
            }
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", "io.insert-koin:koin-ksp-compiler:1.2.2")//libs.di.koin.ksp)
}

tasks.withType<org.jetbrains.kotlin.gradle.dsl.KotlinCompile<*>>().configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}
afterEvaluate {
    tasks.filter {
        it.name.contains("SourcesJar", true)
    }?.forEach {
        println("SourceJarTask====>${it.name}")
        it.dependsOn("kspCommonMainKotlinMetadata")
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
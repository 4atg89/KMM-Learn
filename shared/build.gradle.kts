plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
    id("com.android.library")
//    id("org.jetbrains.compose")
    alias(libs.plugins.jetbrains.compose)
//    alias(libs.plugins.kmm.project.compose)
    kotlin("plugin.serialization")
//    alias(libs.plugins.kmm.project.compose)
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
//    jvm()
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
        val androidMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-okhttp:2.3.4")
            }
        }

        val iosMain by getting {
            dependencies {

                implementation("io.ktor:ktor-client-darwin:2.3.4")
            }
        }

        val desktopMain by getting {
            dependencies {
//                implementation("io.ktor:ktor-client-curl:2.3.4")
                implementation("io.ktor:ktor-client-okhttp:2.3.4")
            }
        }

//        val webMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-winhttp:2.3.4")
//            }
//        }

        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":data:network"))
                //put your multiplatform dependencies here
//                implementation(libs.bundles.network)
                implementation(libs.coroutines.core)
//                implementation(libs.kotlinx.serialization)
                implementation(libs.kamel.image)
                implementation(libs.bundles.di.kotlin)

//                api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
//                api("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatfrom
                implementation(libs.bundles.precompose)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

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

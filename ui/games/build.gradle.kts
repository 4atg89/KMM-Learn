plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp")
    id("com.android.library")
    id("kmm.pre-compose")
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
            baseName = "games"
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
                implementation("io.ktor:ktor-client-okhttp:2.3.4")
            }
        }

        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":data:network"))
                implementation(libs.coroutines.core)
                implementation(libs.kamel.image)
                implementation(libs.bundles.di.kotlin)

            }
        }
        val commonTest by getting {
            dependencies {
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

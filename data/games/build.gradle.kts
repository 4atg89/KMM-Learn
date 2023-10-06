@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    alias(libs.plugins.google.ksp) version libs.versions.kspVersion
    alias(libs.plugins.kotlin.multiplatform)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.coroutines.core)
                implementation(libs.bundles.di.kotlin)
            }

        }
    }
}

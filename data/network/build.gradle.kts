@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.android.library")
    kotlin("plugin.serialization")
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.google.ksp) version libs.versions.kspVersion
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {

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
                implementation(libs.bundles.network)
                implementation(libs.coroutines.core)
                implementation(libs.kotlinx.serialization)
                implementation(libs.bundles.di.kotlin)
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
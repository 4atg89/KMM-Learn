@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("com.google.devtools.ksp")
    id("com.android.library")
    alias(libs.plugins.ui.compose)
    alias(libs.plugins.kotlin.multiplatform)
    kotlin("plugin.serialization")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    sourceSets {
        val androidMain by getting
        val iosMain by getting
        val desktopMain by getting

        val commonMain by getting {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
            dependencies {
                implementation(project(":ui:games"))
                implementation(project(":data:games"))
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
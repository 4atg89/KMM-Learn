rootProject.name = "MyApplication"


include(":androidApp")
include(":shared")
include(":data:network")
include(":data:games")

pluginManagement {
    includeBuild("build-common")
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

    }

    plugins {
        //todo move to another place
        val kotlinVersion = "1.9.10"//extra["kotlin.version"] as String
        val agpVersion = "8.1.1"//extra["agp.version"] as String
        val composeVersion = "1.5.2"//extra["compose.version"] as String

        kotlin("jvm").version(kotlinVersion)
        kotlin("multiplatform").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        kotlin("plugin.serialization").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)

        id("org.jetbrains.compose").version(composeVersion)
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.4.0")
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
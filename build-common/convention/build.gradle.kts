plugins {
    `kotlin-dsl`
}

group = "com.example.kmmproject.buildcommon"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

//
dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly("org.jetbrains.compose:org.jetbrains.compose.gradle.plugin:1.5.2")
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {

        register("kmmProjectLibrary") {
            id = "example.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }

        register("kmmCompose") {
            id = "kmm.compose"
            implementationClass = "ComposeConventionPlugin"
        }
    }
}

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
//    compileOnly(libs.jetbrains.compose)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {

//        register("sondaAndroidApplication") {
//            id = "sonda.android.application"
//            implementationClass = "AndroidApplicationConventionPlugin"
//        }
//
//        register("sondaAndroidLibrary") {
//            id = "sonda.android.library"
//            implementationClass = "AndroidLibraryConventionPlugin"
//        }
//
        register("kmmCompose") {
            id = "kmm.compose"
            implementationClass = "ComposeConventionPlugin"
        }
//
//        register("sondaKotlin") {
//            id = "sonda.kotlin"
//            implementationClass = "KotlinLibraryConventionPlugin"
//        }
    }
}

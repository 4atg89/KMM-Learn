//plugins {
//    kotlin("jvm")
//    id("org.jetbrains.compose")
//}
//
//repositories {
//    mavenCentral()
//    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
//    google()
//}
//
//dependencies {
//    // Note, if you develop a library, you should use compose.desktop.common.
//    // compose.desktop.currentOs should be used in launcher-sourceSet
//    // (in a separate module for demo project and in testMain).
//    // With compose.desktop.common you will also lose @Preview functionality
//    implementation(compose.desktop.currentOs)
//
//    // Include the Test API
//    testImplementation(compose.desktop.uiTestJUnit4)
//}
//
//compose.desktop {
//    application {
//        mainClass = "MainKt"
//
//        nativeDistributions {
//            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi, org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
//            packageName = "KotlinJvmComposeDesktopApplication"
//            packageVersion = "1.0.0"
//        }
//    }
//}

//----------
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    application
}

dependencies {
    implementation(project(":shared"))
    implementation("moe.tlaster:precompose:1.5.4")
    implementation(compose.desktop.currentOs)
    implementation(libs.bundles.di.kotlin)
}

application {
    mainClass.set("desktop")
}

compose {
    kotlinCompilerPlugin.set("1.5.2")
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.10")
}
package com.example.kmmproject

import com.android.build.gradle.BaseExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

// todo do it with another library
@Suppress("UnstableApiUsage")
internal fun Project.compose(ext: BaseExtension) {
    val libraries = extensions.getByType<VersionCatalogsExtension>().named("libs")
    ext.buildFeatures.compose = true
    ext.composeOptions { kotlinCompilerExtensionVersion = libraries.composeCompiler }
//    dependencies { addLibraries(libraries) }
}


private val VersionCatalog.composeCompiler
    get() = findVersion("androidxComposeCompiler").get().toString()
private fun DependencyHandlerScope.addLibraries(libraries: VersionCatalog) {
//    add("implementation", platformName(libraries))
//    add("implementation", compose.runtime)
//    add("implementation", compose.foundation)
//    add("implementation", compose.material)
//    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//    add("implementation", compose.components.resources)
//    implementation(compose.runtime)
//    implementation(compose.foundation)
//    implementation(compose.material)
//    implementation(compose.components.resources)
}

/**
 * Retrieves the [compose][org.jetbrains.compose.ComposePlugin.Dependencies] extension.
 */
//val org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.`compose` get() =
//    (this as org.gradle.api.plugins.ExtensionAware).extensions.getByName("compose") as Dependencies


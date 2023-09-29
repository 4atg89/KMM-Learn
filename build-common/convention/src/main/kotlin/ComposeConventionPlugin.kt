import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Action
import org.gradle.api.JavaVersion
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.pluginManager.apply {
            apply(libs.findPlugin("jetbrains.compose").get().get().pluginId)
            apply(libs.findPlugin("jetbrains.kmm").get().get().pluginId)
        }
//        target.extensions.configure<BaseAppModuleExtension>(::configureAndroid)

//        val libraries = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
//        val ext = target.extensions.getByType<BaseExtension>()
//        ext.buildFeatures.compose = true
//        ext.composeOptions { kotlinCompilerExtensionVersion = libraries.composeCompiler }
        target.kotlin {
            androidTarget {
                compilations.all {
                    kotlinOptions {
                        jvmTarget = "17"
                    }
                }
            }
            sourceSets {
                commonMain {
                    target.dependencies {
                        addLibraries(compose)
//                        implementation(compose.runtime)
//                        implementation(compose.foundation)
//                        implementation(compose.material)
//                        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                        implementation(compose.components.resources)
                    }
                }
            }
        }
    }

}


@Suppress("UnstableApiUsage")
private fun configureAndroid(commonExtension: BaseAppModuleExtension) {
    commonExtension.apply {

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
            jvmTarget = "17"
        }
    }
}

internal fun BaseAppModuleExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
internal fun LibraryExtension.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
private fun SourceSets.getOrCreate(name: String): KotlinSourceSet = findByName(name) ?: create(name)

fun SourceSets.commonMain(block: KotlinSourceSet.() -> Unit = {}): KotlinSourceSet =
    getOrCreate("commonMain").apply(block)

private fun Project.`kotlin`(configure: Action<KotlinMultiplatformExtension>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("kotlin", configure)

typealias SourceSets = NamedDomainObjectContainer<KotlinSourceSet>

fun KotlinMultiplatformExtension.sourceSets(block: SourceSets.() -> Unit) {
    sourceSets.block()
}

private val VersionCatalog.composeCompiler
    get() = findVersion("androidxComposeCompiler").get().toString()
val KotlinMultiplatformExtension.compose: ComposePlugin.Dependencies
    get() = (this as ExtensionAware).extensions.getByName("compose") as ComposePlugin.Dependencies

fun DependencyHandlerScope.addLibraries(compose: ComposePlugin.Dependencies) {
    add("implementation", compose.runtime)
    add("implementation", compose.foundation)
    add("implementation", compose.material)
    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
    add("implementation", compose.components.resources)
}



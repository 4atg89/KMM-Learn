import com.android.build.api.dsl.LibraryExtension
import com.example.kmmproject.commonMain
import com.example.kmmproject.kotlin
import com.example.kmmproject.sourceSets
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class PreComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val libs = target.extensions.getByType<VersionCatalogsExtension>().named("libs")
        target.pluginManager.apply {
            apply(libs.findPlugin("jetbrains.kmm").get().get().pluginId)
        }
        val library = target.extensions.getByType<LibraryExtension>()
        library.compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
        target.kotlin {
            sourceSets {
                commonMain {
                    dependencies {
                        implementation (libs.preCompose)
                    }
                }
            }
        }

    }

}
private val VersionCatalog.preCompose
    get() = findBundle("precompose").get()
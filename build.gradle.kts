import io.github.diskria.gradle.utils.extensions.getCatalogVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.projektor)
}

dependencies {
    implementation(libs.kotlin.utils)
    implementation(libs.kotlin.serialization.json)
}

projekt {
    kotlinLibrary {
        jvmTarget = JvmTarget.JVM_21
    }
}

val kotlinVersion = getCatalogVersion("kotlin")
configurations.all {
    resolutionStrategy {
        eachDependency {
            when (requested.group) {
                "org.jetbrains.kotlin" -> useVersion(kotlinVersion)
            }
        }
    }
}

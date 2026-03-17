import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.projektor)
}

dependencies {
    compileOnly(gradleKotlinDsl())

    implementation(libs.bundles.diskria.utils)
    implementation(libs.kotlin.serialization.json)
}

projekt {
    kotlinLibrary {
        jvmTarget = JvmTarget.JVM_21
    }
}

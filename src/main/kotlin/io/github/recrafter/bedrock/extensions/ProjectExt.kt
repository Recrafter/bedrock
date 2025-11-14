package io.github.recrafter.bedrock.extensions

import io.github.diskria.gradle.utils.extensions.common.gradleError
import io.github.diskria.kotlin.utils.extensions.serialization.deserializeFromJson
import io.github.diskria.kotlin.utils.extensions.serialization.serializeToJson
import io.github.diskria.kotlin.utils.properties.autoNamedProperty
import io.github.recrafter.bedrock.recipes.ModRecipe
import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra

fun Project.setModRecipe(metadata: ModRecipe) {
    val modRecipe by metadata.serializeToJson().autoNamedProperty()
    rootProject.extra[modRecipe.name] = modRecipe.value
}

fun Project.getModRecipe(): ModRecipe {
    val modRecipe: String? by rootProject.extra.properties
    return modRecipe?.deserializeFromJson()
        ?: gradleError(
            """
            Mod recipe not found in root project.
            
            This usually means that the **Recipe** settings plugin was not applied.
            Please ensure that your `settings.gradle.kts` includes:
            
                plugins {
                    id("io.github.recrafter.recipe") version "<version>"
                }
            
            The Recipe plugin is required to generate and register mod recipes 
            before the Crafter plugin can configure module projects.
            """.trimIndent()
        )
}

package io.github.recrafter.bedrock.recipes

import io.github.recrafter.bedrock.sides.ModEnvironment
import kotlinx.serialization.Serializable

@Serializable
data class ModRecipe(
    val environment: ModEnvironment,
)

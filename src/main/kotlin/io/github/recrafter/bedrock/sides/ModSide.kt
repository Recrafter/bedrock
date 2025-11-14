package io.github.recrafter.bedrock.sides

import io.github.diskria.kotlin.utils.extensions.mappers.getName
import io.github.diskria.kotlin.utils.words.PascalCase

enum class ModSide {

    CLIENT,
    SERVER;

    fun getRunTaskName(): String =
        "run" + getName(PascalCase)
}

package io.github.recrafter.bedrock.sides

enum class ModEnvironment(val sides: List<ModSide>) {

    CLIENT_SERVER(
        listOf(ModSide.CLIENT, ModSide.SERVER)
    ),
    CLIENT(
        listOf(ModSide.CLIENT)
    ),
    DEDICATED_SERVER(
        listOf(ModSide.SERVER)
    ),
}

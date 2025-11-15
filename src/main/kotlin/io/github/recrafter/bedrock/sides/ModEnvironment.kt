package io.github.recrafter.bedrock.sides

enum class ModEnvironment(val sides: List<ModSide>) {
    CLIENT_SERVER(listOf(ModSide.CLIENT, ModSide.SERVER)),
    CLIENT_ONLY(listOf(ModSide.CLIENT)),
    SERVER_ONLY(listOf(ModSide.SERVER)),
    DEDICATED_SERVER_ONLY(listOf(ModSide.SERVER)),
}

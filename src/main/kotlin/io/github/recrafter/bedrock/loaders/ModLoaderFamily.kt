package io.github.recrafter.bedrock.loaders

enum class ModLoaderFamily(val loaders: List<ModLoaderType>) {

    FABRIC(
        listOf(
            ModLoaderType.FABRIC,
            ModLoaderType.QUILT,
            ModLoaderType.LEGACY_FABRIC,
            ModLoaderType.ORNITHE,
            ModLoaderType.BABRIC,
        )
    ),
    FORGE(
        listOf(
            ModLoaderType.FORGE,
            ModLoaderType.NEOFORGE,
        )
    );

    companion object {
        fun of(loader: ModLoaderType): ModLoaderFamily =
            ModLoaderFamily.entries.first { it.loaders.contains(loader) }
    }
}

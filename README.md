# Bedrock

The core library of the Recrafter ecosystem, providing shared logic for supported mod loaders, Minecraft versioning, and common utilities used across all Recrafter projects.

[![Maven Central](https://img.shields.io/maven-central/v/io.github.recrafter/bedrock.svg?label=Maven+Central&style=for-the-badge)](https://central.sonatype.com/artifact/io.github.recrafter/bedrock) [![License: MIT](https://img.shields.io/static/v1?label=License&style=for-the-badge&message=MIT&color=yellow)](https://spdx.org/licenses/MIT)

---

## ✨ Overview

Bedrock serves as a common core for:

- **Supported mod loaders** — Fabric, Forge, and others
- **Minecraft versioning system** — version metadata, mappings, and compatibility rules
- **Common constants, types, and utilities** shared across Recrafter modules
- **Core abstractions** for project configuration and plugin infrastructure

## 📦 Installation

```kotlin
dependencies {
    implementation("io.github.recrafter:bedrock:<version>")
}
```

---

## License

This project is licensed under the [MIT License](https://spdx.org/licenses/MIT).

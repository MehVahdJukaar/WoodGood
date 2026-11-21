pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.possible-triangle.helper") version ("1.4")
}

helper {
    versionStrategy.set(com.possible_triangle.gradle.settings.ResolutionStrategy.NONE)
}

include("common", "fabric", "neoforge")
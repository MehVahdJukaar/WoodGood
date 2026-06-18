pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/")
        maven("https://maven.neoforged.net/releases")
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.possible-triangle.helper") version ("1.4")
}

include("common", "fabric", "neoforge")
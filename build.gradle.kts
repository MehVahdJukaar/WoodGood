import org.gradle.internal.impldep.org.bouncycastle.oer.OERDefinition.optional

plugins {
    id("com.possible-triangle.core")
    id("com.possible-triangle.common") apply false
    id("com.possible-triangle.fabric") apply false
    id("com.possible-triangle.neoforge") apply false
    id("net.mehvahdjukaar.candlelight") version "1.2.1" apply false
}

mod {
    additional.add("mod_description")
    additional.add("mod_credits")
    additional.add("mod_license")
    additional.add("mod_homepage")
    additional.add("mod_authors")
    additional.add("mod_github")
    additional.add("moonlight_required_version")
}


subprojects {

    pluginManager.apply("com.possible-triangle.core")
    pluginManager.apply("net.mehvahdjukaar.candlelight")
    pluginManager.apply("maven-publish")

    dependencies {
        compileOnly("net.mehvahdjukaar:candlelight:1.2.1")
    }


    tasks.withType<GenerateModuleMetadata> {
        enabled = true
    }

    repositories {
        nexus()
    }

    upload {
        maven {
            nexus()
        }
        curseforge {
            dependencies {
                required("selene")
                optional("stonezone")
                optional("gemsrealm")
            }
        }
        modrinth {
            dependencies {
                required("moonlight")
                optional("stonezone")
                optional("gemsrealm")
            }
        }

        forEach {
            changelog = rootProject.file("changelog.md").readText()
            versionName = "${mod.id.get()}-${mod.version.get()}-${project.name}"
        }
    }

    tasks.withType<JavaCompile> {
        options.compilerArgs.addAll(listOf("-Xmaxerrs", "4000"))
    }

    repositories {
        // Standard repositories
        mavenLocal()
        mavenCentral()

        // Flat directory for local mods
        flatDir { dirs("mods") }

        // Our publishing repo
        maven("https://api.modrinth.com/maven")
        maven("https://www.cursemaven.com")
        maven("https://jitpack.io")

//        maven("https://maven.neoforged.net/releases")
        maven("https://maven.architectury.dev")
        maven("https://maven.parchmentmc.org")

        maven("https://maven.createmod.net") // Create Mod, Ponder, Flywheel
        maven("https://maven.blamejared.com") // JEI, Vazkii's Mods
        maven("https://maven.ladysnake.org/releases") // Ladysnake mods
        maven("https://maven.tterrag.com/") // Flywheel, EnderIO
        maven("https://maven.ithundxr.dev/snapshots") // Registrate
        maven("https://mvn.devos.one/releases/") // Registrate, Porting Lib (releases)
        maven("https://mvn.devos.one/snapshots/") // Registrate, Porting Lib (snapshots)
        maven("https://maven.terraformersmc.com/") // EMI
        maven("https://maven.saps.dev/releases") // FTB Mods

        maven("https://maven.theillusivec4.top/") // Curios API
        maven("https://maven.squiddev.cc") // CC: Tweaked
        maven("https://maven.su5ed.dev/releases") // SU5ED mods
        maven("https://harleyoconnor.com/maven") // Dynamic Trees
        maven("https://maven.misterpemodder.com/libs-release/") // ShulkerBoxTooltip
        maven("https://maven.firstdarkdev.xyz/snapshots") // FirstDarkDev (snapshots)
        maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven") // NeoForge-config-API-port

        maven("https://maven.shedaniel.me/") // Cloth Config
        maven("https://squiddev.cc/maven") // CC: Tweaked
        maven("https://maven.teamresourceful.com/repository/maven-public/") // Chipped
        maven("https://raw.githubusercontent.com/moddingplayground/maven/main/")
        maven("https://maven.resourcefulbees.com/repository/maven-public/") // Resourceful-Lib
        maven("https://maven.blamejared.com/") // JEI, CraftTweaker
        maven("https://modmaven.dev") // Botania & FALLBACK for JEI
        maven("https://maven.isxander.dev/releases") // Yet-Another-Config-Lib

        maven { // Reach Entity Attributes
            url = uri("https://maven.jamieswhiteshirt.com/libs-release")
            content { includeGroup(("com.jamieswhiteshirt")) }
        }
    }
}

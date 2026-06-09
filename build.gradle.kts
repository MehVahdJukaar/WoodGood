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
        var modLoader = name
        curseforge {
            dependencies {
                required("selene")
                optional("stone-zone")
                optional("gems-realm")
                optional("sawmill")

                optional("macaws-bridges")
                optional("macaws-doors")
                optional("macaws-fences-and-walls")
                optional("macaws-furniture")
                optional("macaws-lights-and-lamps")
                optional("macaws-paths-and-pavings")
                optional("macaws-roofs")
                optional("macaws-trapdoors")
                optional("macaws-windows")

                if (modLoader == "fabric") {
                    optional("another-furniture")
                    optional("architects-palette-fabric")
                    optional("backpacked")
                    optional("guitas-woodworks")
                    optional("building-but-better")
//                    optional("camp-chair")
                    optional("chipped")
                    optional("clutter")
                    optional("create-fabric")
                    optional("decorative-blocks")
                    optional("dramatic-doors")
                    optional("exlines-awnings")
//                    optional("exlines-bark-carpets")
                    optional("excessive-building")
                    optional("farmers-delight-fabric")
                    optional("farmers-delight-refabricated")
                    optional("friends-and-foes")
                    optional("furnish")
                    optional("handcrafted")
//                    optional("hearth-and-home")
                    optional("infinitybuttons")
                    optional("lightmans-currency-fabric")
                    optional("mighty-mail-fabric")
                    optional("missing-wilds")
                    optional("more-beautiful-torches")
//                    optional("more-crafting-tables-lieonlion")
                    optional("quark")
                    optional("refurbished-furniture")
                    optional("regions-unexplored")
//                    optional("the-twilight-forest") //NOT AVAILABLE
//                    optional("table-top-craft-fabric")
                    optional("twigs")
                    optional("valhelsia-furniture")
                    optional("variant-vanilla-blocks")
                    optional("villagersplus-fabric")
                    optional("wooden-hoppers")
                } else { // NEOFORGE

                    optional("absent-by-design")
                    optional("another-furniture")
                    optional("architects-palette")
                    optional("backpacked")
                    optional("bibliocraft-legacy")
                    optional("boatload")
                    optional("guitas-woodworks")
                    optional("buildersaddition")
                    optional("building-but-better")
                    optional("camp-chair")
                    optional("chipped")
                    optional("corail-pillar")
                    optional("create")
                    optional("dawn-of-time")
                    optional("decorative-blocks")
                    optional("decoration-delight")
                    optional("domum-ornamentum")
                    optional("dramatic-doors")
                    optional("exlines-bark-carpets")
                    optional("excessive-building")
                    optional("farmers-delight")
                    optional("friends-and-foes-forge")
                    optional("functional-storage")
                    optional("furnish")
                    optional("handcrafted")
//                    optional("hearth-and-home")
                    optional("infinitybuttons")
                    optional("just-a-raft-mod")
                    optional("lightmans-currency")
                    optional("mighty-mail")
                    optional("missing-wilds-forge")
                    optional("more-beautiful-torches")
                    optional("more-chest-variants-lieonlion")
                    optional("more-crafting-tables-for-forge")
//                    optional("more-crafting-tables-lieonlion")
                    optional("mosaic-carpentry")
                    optional("pokecube-aoi")
                    optional("premium-wood")
//                        optional("productivebees")
                    optional("quark")
                    optional("redeco")
                    optional("refurbished-furniture")
                    optional("regions-unexplored")
                    optional("storage-drawers")
                    optional("timber-frames")
                    optional("the-graveyard-forge")
                    optional("the-twilight-forest")
//                    optional("table-top-craft")
                    optional("tropicraft")
                    optional("twigs")
                    optional("valhelsia-furniture")
                    optional("variant-crafting-tables")
                    optional("variant-vanilla-blocks")
                    optional("villagersplus-forge")
                    optional("woodworks")
//                    optional("workshop-for-handsome-adventurer")
                    optional("xercamod")
                }
            }
        }
        modrinth {
            dependencies {
                required("moonlight")
                optional("stone-zone")
                optional("gems-realm")
                optional("sawmill")

                optional("macaws-bridges")
                optional("macaws-doors")
                optional("macaws-fences-and-walls")
                optional("macaws-furniture")
                optional("macaws-lights-and-lamps")
                optional("macaws-paths-and-pavings")
                optional("macaws-roofs")
                optional("macaws-trapdoors")
                optional("macaws-windows")

                optional("chipped")
                optional("corail-pillar")
                optional("dramatic-doors")

                if (modLoader == "fabric") {

                    required("fabric-api")
                    optional("another-furniture")
                    optional("architects-palette-fabric")
                    optional("backpacked")
                    optional("guitas-woodworks")
                    optional("building-but-better")
//                    optional("camp-chair")
                    optional("clutter")
                    optional("create-fabric")
                    optional("dawn-of-time")
                    optional("decorative-blocks")
                    optional("exlines-awnings")
//                    optional("bark-carpets")
                    optional("excessive-building")
                    optional("farmers-delight-fabric")
                    optional("farmers-delight-refabricated")
                    optional("friends-and-foes")
                    optional("furnish")
                    optional("handcrafted")
//                    optional("hearth-and-home")
                    optional("infinitybuttons")
                    optional("lightmans-currency")
                    optional("mighty-mail-fabric")
                    optional("missing-wilds")
                    optional("more-beautiful-torches")
//                    optional("more-crafting-tables-lieonlion")
                    optional("quark")
                    optional("regions-unexplored")
//                    optional("the-twilight-forest") //NOT AVAILABLE
//                    optional("table-top-craft")
                    optional("twigs")
                    optional("valhelsia-furniture")
                    optional("variant-vanilla-blocks")
                    optional("villagersplus") // FABRIC
                    optional("wooden-hoppers") // FABRIC
                } else { //!! NEOFORGE

                    optional("absent-by-design")
                    optional("another-furniture")
                    optional("architects-palette")
                    optional("bibliocraft-legacy")
                    optional("boatload")
                    optional("guitas-woodworks")
                    optional("buildersaddition")
                    optional("building-but-better")
//                    optional("camp-chair")
                    optional("create")
                    optional("decorative-blocks")
                    optional("decoration-delight")
//                    optional("domum-ornamentum")
                    optional("dramatic-doors")
                    optional("bark-carpets")
                    optional("excessive-building")
                    optional("farmers-delight")
                    optional("friends-and-foes-forge")
                    optional("functional-storage")
                    optional("furnish")
                    optional("handcrafted")
//                    optional("hearth-and-home")
                    optional("infinitybuttons")
                    optional("just-a-raft-mod")
                    optional("lightmans-currency")
                    optional("mighty-mail")
                    optional("missing-wilds")
                    optional("more-beautiful-torches")
                    optional("more-chest-variants-lieonlion")
                    optional("more-crafting-tables-for-forge")
//                    optional("more-crafting-tables-lieonlion")
                    optional("mosaic-carpentry")
                    optional("pokecube-aoi")
                    optional("premium-wood")
//                    optional("productivebees")
                    optional("quark")
                    optional("redeco")
                    optional("regions-unexplored")
                    optional("storage-drawers")
                    optional("timber-frames")
                    optional("the-graveyard-forge")
                    optional("the-twilight-forest")
                    optional("table-top-craft")
                    optional("tropicraft")
                    optional("twigs")
                    optional("valhelsia-furniture")
                    optional("variant-crafting-tables")
                    optional("variant-vanilla-blocks")
                    optional("villagersplus")
                    optional("woodworks")
//                    optional("workshop-for-handsome-adventurer")
                    optional("xercamod")
                }
            }
        }
        forEach {
            changelog = rootProject.file("changelog.md").readText()
            versionName = "${mod.id.get()}-${mod.version.get()}-${project.name}"
        }
//        maven {
//            nexus()
//        }
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

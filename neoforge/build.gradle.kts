plugins {
    id("com.possible-triangle.neoforge")
}

neoforge {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

//val path = System.getenv("REPOS21_1").toString()
dependencies {

//    neoForge("net.neoforged:neoforge:${property("neoforge_version")}")

//    common(project(path: ':common', configuration: 'namedElements')) { transitive false }
//    shadowCommon(project(path: ':common', configuration: 'transformProductionNeoForge'))

//    implementation("org.jetbrains:annotations:22.0.0")

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (property("enable_moonlight_test").toString().toBoolean()) {
      //  modApi(files(path + "\\Moonlight\\neoforge\\build\\libs\\moonlight-${property("moonlight_testVersion")}-neoforge.jar"))
    }
    //+ MAVEN
    else {
        if (property("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-neoforge")
        else {
            modImplementation("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
            accessTransformers("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
        }
    }


//!! TOOLS ========================================================================================================== \\
    modRuntimeOnly("dev.emi:emi-neoforge:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-neoforge-${property("minecraft_version")}:${property("crafttweaker_version")}")
    modRuntimeOnly("curse.maven:jei-238222:7420587")
    modRuntimeOnly("curse.maven:jade-324717:7545219")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452")

    //+ REQUIRED
    modImplementation("curse.maven:configured-457570:7276577")

//!! ================================================ DEPENDENCIES ================================================== \\
    //@ IMPORTANT: RLM - "REQUIRED LOCAL MOD" - You need to get the mod and put it in ~/forge/run/mods/....
    //@ IMPORTANT: DNU - "DO NOT USE" the modRunTimeOnly because it can cause issues in production

    //- Only For TESTING - can be commented out or enabled
    modRuntimeOnly("maven.modrinth:terrablender:4.1.0.8") // BOP, Regions-Unexplored
    modRuntimeOnly("maven.modrinth:glitchcore:2.1.0.2") // BOP
//    modRuntimeOnly("curse.maven:athena-841890:5629395") // v4.0.1 | Chipped
//    modRuntimeOnly("curse.maven:architectury-api-419699:5786327") // v13.0.8 | REI, Furnish, [Let's Do]-Meadow
//    modRuntimeOnly("curse.maven:cloth-config-348521:5729127") // v15.0.140 | REI
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:7471474") // v1.2.12 | Dawn of Time, Timber-Frame, Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:5546996") // v1.1.8 | Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-core-lib-454372:7521894") // v1.1.20 | Rechiseled
//    modRuntimeOnly("maven.modrinth:midnightlib:YeePowOJ") // v1.6.3 | Building-But-Better
    modRuntimeOnly("maven.modrinth:patchouli:1.21.1-93-neoforge") // TFC, Timber-Frames
//    modRuntimeOnly("curse.maven:flib-661261:5495793") // Absent-By-Design //!! 1.20.1
//    modRuntimeOnly("curse.maven:curios-309927:5680164") // Malum //!! 1.20.1
//    modRuntimeOnly("curse.maven:lodestone-616457:5712854") // Malum //!! 1.20.1
//    modRuntimeOnly("curse.maven:citadel-331936:5143956") // Alex's Cave //!! 1.20.1
//    modRuntimeOnly("curse.maven:structure-gel-api-378802:5188368") // Blue-Skies //!! 1.20.1
//    modRuntimeOnly("curse.maven:monolib-968432:6123990") //v2.0.0 | More-Beautiful-Torches
//    modRuntimeOnly("curse.maven:quad-932715:5900860") // v1.2.9 | More-Chest-Variants, More-Crafting-Table

    //- ~/neoforge/mods LOCAL
//    modRuntimeOnly("quark-biolith:biolith-neoforge-3.0.10") // Quark

    //- OTHER MAVENs
//    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2") // Chipped, Resourceful-Lib
//    modRuntimeOnly("dev.engine-room.flywheel:flywheel-neoforge-${minecraft_version}:${flywheel_version}") // Create
//    modRuntimeOnly("net.createmod.ponder:Ponder-NeoForge-${minecraft_version}:${ponder_version}") // Create
//    modRuntimeOnly("dev.isxander:yet-another-config-lib:$yacl_version-neoforge") // Friends&Foes
//    modRuntimeOnly("com.teamresourceful.resourcefullib:resourcefullib-neoforge-1.21:$resourcefullib_version") // Chipped, Handcrafted, Cozy, Friends&Foes

    //+ REQUIRED - The modules access libaries from below - ONLY IN NEOFORGE
    modCompileOnly("curse.maven:framework-549225:7530361") // Refurbished-Furniture, +Mighty-Mail, Backpacked
    modCompileOnly("maven.modrinth:titanium:1.21-4.0.43") // Functional-Storage
    modCompileOnly("maven.modrinth:blueprint:8.1.0") // The-Outer-End, Woodworks, Boatload, Upgrade-Aquatic, Curiosities!, Autumnity
    modCompileOnly("maven.modrinth:valhelsia-core:1.1.4") // Valhelsia Structure, Valhelsia Furniture


    //+ OTHER MAVENs
    modCompileOnly("com.tterrag.registrate:Registrate:${property("registrate_version")}") // Create, The-Twilight-Forest, Tropicraft

    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-39-SNAPSHOT") // Quark - @ https://maven.blamejared.com/org/violetmoon/zeta/Zeta/
//    modCompileOnly("curse.maven:zeta-968868:7640154") // v1.1-39 | TEMP BACKUP MAVEN

//!! =================================================== IMPORTS ==================================================== \\
    //- ONLY FOR TESTING - can be commented out or enabled
//    modRuntimeOnly("curse.maven:another-furniture-610492:4034009") //WARNING: version 2.1.2-1.19.2
//    modRuntimeOnly("curse.maven:architects-palette-433862:6861008") //@ BETA
//    modRuntimeOnly("curse.maven:beautiful-campfires-1085950:6162194")
//    modRuntimeOnly("curse.maven:backpacked-352835:7500602")
//    modRuntimeOnly("curse.maven:camp-chair-531744:4579679") //!! 1.20.1
//    modRuntimeOnly("curse.maven:dawn-of-time-312359:7029195") // Fusion-Connected-Texture
//    modRuntimeOnly("curse.maven:decorative-blocks-reborn-1327768:6897419")
//    modRuntimeOnly("curse.maven:exlines-bark-carpets-527296:4094399") //!! 1.20.1
//    modRuntimeOnly("curse.maven:farmersdelight-398521:8083481")
//    modRuntimeOnly("curse.maven:friends-and-foes-forge-602059:6470209") // Yet-Another-Config-Lib-V3, ResourcefulLib
//    modRuntimeOnly("curse.maven:furnish-547069:4821511") //!! 1.20.1 -> 1.21.1 Cannot remap access widener from namespace 'mojang'. Expected: 'intermediary'
//    modRuntimeOnly("curse.maven:handcrafted-538214:5617252") // Resourceful-Lib
//    modRuntimeOnly("curse.maven:missing-wilds-622590:6302230")
//    modRuntimeOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib
//    modRuntimeOnly("curse.maven:more-chest-variants-lieonlion-858032:5862569") // Quad - LieOnLion
//    modRuntimeOnly("curse.maven:more-crafting-tables-lieonlion-913586:5520190") // Quad - LieOnLion
//    modRuntimeOnly("curse.maven:refurbished-furniture-897116:6272849") // Framework
//    modRuntimeOnly("curse.maven:rechiseled-558998:7687594") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
//    modRuntimeOnly("curse.maven:storage-drawers-223852:6967730")
//    modRuntimeOnly("curse.maven:twigs-496913:4605097") //!! 1.20.1
//    modRuntimeOnly("curse.maven:valhelsia-furniture-694349:5189602") //!! 1.20.1
//    modRuntimeOnly("curse.maven:variant-vanilla-blocks-866509:4997060") //v1.3.6 //@ 1.20.1-NOT_AVAILABLE
//    modRuntimeOnly("curse.maven:villagersplus-forge-817272:4996995") //!! 1.20.1

    //- OTHER MAVENS
//    modRuntimeOnly("maven.modrinth:stylish-stiles:l9FFA4BK") //!! 1.20.1
//    modRuntimeOnly("earth.terrarium.chipped:chipped-neoforge-${minecraft_version}:4.0.2") //INCLUDED: Athena, Resourceful-Lib, REQUIRED: +Bytecodecs

    //- LOCAL
//    modRuntimeOnly("copper-age-neoforge:copperagebackport-neoforge-1.21.1-0.1.4")

    //+ REQUIRED - The modules access libaries from below - ONLY IN NEOFORGE
    // ~/forge/mods LOCAL
    modCompileOnly("net.stehschnitzel.shutter:shutters-2.0.2-1.20.1")
    modCompileOnly("com.tynoxs.buildersdelight:BuildersDelight-1.20.1-v.1.3")
    //modCompileOnly("com.polipo.bookshelf:giacomos_bookshelf-1.20.1-1.3.9") // WIP

    // MACAW's
    modCompileOnly("maven.modrinth:macaws-bridges:3.1.2")
    modCompileOnly("maven.modrinth:macaws-doors:1.1.5")
    modCompileOnly("maven.modrinth:macaws-fences-and-walls:1.2.1")
    modCompileOnly("maven.modrinth:macaws-lights-and-lamps:1.1.5")
    modCompileOnly("maven.modrinth:macaws-paths-and-pavings:1.1.1")
    modCompileOnly("curse.maven:macaws-roofs-352039:6494399")
    modCompileOnly("maven.modrinth:macaws-trapdoors:1.1.5")
    modCompileOnly("maven.modrinth:macaws-windows:2.4.2")
    modCompileOnly("maven.modrinth:macaws-furniture:3.4.1")
    modCompileOnly("maven.modrinth:macaws-stairs:1.0.2")

    //+ GENERAL
    modCompileOnly("maven.modrinth:absent-by-design:1.21.1-1.9.2") //!! 1.20.1
    modCompileOnly("maven.modrinth:beautify:2.0.2")
    modCompileOnly("maven.modrinth:bibliocraft-legacy:1.21.1-1.6.5")
    modCompileOnly("maven.modrinth:boatload:5.0.1") //!! 1.20.1
    modCompileOnly("maven.modrinth:buildersaddition:2.1.2")
    modCompileOnly("curse.maven:corail-pillar-266228:5669131") //TODO: Move the module to COMMON
    modCompileOnly("maven.modrinth:curiosities-syndicate:0.2.1") // Blueprint
    modCompileOnly("curse.maven:decoration-delight-687475:5563942") //!! 1.20.1
    modCompileOnly("curse.maven:domum-ornamentum-527361:7812603") //!! 1.20.1
    modCompileOnly("maven.modrinth:dramatic-doors:1.21.1-3.3.2")
    modCompileOnly("maven.modrinth:functional-storage:1.21-1.5.7")
    modCompileOnly("curse.maven:infinity-buttons-661902:6630983") //!! 1.20.1
    modCompileOnly("maven.modrinth:just-a-raft-mod:7.0.4")
    modCompileOnly("maven.modrinth:lightmans-currency:1.20.1-2.3.0.4g")
    modCompileOnly("curse.maven:mighty-mail-902986:6542124") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302230")
    modCompileOnly("curse.maven:more-crafting-tables-for-forge-417365:6002554") //CRAFTING_TABLES for FORGE //!! 1.20.1
    modCompileOnly("maven.modrinth:mosaic-carpentry:1.3") //!! 1.20.1
    modCompileOnly("maven.modrinth:oreberries-replanted:0.5.2") //!! 1.20.1
    modCompileOnly("curse.maven:pokecube-aoi-285121:7374140") //!! 1.20.1
    modCompileOnly("curse.maven:premium-wood-353515:3905203") //!! 1.20.1
    modCompileOnly("maven.modrinth:productivebees:1.21.1-13.13.0") // WIP //!! 1.20.1
    modCompileOnly("maven.modrinth:redeco:1.14.1") //!! 1.20.1
    modCompileOnly("maven.modrinth:table-top-craft:1.20.1-6.1.2") //!! 1.20.1
    modCompileOnly("maven.modrinth:the-graveyard-forge:3.1") //!! 1.20.1
    modCompileOnly("curse.maven:the-twilight-forest-227639:7797302")
    modCompileOnly("maven.modrinth:timber-frames:2.0.0") //!! 1.20.1
    modCompileOnly("maven.modrinth:tropicraft:9.8.1-1.21.1")
    modCompileOnly("maven.modrinth:unusual-furniture:1.1.2c")
    modCompileOnly("maven.modrinth:valhelsia-structures:1.1.2") // Valhelsia-Core
    modCompileOnly("curse.maven:variant-crafting-tables-565095:4585921") //!! 1.20.1
    modCompileOnly("curse.maven:woodster-869951:6732058")
    modCompileOnly("maven.modrinth:woodworks:4.0.2") // Blueprint
    modCompileOnly("maven.modrinth:workshop-for-handsome-adventurer:1.36.0")
    modCompileOnly("maven.modrinth:xercamod:1.20.1-1.0.0") //!! 1.20.1

    modCompileOnly("maven.modrinth:regions-unexplored:0.6-neoforge-21.1")

    // OTHER MAVENS
    modCompileOnly("maven.modrinth:building-but-better:2.0pre4") // MidnightLib //!! 1.20.1
    modCompileOnly("com.simibubi.create:create-${property("minecraft_version")}:${property("create_version")}:slim") { isTransitive = false } // Registrate, Flywheel, Ponder

    modCompileOnly("org.violetmoon.quark:Quark:4.1-475-SNAPSHOT") // Zeta, Biolith @ https://maven.blamejared.com/org/violetmoon/quark/Quark/
//    modCompileOnly("curse.maven:quark-243121:7640331") // v4.1.474 | TEMP BACKUP MAVEN

    // ======================================== DISABLED FOR A REASON =============================================== \\
//     modRuntimeOnly("curse.maven:geckolib-388172:5460309") //
    // implementation fg.deobf("curse.maven:marg-324494:3723497") // LIBRARY
    // implementation fg.deobf("curse.maven:ortuslib-616457:3768197") // LIBRARY
    // implementation fg.deobf("curse.maven:project-brazier-238326:3835038")

    // modImplementation("curse.maven:benched-417063:3821546") // Use OBJ Format

//     modRuntimeOnly("curse.maven:malum-484064:5718038") // MAGIC MOD & use BBModel

//!! ============================================== FOR TESTING ===================================================== \\

//    modRuntimeOnly("curse.maven:strata-forge-edition-387296:4989643") // STONE-TYPES //!! 1.20.1
//    modRuntimeOnly("curse.maven:endless-biomes-667688:5109705") //!! 1.20.1
//    modRuntimeOnly("curse.maven:blue-skies-312918:5010316") // structure-gel-api //!! 1.20.1
    modRuntimeOnly("maven.modrinth:biomes-o-plenty:21.1.0.13") // Terrablender, GlitchCore
//    modRuntimeOnly("curse.maven:upgrade-aquatic-326895:6969604") // Blueprint
//    modRuntimeOnly("curse.maven:autumnity-365045:7118591") // Blueprint
//    modRuntimeOnly("curse.maven:the-outer-end-430404:5043937") // Blueprint //!! 1.20.1
//    modRuntimeOnly("curse.maven:alexs-caves-924854:5162617") // citadel //!! 1.20.1
//    modRuntimeOnly("curse.maven:deeperdarker-659011:6463247")
//    modRuntimeOnly("curse.maven:frightful-winter-1241289:6447798")
//    modRuntimeOnly("curse.maven:natures-spirit-1044992:6962784") // Tinted LeavesType
//    modRuntimeOnly("curse.maven:lets-do-meadow-821483:7672847") // Architectury

//    modImplementation("curse.maven:terrafirmacraft-302973:7024199") // patchouli

}


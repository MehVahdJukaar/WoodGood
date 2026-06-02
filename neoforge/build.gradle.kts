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
        else modApi("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}:neoforge") { isTransitive = false }
    }

//!! TOOLS ========================================================================================================== \\
    modRuntimeOnly("dev.emi:emi-neoforge:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-neoforge-${property("minecraft_version")}:${property("crafttweaker_version")}")
    modRuntimeOnly("curse.maven:jei-238222:7420587") // v19.25.0.322
    modRuntimeOnly("curse.maven:jade-324717:7545219") // v15.10.1
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452")

    //+ REQUIRED
    modImplementation("curse.maven:configured-457570:7276577") // v2.6.2

//!! ================================================ DEPENDENCIES ================================================== \\
    //@ IMPORTANT: RLM - "REQUIRED LOCAL MOD" - You need to get the mod and put it in ~/forge/run/mods/....
    //@ IMPORTANT: DNU - "DO NOT USE" the modRunTimeOnly because it can cause issues in production

    //- Only For TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:terrablender-neoforge-940057:6054947") // v4.0.0.2 | BOP, Regions-Unexplored
    modRuntimeOnly("curse.maven:glitchcore-955399:8109792") // v2.1.0.0 | BOP
//    modRuntimeOnly("curse.maven:athena-841890:5629395") // v4.0.1 | Chipped
//    modRuntimeOnly("curse.maven:architectury-api-419699:5786327") // v13.0.8 | REI, Furnish, [Let's Do]-Meadow
//    modRuntimeOnly("curse.maven:cloth-config-348521:5729127") // v15.0.140 | REI
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:7471474") // v1.2.12 | Dawn of Time, Timber-Frame, Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:5546996") // v1.1.8 | Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-core-lib-454372:7521894") // v1.1.20 | Rechiseled
//    modRuntimeOnly("maven.modrinth:midnightlib:YeePowOJ") // v1.6.3 | Building-But-Better
    modRuntimeOnly("curse.maven:patchouli-306770:7730942") // v92 | TFC, Timber-Frames
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
    modCompileOnly("curse.maven:framework-549225:7530361") // v0.13.11 | Refurbished-Furniture, +Mighty-Mail, Backpacked
    modCompileOnly("curse.maven:titanium-287342:7951516") // v4.0.40 | Functional-Storage
    modCompileOnly("curse.maven:blueprint-382216:8048607") // v8.0.8 | The-Outer-End, Woodworks, Boatload, Upgrade-Aquatic, Curiosities!, Autumnity
    modCompileOnly("curse.maven:valhelsia-core-416935:6296775") // v1.1.5 | Valhelsia Structure, Valhelsia Furniture


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
    modCompileOnly("curse.maven:macaws-bridges-351725:7627896")
    modCompileOnly("curse.maven:macaws-doors-378646:7618651") // v1.1.5
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:7308338")
    modCompileOnly("curse.maven:macaws-lights-and-lamps-502372:7304075")
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7029451") // v1.1.1
    modCompileOnly("curse.maven:macaws-roofs-352039:6494399")
    modCompileOnly("curse.maven:macaws-trapdoors-400933:7256148")
    modCompileOnly("curse.maven:macaws-windows-363569:7317672")
    modCompileOnly("curse.maven:macaws-furniture-359540:7255584")
    modCompileOnly("curse.maven:macaws-stairs-1119394:7317479")

    //+ GENERAL
    modCompileOnly("curse.maven:absent-by-design-305840:8030393") //!! 1.20.1
    modCompileOnly("curse.maven:beautify-decorate-633252:5947973")
    modCompileOnly("curse.maven:bibliocraft-legacy-1122260:7740866")
    modCompileOnly("curse.maven:boatload-337396:7118750")
    modCompileOnly("curse.maven:buildersaddition-389697:8155184")
    modCompileOnly("curse.maven:corail-pillar-266228:5669131") //TODO: Move the module to COMMON
    modCompileOnly("curse.maven:curiosities-syndicate-1489190:7893051") // Blueprint
    modCompileOnly("curse.maven:decoration-delight-687475:5563942") //!! 1.20.1
    modCompileOnly("curse.maven:domum-ornamentum-527361:7812603") //!! 1.20.1
    modCompileOnly("curse.maven:dramatic-doors-380617:6479044")
    modCompileOnly("curse.maven:functional-storage-556861:8179577")
    modCompileOnly("curse.maven:infinity-buttons-661902:6630983") //!! 1.20.1
    modCompileOnly("curse.maven:just-a-raft-mod-274350:6945796")
    modCompileOnly("curse.maven:lightmans-currency-472521:8133254")
    modCompileOnly("curse.maven:mighty-mail-902986:6542124") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302230")
    modCompileOnly("curse.maven:more-crafting-tables-for-forge-417365:6002554") //CRAFTING_TABLES for FORGE
    modCompileOnly("curse.maven:mosaic-carpentry-690226:7325187") //!! 1.20.1
    modCompileOnly("curse.maven:oreberries-replanted-454062:6123417")
    modCompileOnly("curse.maven:pokecube-aoi-285121:7374140")
    modCompileOnly("curse.maven:premium-wood-353515:3905203") //!! 1.20.1
    modCompileOnly("curse.maven:productivebees-377897:8022994") //v12.5.1 | WIP //!! 1.20.1
    modCompileOnly("curse.maven:redeco-897377:6223817") //!! 1.20.1
    modCompileOnly("curse.maven:table-top-craft-467136:5318681") //!! 1.20.1
    modCompileOnly("curse.maven:the-graveyard-forge-531188:5114579") //!! 1.20.1
    modCompileOnly("curse.maven:the-twilight-forest-227639:7797302")
    modCompileOnly("curse.maven:timber-frames-606011:5372390") //!! 1.20.1
    modCompileOnly("curse.maven:tropicraft-254794:6600109")
    modCompileOnly("curse.maven:unusual-furniture-1278034:7332052")
    modCompileOnly("curse.maven:valhelsia-structures-347488:6814480") // Valhelsia-Core
    modCompileOnly("curse.maven:variant-crafting-tables-565095:4585921") //!! 1.20.1
    modCompileOnly("curse.maven:woodster-869951:6732058")
    modCompileOnly("curse.maven:woodworks-543610:7118286") // Blueprint
    modCompileOnly("curse.maven:workshop-for-handsome-adventurer-875843:7903929")
    modCompileOnly("curse.maven:xercamod-341575:4667995") //!! 1.20.1

    modCompileOnly("curse.maven:regions-unexplored-659110:8167399")

    // OTHER MAVENS
    modCompileOnly("maven.modrinth:building-but-better:2.0pre4") // v1.1.1 | MidnightLib //!! 1.20.1
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
    modRuntimeOnly("curse.maven:biomes-o-plenty-220318:7251965") // Terrablender, GlitchCore
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


plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

//val path = System.getenv("REPOS21_1").toString()
dependencies {

//    common(project(path: ":common", configuration: "namedElements")) { transitive false }
//    shadowCommon(project(path: ":common", configuration: "transformProductionFabric")) { transitive false }

//    modCompileOnly("me.shedaniel.cloth:cloth-config-fabric:${property("cloth_version")}") {
//        exclude(group: "net.fabricmc.fabric-api")
//    }

    // porting_lib required by fabric & everycomp
//    for (String module in port_lib_modules.split(",")) {
    // modImplementation("io.github.fabricators_of_create.Porting-Lib:$module:$port_lib_version")
//}

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\

    //- LOCAL
    if (property("enable_moonlight_test").toString().toBoolean()) {
       // modApi(files(path + "\\Moonlight\\fabric\\build\\libs\\moonlight-${property("moonlight_testVersion")}-fabric.jar"))
    }
    //+ MAVEN
    else {
        if (property("maven_backup").toString().toBoolean()) modApi("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else {
            modImplementation("net.mehvahdjukaar:moonlight-fabric:${property("moonlight_version")}")
        }
    }

//!! SUPPLEMENTARIES ------------------------------------------------------------------------------------------------ \\
//     modImplementation("net.mehvahdjukaar:supplementaries-fabric:${project.supplementaries_version}")

//!! TOOLS ========================================================================================================== \\
    modRuntimeOnly("dev.emi:emi-fabric:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-fabric-${property("minecraft_version")}:${property("crafttweaker_version")}")
    modRuntimeOnly("curse.maven:jade-324717:7545228")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452") // required loom 1.7.410

    //+ REQUIRED
    modImplementation("maven.modrinth:configured:1.8.0")

//!! ================================================ DEPENDENCIES ================================================== \\
    //@ IMPORTANT: RLM - "REQUIRED LOCAL MOD" - You need to get the mod and put it in ~/fabric/run/mods/....
    //@ IMPORTANT: DNU - "DO NOT USE" the modRunTimeOnly because it can cause issues in production

    //- Only For TESTING - can be commented out or enabled
    modRuntimeOnly("maven.modrinth:terrablender:4.1.0.8") // Regions-Unexplored, Biomes-O'-Plenty
    modRuntimeOnly("maven.modrinth:glitchcore:2.1.0.2") // Biomes-O'-Plenty
//    modRuntimeOnly("curse.maven:cloth-config-348521:4973440") // v11.1.118 | Required by: REI
//    modRuntimeOnly("curse.maven:architectury-api-419699:5137936") // v9.2.14 | Required by: REI, Garden-Of-The-Dead
//    modRuntimeOnly("curse.maven:forge-config-api-port-fabric-547434:5982384") //v21.1.3 | Regions-Unexplored
//    modRuntimeOnly("curse.maven:fusion-connected-textures-854949:5129312") // v1.1.1 | Rechiseled //!! 1.20
//    modRuntimeOnly("curse.maven:supermartijn642s-config-lib-438332:5546988") // v1.1.8 | Rechiseled
//    modRuntimeOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled
//    modRuntimeOnly("curse.maven:trinkets-341284:5173501") // Backpacked
//    modRuntimeOnly("curse.maven:monolib-968432:6123972") //v2.0.0 | More-Beautiful-Torches
//    modRuntimeOnly("curse.maven:athena-841890:5176880") // Chipped

    //- OTHER LIBRARIES
//    modRuntimeOnly("org.reflections:reflections:0.10.2") // Refurbished-Furniture
//    modRuntimeOnly("com.jozufozu.flywheel:flywheel-fabric-${minecraft_version}:${flywheel_fabric_version}") // Create
//    modRuntimeOnly("dev.isxander:yet-another-config-lib:$yacl_version-fabric") // Friends&Foes
//    modRuntimeOnly("com.teamresourceful.resourcefullib:resourcefullib-fabric-1.21:$resourcefullib_version") // Chipped, Handcrafted, Cozy, Friends&Foes

    //- OTHER MAVENS

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    modCompileOnly("curse.maven:framework-549225:7530359") // Refurbished-Furniture, +Mighty-Mail, Backpacked
    modCompileOnly("maven.modrinth:quad:1.2.9+1.21.1-Fabric") // More-Chest-Variants, More-Crafting-Tables
    modCompileOnly("curse.maven:valhelsia-core-416935:6296784") // Valhelsia-Furniture
    modCompileOnly("maven.modrinth:frozenlib:2.2.4-mc1.21.1") // Wilder-Wild

    //+ OTHER MAVENS
    modCompileOnly("com.tterrag.registrate_fabric:Registrate:${property("registrate_fabric_version")}") // Create, The-Twilight-Forest

    // Special dumb stuff required by TerraBlender
    modImplementation("com.terraformersmc:modmenu:11.0.3")
    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-base:6.1.1")
    modImplementation("dev.onyxstudios.cardinal-components-api:cardinal-components-entity:6.1.1")
    // so dumber stuff requird by GlitchCore
    modImplementation("net.jodah:typetools:0.6.3")
    modImplementation("com.electronwill.night-config:core:3.8.1")
    modImplementation("com.electronwill.night-config:toml:3.8.1")

//!! =================================================== IMPORTS ==================================================== \\
    //- ONLY FOR TESTING - can be commented out or enabled
//    modRuntimeOnly("curse.maven:another-furniture-610492:4815126") //!! 1.20.1
//    modRuntimeOnly("curse.maven:architects-palette-433862:6861002") // Forge-Config-API-Port @ BETA
//    modRuntimeOnly("curse.maven:beautiful-campfires-1085950:6828158")
//    modRuntimeOnly("curse.maven:backpacked-352835:7500600")
    modCompileOnly("maven.modrinth:blockus:2.9.15+1.21.1")
//    modRuntimeOnly("curse.maven:camp-chair-531744:4579676") //!! 1.20.1
//    modRuntimeOnly("curse.maven:corail-pillar-266228:5613351")
//    modRuntimeOnly("curse.maven:dawn-of-time-312359:7029308")
//    modRuntimeOnly("curse.maven:decorative-blocks-reborn-1327768:6897415")
//    modRuntimeOnly("curse.maven:excessive-building-845097:6075987") // v3.3.10
    modCompileOnly("curse.maven:exlines-bark-carpets-527296:4661550") //!! 1.20.1
    modCompileOnly("maven.modrinth:farmers-delight-refabricated:1.21.1-3.3.3")
    modCompileOnly("maven.modrinth:friends-and-foes:fabric-4.0.26+mc1.21.1")
//    modRuntimeOnly("curse.maven:furnish-547069:5683297")
//    modRuntimeOnly("curse.maven:handcrafted-538214:5617253")
//    modRuntimeOnly("curse.maven:hearth-and-home-849364:4828281") //!! 1.20.1
//    modRuntimeOnly("curse.maven:missing-wilds-622590:6302231")
//    modRuntimeOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib
//    modRuntimeOnly("curse.maven:more-chest-variants-lieonlion-858032:5476664") // Quad - LieOnLion
//    modRuntimeOnly("curse.maven:more-crafting-tables-lieonlion-913586:5473304") // Quad - LieOnLion
//    modRuntimeOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
//    modRuntimeOnly("curse.maven:storage-drawers-223852:6967726")
//    modRuntimeOnly("curse.maven:table-top-craft-fabric-729535:5319819") //@RLM: exp4j //!! 1.20.1
    modCompileOnly("curse.maven:twigs-496913:4605097") //!! 1.20.1
    modCompileOnly("maven.modrinth:valhelsia-furniture:1.1.3") //!! 1.20.1
    modCompileOnly("curse.maven:variant-vanilla-blocks-866509:5703471")
    modCompileOnly("curse.maven:villagersplus-fabric-809542:5703471") //!! 1.20.1

    //- OTHER MAVENS
//    modRuntimeOnly("maven.modrinth:stylish-stiles:zLlRqz68") //!! 1.20.1
//    modRuntimeOnly("earth.terrarium.chipped:chipped-fabric-${minecraft_version}:4.0.2") //INCLUDED: Athena, Resourceful-Lib -NOTE: no need to enable these dependencies mods, it's already included

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    // ~/forge/mods LOCAL
    modCompileOnly("net.stehschnitzel.shutter:shutter-2.0.2-1.20.1")
    modCompileOnly("net.darktree.redbits:redbits-1.20.1-1.16.1")

    // MACAW's
    modCompileOnly("maven.modrinth:macaws-bridges:3.1.2")
    modCompileOnly("maven.modrinth:macaws-doors:1.1.5")
    modCompileOnly("maven.modrinth:macaws-fences-and-walls:1.2.1")
    modCompileOnly("maven.modrinth:macaws-lights-and-lamps:1.1.5")
    modCompileOnly("maven.modrinth:macaws-paths-and-pavings:1.1.1")
    modCompileOnly("curse.maven:macaws-roofs-352039:6494433")
    modCompileOnly("maven.modrinth:macaws-trapdoors:1.1.5")
    modCompileOnly("maven.modrinth:macaws-windows:2.4.2")
    modCompileOnly("maven.modrinth:macaws-furniture:3.4.1")
    modCompileOnly("maven.modrinth:macaws-stairs:1.0.2")

    //+ GENERAL
    modCompileOnly("maven.modrinth:beautify-refabricated:2.0.0+1.21.1") //!! 1.20.1
    modCompileOnly("maven.modrinth:bewitchment:1.20-10") //!! 1.20.1
    modCompileOnly("maven.modrinth:building-but-better:2.0pre4") //!! 1.20.1
    modCompileOnly("maven.modrinth:clutter:1.20.(0-1)-0.6.2") //@RML: Geckolib //!! 1.20.1
    modCompileOnly("maven.modrinth:create-fabric:6.0.8.1+build.1744-mc1.20.1") //!! 1.20.1
    modCompileOnly("maven.modrinth:dramatic-doors:1.20.1-3.3.3")
    modCompileOnly("curse.maven:exlines-awnings-526698:5478883")
    modCompileOnly("curse.maven:infinity-buttons-661902:5409967") //!! 1.20.1
    modCompileOnly("maven.modrinth:lightmans-currency:1.20.1-1.0.2.4") //!! Not maintained since 1.20.1
    modCompileOnly("curse.maven:mighty-mail-fabric-904097:4750271") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302231") //!! 1.20.1
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473562") // Framework, Reflection - @DNU
    modCompileOnly("curse.maven:the-twilight-forest-227639:4389094") //!! NOT AVAILABLE //!! 1.20.1
    modCompileOnly("maven.modrinth:wooden-hoppers:1.6.0") //!! 1.20.1

    modCompileOnly("maven.modrinth:regions-unexplored:0.6-fabric-21.1") // Forge-Config-API-Port

    // OTHER MAVENS
    modCompileOnly("maven.modrinth:wilder-wild:4.2.1-mc1.21.1") // Frozen-Lib
//    modCompileOnly("com.simibubi.create:create-fabric-1.20.1:$create_fabric_version") // Registrate, Flywheel //!! Need to update maven to get other libraries & the old maven are outdated

    // ======================================== DISABLED FOR A REASON =============================================== \\

//!!=============================================== FOR TESTING ===================================================== \\

    modRuntimeOnly("maven.modrinth:biomes-o-plenty:21.1.0.13")
//    modRuntimeOnly("curse.maven:gardens-of-the-dead-683174:4784268")
//    modRuntimeOnly("curse.maven:terrestria-323974:5799166")

}

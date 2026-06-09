plugins {
    id("com.possible-triangle.fabric")
}

fabric {
    dependOn(project(":common"))
    accessWidener(project(":common"))
}

val modId: String = property("mod_id").toString()
val modVersion: String = property("mod_version").toString()
tasks.remapJar {
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric")
}
tasks.remapSourcesJar {
    from(sourceSets.main.get().allSource)
    archiveBaseName.set(modId)
    archiveVersion.set(modVersion)
    archiveClassifier.set("fabric-sources")
}

//val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\

    modApi("net.mehvahdjukaar:moonlight-fabric:${property("moonlight_version")}")

//!! SUPPLEMENTARIES ------------------------------------------------------------------------------------------------ \\
//     modImplementation("net.mehvahdjukaar:supplementaries-fabric:${project.supplementaries_version}")

//!! TOOLS ========================================================================================================== \\
    modRuntimeOnly("dev.emi:emi-fabric:${property("emi_version")}+${property("minecraft_version")}")
    modRuntimeOnly("com.blamejared.crafttweaker:CraftTweaker-fabric-${property("minecraft_version")}:${property("crafttweaker_version")}")
    modRuntimeOnly("curse.maven:jade-324717:7545228")
//    modRuntimeOnly("curse.maven:worldedit-225608:5830452") // required loom 1.7.410

    //+ REQUIRED
    modImplementation("curse.maven:configured-457570:7276575")

//!! ================================================ DEPENDENCIES ================================================== \\
    //@ IMPORTANT: RLM - "REQUIRED LOCAL MOD" - You need to get the mod and put it in ~/fabric/run/mods/....
    //@ IMPORTANT: DNU - "DO NOT USE" the modRunTimeOnly because it can cause issues in production

    //- Only For TESTING - can be commented out or enabled
    modRuntimeOnly("curse.maven:terrablender-fabric-565956:6054948") // Regions-Unexplored, Biomes-O'-Plenty
    modRuntimeOnly("curse.maven:glitchcore-955399:8109791") // Biomes-O'-Plenty
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
    modCompileOnly("curse.maven:quad-932715:5903633") // More-Chest-Variants, More-Crafting-Tables
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
    //+ MIRRORED FROM COMMON - Required because dependOn(common) compiles common sources with fabric classpath
    modCompileOnly("curse.maven:another-furniture-610492:4815126") //!! 1.20.1
    modCompileOnly("curse.maven:architects-palette-433862:6861002") // Forge-Config-API-Port @ BETA
    modCompileOnly("curse.maven:backpacked-352835:7500600")
    modCompileOnly("curse.maven:corail-pillar-266228:5613351")
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:6897415")
    modCompileOnly("curse.maven:farmers-delight-refabricated-993166:8088691")
    modCompileOnly("curse.maven:friends-and-foes-551364:8154503")
    modCompileOnly("curse.maven:handcrafted-538214:6330030") // Resourceful-Lib
    modCompileOnly("curse.maven:mighty-mail-902986:6542124")
    modCompileOnly("curse.maven:missing-wilds-622590:6302231")
    modCompileOnly("curse.maven:more-chest-variants-lieonlion-858032:5476664") // Quad - LieOnLion
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473565")
    modCompileOnly("curse.maven:storage-drawers-223852:6967726")
    modCompileOnly("curse.maven:table-top-craft-fabric-729535:5319819") //@RLM: exp4j //!! 1.20.1
    modCompileOnly("curse.maven:twigs-496913:4605097")
    modCompileOnly("curse.maven:valhelsia-furniture-694349:6334936")
    modCompileOnly("curse.maven:variant-vanilla-blocks-866509:5703471")
    // OTHER MAVENs
    modCompileOnly("maven.modrinth:furnish-furniture:29")
    // ~/fabric/mods LOCAL
    modCompileOnly("local-copper-age-neoforge:copperagebackport-fabric-1.21.1-0.1.4")
    modCompileOnly("local-dawnoftimebuilder:Dawn Of Time-fabric-1.21.1-1.6.6")

    //- ONLY FOR TESTING - can be commented out or enabled
//    modRuntimeOnly("curse.maven:beautiful-campfires-1085950:6828158")
//    modRuntimeOnly("curse.maven:camp-chair-531744:4579676") //!! 1.20.1
//    modRuntimeOnly("curse.maven:dawn-of-time-312359:7029308")
//    modRuntimeOnly("curse.maven:exlines-bark-carpets-527296:4661550") //!! 1.20.1
//    modRuntimeOnly("curse.maven:handcrafted-538214:5617253")
//    modRuntimeOnly("curse.maven:hearth-and-home-849364:4828281") //!! 1.20.1
//    modRuntimeOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib
//    modRuntimeOnly("curse.maven:more-crafting-tables-lieonlion-913586:5473304") // Quad - LieOnLion
//    modRuntimeOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ]
//    modCompileOnly("curse.maven:villagersplus-fabric-809542:4996993") //!! 1.20.1

    //- OTHER MAVENS
//    modRuntimeOnly("maven.modrinth:stylish-stiles:zLlRqz68") //!! 1.20.1
//    modRuntimeOnly("earth.terrarium.chipped:chipped-fabric-${minecraft_version}:4.0.2") //INCLUDED: Athena, Resourceful-Lib -NOTE: no need to enable these dependencies mods, it's already included

    //+ REQUIRED - The modules access libaries from below - ONLY IN FABRIC
    // ~/fabric/mods LOCAL
    modCompileOnly("local-shutter-fabric:shutter-2.0.2-1.20.1")
    modCompileOnly("local-redbits-fabric:redbits-1.21-1.16.6")

    // MACAW's
    modCompileOnly("curse.maven:macaws-bridges-351725:7628029")
    modCompileOnly("curse.maven:macaws-doors-378646:7618800")
    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:7308375")
    modCompileOnly("curse.maven:macaws-lights-and-lamps-502372:7304042")
    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:7029506")
    modCompileOnly("curse.maven:macaws-roofs-352039:6494433")
    modCompileOnly("curse.maven:macaws-trapdoors-400933:7256233")
    modCompileOnly("curse.maven:macaws-windows-363569:7317646")
    modCompileOnly("curse.maven:macaws-furniture-359540:7255687")
    modCompileOnly("curse.maven:macaws-stairs-1119394:7317453")

    //+ GENERAL
    modCompileOnly("curse.maven:beautify-refabricated-809311:7553989")
    modCompileOnly("curse.maven:bewitchment-394915:5819540") //!! 1.20.1
    modCompileOnly("curse.maven:blockus-312289:7920575")
    modCompileOnly("curse.maven:building-but-better-989479:7627392") //!! 1.20.1
    modCompileOnly("curse.maven:clutter-826060:7876249") //@RML: Geckolib //!! 1.20.1
    modCompileOnly("curse.maven:create-fabric-624165:7286603") //!! 1.20.1
    modCompileOnly("curse.maven:dramatic-doors-380617:6479044")
    modCompileOnly("curse.maven:excessive-building-845097:6075987") // v3.3.10
    modCompileOnly("curse.maven:exlines-awnings-526698:5478883")
    modCompileOnly("curse.maven:infinity-buttons-661902:5409967") //!! 1.20.1
    modCompileOnly("curse.maven:lightmans-currency-fabric-724119:5544643") //!! Not maintained since 1.20.1
    modCompileOnly("curse.maven:mighty-mail-fabric-904097:4750271") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302231")
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473562") // Framework, Reflection - @DNU
    modCompileOnly("curse.maven:the-twilight-forest-227639:4389094") //!! NOT AVAILABLE //!! 1.20.1
    modCompileOnly("curse.maven:wooden-hoppers-406021:4796143") //!! 1.20.1

    modCompileOnly("curse.maven:regions-unexplored-659110:8167272") // Forge-Config-API-Port

    // OTHER MAVENS
    modCompileOnly("maven.modrinth:stylish-stiles:1.1.1-1.21")
    modCompileOnly("maven.modrinth:wilder-wild:4.2.1-mc1.21.1") // Frozen-Lib
//    modCompileOnly("com.simibubi.create:create-fabric-1.20.1:$create_fabric_version") // Registrate, Flywheel //!! Need to update maven to get other libraries & the old maven are outdated

    // ======================================== DISABLED FOR A REASON =============================================== \\

//!!=============================================== FOR TESTING ===================================================== \\

    modRuntimeOnly("curse.maven:biomes-o-plenty-220318:7251967")
//    modRuntimeOnly("curse.maven:gardens-of-the-dead-683174:4784268")
//    modRuntimeOnly("curse.maven:terrestria-323974:5799166")
}

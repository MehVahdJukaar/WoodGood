plugins {
    id("com.possible-triangle.common")
}

common {
    accessWidener()
}

val path = System.getenv("REPOS21_1").toString()
dependencies {

    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\
    //- Repository LOCAL

    //- LOCAL
    if (property("enable_moonlight_test").toString().toBoolean()) {
        modImplementation(files(path + "\\Moonlight\\common\\build\\libs\\moonlight-${property("moonlight_testVersion")}.jar"))
    }
    //+ MAVEN
    else {
        if (property("maven_backup").toString().toBoolean()) modImplementation("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else modImplementation("net.mehvahdjukaar:moonlight:${property("moonlight_version")}") { isTransitive = false }
    }

//!! ============================================= DEPENDENCIES ========================================================
    //+ REQUIRED - The modules access libaries from below
//    modCompileOnly("curse.maven:configured-457570:7122913") // v2.6.2 - NOT ADDED YET
    modCompileOnly("curse.maven:framework-549225:5911983") // v0.9.4 | +Refurbished-Furniture, Mighty-Mail, Backpacked
    modCompileOnly("curse.maven:resourceful-lib-570073:5659872") // v2.1.29
    modCompileOnly("curse.maven:architectury-api-419699:5137936") // v9.2.14
    modCompileOnly("curse.maven:valhelsia-core-416935:5376599") // Valhelsia-Furniture
//    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled

//    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-36-SNAPSHOT") // Quark - @ DNU - https://maven.blamejared.com/org/violetmoon/zeta/Zeta/
    modCompileOnly("curse.maven:zeta-968868:7640154") // v1.1-39 | TEMP BACKUP MAVEN

//!! ================================================ IMPORTS ==========================================================
    //+ REQUIRED - The modules access libaries from below
    // ~/common/mods LOCAL
//    modCompileOnly("local-bibliocraft_legacy_expanded:bibliocraftfabric-1.1.0") //@ Dont import because it was built with Loom-1.14+
    modCompileOnly("net.darktree.redbits:redbits-1.20.1-1.16.1") //FABRIC
//    modCompileOnly("net.stehschnitzel.shutter:shutter-2.0.2-1.20.1")

    // MACAW's
//    modCompileOnly("curse.maven:macaws-bridges-351725:5465222")
//    modCompileOnly("curse.maven:macaws-doors-378646:5439190")
//    modCompileOnly("curse.maven:macaws-fences-and-walls-453925:5442197")
//    modCompileOnly("curse.maven:macaws-lights-and-lamps-502372:5473592")
//    modCompileOnly("curse.maven:macaws-paths-and-pavings-629153:5430737")
//    modCompileOnly("curse.maven:macaws-roofs-352039:5554934")
//    modCompileOnly("curse.maven:macaws-trapdoors-400933:5431124")
//    modCompileOnly("curse.maven:macaws-windows-363569:5592083")
    modCompileOnly("curse.maven:macaws-furniture-359540:7255687")
//    modCompileOnly("curse.maven:macaws-stairs-1119394:5802484") // Not needed

    //+ GENERAL
    modCompileOnly("curse.maven:another-furniture-610492:4815126") //!! 1.20.1
    modCompileOnly("curse.maven:architects-palette-433862:6861002") //!! BETA
    modCompileOnly("curse.maven:backpacked-352835:7500600")
    modCompileOnly("curse.maven:beautiful-campfires-1085950:6828158")
    modCompileOnly("curse.maven:blockus-312289:7060780")
    modCompileOnly("curse.maven:camp-chair-531744:4579679") //!! 1.20.1
    modCompileOnly("curse.maven:chipped-456956:5813118")
    modCompileOnly("curse.maven:create-624165:5168511") //!! 1.20.1
//    modCompileOnly("curse.maven:corail-pillar-266228:5613351") //!! TODO: Add it for FABRIC
    modCompileOnly("curse.maven:dawn-of-time-312359:7029308")
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:6897415")
    modCompileOnly("curse.maven:excessive-building-845097:6075987")
    modCompileOnly("curse.maven:exlines-awnings-526698:4668406") //FABRIC //!! 1.20.1
    modCompileOnly("curse.maven:exlines-bark-carpets-527296:4661550") //!! 1.20.1
//    modCompileOnly("curse.maven:farmersdelight-398521:5962800")
    modCompileOnly("curse.maven:farmers-delight-refabricated-993166:6955339") // v3.3.3 -> 8088691
    modCompileOnly("curse.maven:friends-and-foes-551364:6354620")
    modCompileOnly("curse.maven:furnish-547069:5683297")
    modCompileOnly("curse.maven:handcrafted-538214:5617253")
    modCompileOnly("curse.maven:hearth-and-home-849364:4828281") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302231")
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib
    modCompileOnly("curse.maven:more-chest-variants-lieonlion-858032:5476664")
    modCompileOnly("curse.maven:more-crafting-tables-lieonlion-913586:5473304")
    modCompileOnly("curse.maven:refurbished-furniture-897116:6272856") // Framework
//    modCompileOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ] //!! Not created yet - do not import it because fabric-loom is v1.14+
    modCompileOnly("curse.maven:storage-drawers-223852:6967726")
    modCompileOnly("curse.maven:table-top-craft-fabric-729535:5319819") //!! 1.20.1
    modCompileOnly("curse.maven:twigs-496913:4605097") //!! 1.20.1
    modCompileOnly("curse.maven:valhelsia-furniture-694349:5189603") //!! 1.20.1
    modCompileOnly("curse.maven:variant-vanilla-blocks-866509:5703471")
    modCompileOnly("curse.maven:villagersplus-fabric-809542:4996993") //!! 1.20.1
    modCompileOnly("curse.maven:wooden-hoppers-406021:4796143") //FABRIC //!! 1.20.1

    //+ OTHER MAVENS
    modCompileOnly("maven.modrinth:stylish-stiles:zLlRqz68") //!! 1.20.1
    modCompileOnly("maven.modrinth:wilder-wild:4.0-mc1.21.1") //FABRIC

    modCompileOnly("org.violetmoon.quark:Quark:4.1-472-SNAPSHOT") // Zeta @ DNU - https://maven.blamejared.com/org/violetmoon/quark/Quark/
    modCompileOnly("curse.maven:quark-243121:7640331") // v4.1.474 | TEMP BACKUP MAVEN

    //+ LOCAL
    modCompileOnly("copper-age-common:copperagebackport-fabric-1.21.1-0.1.4")

}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}

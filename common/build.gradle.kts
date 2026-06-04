plugins {
    id("com.possible-triangle.common")
}

common {
    accessWidener()
}

//val path = System.getenv("REPOS21_1").toString()
dependencies {

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\

    modApi("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}")
    accessTransformers("net.mehvahdjukaar:moonlight-common:${property("moonlight_version")}")


//!! ============================================= DEPENDENCIES ========================================================
    //+ REQUIRED - The modules access libaries from below
//    modCompileOnly("curse.maven:configured-457570:7122913") // v2.6.2 - NOT ADDED YET
    modCompileOnly("curse.maven:framework-549225:7530361") // +Refurbished-Furniture, Mighty-Mail, Backpacked
    modCompileOnly("curse.maven:resourceful-lib-570073:5973188")
    modCompileOnly("curse.maven:architectury-api-419699:5786327")
    modCompileOnly("curse.maven:valhelsia-core-416935:6296775") // Valhelsia-Furniture
//    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled

//    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-36-SNAPSHOT") // Quark - @ DNU - https://maven.blamejared.com/org/violetmoon/zeta/Zeta/
    modCompileOnly("curse.maven:zeta-968868:7980010") // TEMP BACKUP MAVEN

//!! ================================================ IMPORTS ==========================================================
    //+ REQUIRED - The modules access libaries from below
    // ~/common/mods LOCAL
    modCompileOnly("local-dawnoftimebuilder:dawnoftimebuilder-neoforge-1.21.1-1.6.6")
    modCompileOnly("copper-age-common:copperagebackport-neoforge-1.21.1-0.1.4")
//    modCompileOnly("local-bibliocraft_legacy_expanded:bibliocraftfabric-1.1.0") //@ Dont import because it was built with Loom-1.14+
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
    modCompileOnly("curse.maven:macaws-furniture-359540:7255584")
//    modCompileOnly("curse.maven:macaws-stairs-1119394:5802484") // Not needed

    //+ GENERAL
    modCompileOnly("curse.maven:another-furniture-610492:7355747")
    modCompileOnly("curse.maven:architects-palette-433862:6861008") //!! BETA
    modCompileOnly("curse.maven:backpacked-352835:7866688")
    modCompileOnly("curse.maven:beautiful-campfires-1085950:6162194")
    modCompileOnly("curse.maven:camp-chair-531744:4579679") //!! 1.20.1
    modCompileOnly("curse.maven:chipped-456956:5813117")
    modCompileOnly("curse.maven:create-624165:5168511")
//    modCompileOnly("curse.maven:corail-pillar-266228:5613351") //!! TODO: Add it for FABRIC
    modCompileOnly("curse.maven:decorative-blocks-reborn-1327768:7926194")
    modCompileOnly("curse.maven:exlines-bark-carpets-527296:5259192")
    modCompileOnly("curse.maven:farmersdelight-398521:8083481")
//    modCompileOnly("curse.maven:furnish-547069:7662583-neoforge")
    modCompileOnly("curse.maven:handcrafted-538214:6330030")
    modCompileOnly("curse.maven:hearth-and-home-849364:5310272") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302230")
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib
    modCompileOnly("curse.maven:more-chest-variants-lieonlion-858032:7310871")
    modCompileOnly("curse.maven:more-crafting-tables-lieonlion-913586:5330971")
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473565") // Framework
//    modCompileOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ] //!! Not created yet - do not import it because fabric-loom is v1.14+
    modCompileOnly("curse.maven:storage-drawers-223852:6995432") // 1.21.1-13.11.4 NeoForge
    modCompileOnly("curse.maven:table-top-craft-fabric-729535:5319819") //!! 1.20.1
    modCompileOnly("curse.maven:twigs-496913:8191595")
    modCompileOnly("curse.maven:valhelsia-furniture-694349:6341023")
    modCompileOnly("curse.maven:villagersplus-fabric-809542:4996993") //!! 1.20.1

    //+ OTHER MAVENS
    modCompileOnly("maven.modrinth:stylish-stiles:1.1.1-1.20.4") //!! 1.20.1
    modCompileOnly("maven.modrinth:furnish-furniture:27-neoforge")
    modCompileOnly("org.violetmoon.quark:Quark:4.1-472-SNAPSHOT") // Zeta @ DNU - https://maven.blamejared.com/org/violetmoon/quark/Quark/
    modCompileOnly("curse.maven:quark-243121:8146177") // TEMP BACKUP MAVEN


}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}

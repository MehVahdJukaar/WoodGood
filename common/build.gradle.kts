plugins {
    id("com.possible-triangle.common")
}

common {
    accessWidener()
}

//val path = System.getenv("REPOS21_1").toString()
dependencies {

    // We depend on fabric loader here to use the fabric @Environment annotations and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation("net.fabricmc:fabric-loader:${property("fabric_loader_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_api_version")}")

//!! MOONLIGHT LIB (REQUIRED) --------------------------------------------------------------------------------------- \\
    //- Repository LOCAL

    //- LOCAL
    if (property("enable_moonlight_test").toString().toBoolean()) {
       // modImplementation(files(path + "\\Moonlight\\common\\build\\libs\\moonlight-${property("moonlight_testVersion")}.jar"))
    }
    //+ MAVEN
    else {
        if (property("maven_backup").toString().toBoolean()) modImplementation("maven.modrinth:moonlight:${property("moonlight_version")}-fabric")
        else {
            modCompileOnly("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
            accessTransformers("net.mehvahdjukaar:moonlight-neoforge:${property("moonlight_version")}")
        }
    }

//!! ============================================= DEPENDENCIES ========================================================
    //+ REQUIRED - The modules access libaries from below
//    modCompileOnly("curse.maven:configured-457570:7122913") // v2.6.2 - NOT ADDED YET
    modCompileOnly("curse.maven:framework-549225:7530361") // +Refurbished-Furniture, Mighty-Mail, Backpacked
    modCompileOnly("maven.modrinth:resourceful-lib:3.0.12")
    modCompileOnly("maven.modrinth:architectury-api:13.0.8+neoforge")
    modCompileOnly("maven.modrinth:valhelsia-core:1.1.4") // Valhelsia-Furniture
//    modCompileOnly("curse.maven:supermartijn642s-core-lib-454372:7521829") // v1.1.20 | Rechiseled

//    modCompileOnly("org.violetmoon.zeta:Zeta:1.1-36-SNAPSHOT") // Quark - @ DNU - https://maven.blamejared.com/org/violetmoon/zeta/Zeta/
    modCompileOnly("maven.modrinth:zeta:1.1-40") // TEMP BACKUP MAVEN

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
    modCompileOnly("maven.modrinth:macaws-furniture:3.4.1")
//    modCompileOnly("curse.maven:macaws-stairs-1119394:5802484") // Not needed

    //+ GENERAL
    modCompileOnly("maven.modrinth:another-furniture:4.0.2") //!! 1.20.1
    modCompileOnly("curse.maven:backpacked-352835:7866688") //!! 1.20.1
    modCompileOnly("maven.modrinth:beautiful-campfires:1.0.2")
    modCompileOnly("curse.maven:camp-chair-531744:4579679") //!! 1.20.1
    modCompileOnly("maven.modrinth:chipped:4.0.2")
    modCompileOnly("maven.modrinth:create:6.0.10+mc1.21.1") //!! 1.20.1
//    modCompileOnly("curse.maven:corail-pillar-266228:5613351") //!! TODO: Add it for FABRIC
    modCompileOnly("maven.modrinth:dawn-of-time:1.6.6")
//    modCompileOnly("curse.maven:farmersdelight-398521:5962800")
    modCompileOnly("maven.modrinth:hearth-and-home:1.20.1-2.0.3") //!! 1.20.1
    modCompileOnly("curse.maven:missing-wilds-622590:6302230")
    modCompileOnly("curse.maven:more-beautiful-torches-860325:5609745") // MonoLib //!! 1.20.1
    modCompileOnly("curse.maven:more-chest-variants-lieonlion-858032:7310871")
    modCompileOnly("maven.modrinth:more-crafting-tables-lieonlion:1.2.9+1.21-Neo") //!! 1.20.1
    modCompileOnly("curse.maven:refurbished-furniture-897116:7473565") // Framework
//    modCompileOnly("curse.maven:rechiseled-558998:7687483") // Fusion, supermartijn642s-[ Config-Lib, Core-Lib ] //!! Not created yet - do not import it because fabric-loom is v1.14+
    modCompileOnly("maven.modrinth:storagedrawers:1.21.1-13.11.4")
    modCompileOnly("maven.modrinth:table-top-craft:1.20.1-6.1.2") //!! 1.20.1
    modCompileOnly("maven.modrinth:valhelsia-furniture:1.1.1") //!! 1.20.1
    modCompileOnly("maven.modrinth:villagersplus:3.1") //!! 1.20.1

    //+ OTHER MAVENS
    modCompileOnly("maven.modrinth:stylish-stiles:1.1.1-1.20.4") //!! 1.20.1

    modCompileOnly("org.violetmoon.quark:Quark:4.1-472-SNAPSHOT") // Zeta @ DNU - https://maven.blamejared.com/org/violetmoon/quark/Quark/
    modCompileOnly("maven.modrinth:quark:4.1-480") // TEMP BACKUP MAVEN

    modCompileOnly("copper-age-common:copperagebackport-fabric-1.21.1-0.1.4")

}

tasks.named("copyAccessTransformersPublications") {
    dependsOn(":common:transformAccessWidener")
}

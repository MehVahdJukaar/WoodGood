package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.client.TextureCache;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

public class SpriteHelper {

    public static void addHardcodedSprites() {
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log", WoodGood.res("block/cactus_side"));
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log_top", WoodGood.res("block/cactus_top"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log", res("block/stripped_cactus_side"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log_top", res("block/stripped_cactus_top"));

        // End's Phantasm
        addOptional("phantasm:ebony_packed_stems", "_side" , "phantasm:blocks/ebony_stems_side");
        addOptional("phantasm:ebony_packed_stems", "_top" , "phantasm:blocks/ebony_stems_top");
        addOptional("phantasm:stripped_ebony_packed_stems", "_side" , "phantasm:blocks/ebony_stems_side_stripped");
        addOptional("phantasm:stripped_ebony_packed_stems", "_top" , "phantasm:blocks/ebony_stems_top_stripped");

        // Regions Unexplored
//        addOptional("regions_unexplored:eucalyptus_log", "_side", WoodGood.MOD_ID+":block/regions_unexplored/eucalyptus_log");
            // Leaves
        addOptional("regions_unexplored:alpha_leaves", "_leaves", "regions_unexplored:block/alpha_oak_leaves");
        addOptional("regions_unexplored:apple_oak_leaves", "_leaves", "regions_unexplored:block/apple_oak_leaves_stage_0");
        addOptional("regions_unexplored:flowering_leaves", "_leaves", "regions_unexplored:item/flowering_leaves");
        addOptional("regions_unexplored:palm_leaves", "_leaves", "regions_unexplored:block/palm_leaves_side");
        addOptional("regions_unexplored:enchanted_birch_leaves", "_leaves", "regions_unexplored:item/enchanted_birch_leaves");
        addOptional("regions_unexplored:silver_birch_leaves", "_leaves", "regions_unexplored:item/silver_birch_leaves");
        addOptional("regions_unexplored:small_oak_leaves", "_leaves", "minecraft:block/oak_leaves");

        // Endless Biomes
//        addOptional("endlessbiomes:twisted_stem", "_side", "endlessbiomes:blocks/twistedlogsidetest");
//        addOptional("endlessbiomes:twisted_stem", "_top", "endlessbiomes:blocks/twistedlogtoptest");
//        addOptional("endlessbiomes:stripped_twisted_stem", "_side", "endlessbiomes:blocks/twistedstrippedlogsidetest");
//        addOptional("endlessbiomes:stripped_twisted_stem", "_top", "endlessbiomes:blocks/twistedstrippedlogtoptest");

//        addOptional("endlessbiomes:penumbra_stem", "_side", "endlessbiomes:blocks/penumbrallogsidenewest");
//        addOptional("endlessbiomes:penumbra_stem", "_top", "endlessbiomes:blocks/penumbrallogtopnewest");
//        addOptional("endlessbiomes:stripped_penumbra_stem", "_side", "endlessbiomes:blocks/strippedpenumbralogsidenewest");
//        addOptional("endlessbiomes:stripped_penumbra_stem", "_top", "endlessbiomes:blocks/strippedpenumbralogtopnewest");

        // Gardens Of The Dead
//        addOptional("gardens_of_the_dead:whistlecane", "_side", "gardens_of_the_dead:block/whistlecane_block");
//        addOptional("gardens_of_the_dead:whistlecane", "_top", "gardens_of_the_dead:block/whistlecane_block_top");
//        addOptional("gardens_of_the_dead:stripped_soulblight_stem", "_side", "gardens_of_the_dead:block/stripped_soulblight_stem");
//        addOptional("gardens_of_the_dead:stripped_soulblight_stem", "_top", "gardens_of_the_dead:block/stripped_soulblight_stem_top");

        // BetterNether
//        addOptional("betternether:nether_reed_stem", "_side", "betternether:block/nether_reed_planks_side");
//        addOptional("betternether:nether_reed_stem", "_top", "betternether:block/nether_reed_planks_top");

        // Blue Skies
            // Leaves
//        addOptional("blue_skies:cherry_leaves", "_leaves", "blue_skies:block/leaves/cherry_leaves_grown");

        // L_Ender 's Cataclysm
//        addOptional("cataclysm:chorus_stem", "_side", "cataclysm:block/chorus_stem");
//        addOptional("cataclysm:chorus_stem", "_top", "cataclysm:block/chorus_stem");

        // The Endergetic Expansion
//        addOptional("endergetic:stripped_poise_stem", "_side", "endergetic:block/poise_stem_stripped");
//        addOptional("endergetic:stripped_poise_stem", "_top", "endergetic:block/poise_stem_stripped_top");
    }

    private static void addOptional(String blockId, String textureId, String texturePath) {
        Registry.BLOCK.getOptional(new ResourceLocation(blockId))
                .ifPresent(b -> TextureCache.registerSpecialTextureForBlock(b, textureId, new ResourceLocation(texturePath)));
    }
}

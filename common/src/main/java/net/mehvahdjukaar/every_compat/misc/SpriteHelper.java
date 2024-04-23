package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.client.TextureCache;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.function.Predicate;

// Used to identify textures "types" only based off their name.
// feed into "findFirstBlockTextureLocation()"
public class SpriteHelper {

    public static final @NotNull Predicate<String> LOOKS_LIKE_TOP_LOG_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        if (s.contains("_overlay")) return false;
        return s.contains("_top") || s.contains("_end") || s.contains("_up");
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_SIDE_LOG_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return (!LOOKS_LIKE_TOP_LOG_TEXTURE.test(s) && !(s.contains("_overlay") && !(s.contains("_leaves"))));
    };
    public static final @NotNull Predicate<String> LOOKS_LIKE_LEAF_TEXTURE = (s) -> {
        s = (new ResourceLocation(s)).getPath();
        return !s.contains("_bushy") && !s.contains("_snow") && !s.contains("_overlay") && !s.contains("/snow");
    };

    public static void addHardcodedSprites() {
        // Minecraft
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log", EveryCompat.res("block/cactus_side"));
        TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "cactus_log_top", EveryCompat.res("block/cactus_top"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log", res("block/stripped_cactus_side"));
//            TextureCache.registerSpecialTextureForBlock(Blocks.CACTUS, "stripped_cactus_log_top", res("block/stripped_cactus_top"));

        // The Twilight Forest
            // Leaves
        addOptional("twilightforest:beanstalk_leaves", "_leaves", "minecraft:block/azalea_leaves");
        addOptional("twilightforest:thorn_leaves", "_leaves", "minecraft:block/oak_leaves");

        // Regions Unexplored
        addOptional("regions_unexplored:eucalyptus_log", "_side", EveryCompat.MOD_ID+":block/regions_unexplored/eucalyptus_log");
            // Leaves
        addOptional("regions_unexplored:alpha_leaves", "_leaves", "regions_unexplored:block/alpha_oak_leaves");
        addOptional("regions_unexplored:apple_oak_leaves", "_leaves", "regions_unexplored:block/apple_oak_leaves_stage_0");
        addOptional("regions_unexplored:flowering_leaves", "_leaves", "regions_unexplored:item/flowering_leaves");
        addOptional("regions_unexplored:palm_leaves", "_leaves", "regions_unexplored:block/palm_leaves_side");
        addOptional("regions_unexplored:enchanted_birch_leaves", "_leaves", "regions_unexplored:item/enchanted_birch_leaves");
        addOptional("regions_unexplored:silver_birch_leaves", "_leaves", "regions_unexplored:item/silver_birch_leaves");
        addOptional("regions_unexplored:small_oak_leaves", "_leaves", "minecraft:block/oak_leaves");

        // Endless Biomes
        addOptional("endlessbiomes:twisted_stem", "_side", "endlessbiomes:block/twistedlogsidetest");
        addOptional("endlessbiomes:twisted_stem", "_top", "endlessbiomes:block/twistedlogtoptest");
        addOptional("endlessbiomes:stripped_twisted_stem", "_side", "endlessbiomes:block/twistedstrippedlogsidetest");
        addOptional("endlessbiomes:stripped_twisted_stem", "_top", "endlessbiomes:block/twistedstrippedlogtoptest");

        addOptional("endlessbiomes:penumbra_stem", "_side", "endlessbiomes:block/penumbrallogsidenewest");
        addOptional("endlessbiomes:penumbra_stem", "_top", "endlessbiomes:block/penumbrallogtopnewest");
        addOptional("endlessbiomes:stripped_penumbra_stem", "_side", "endlessbiomes:block/strippedpenumbralogsidenewest");
        addOptional("endlessbiomes:stripped_penumbra_stem", "_top", "endlessbiomes:block/strippedpenumbralogtopnewest");

        // Gardens Of The Dead
        addOptional("gardens_of_the_dead:whistlecane", "_side", "gardens_of_the_dead:block/whistlecane_block");
        addOptional("gardens_of_the_dead:whistlecane", "_top", "gardens_of_the_dead:block/whistlecane_block_top");
        addOptional("gardens_of_the_dead:stripped_soulblight_stem", "_side", "gardens_of_the_dead:block/stripped_soulblight_stem");
        addOptional("gardens_of_the_dead:stripped_soulblight_stem", "_top", "gardens_of_the_dead:block/stripped_soulblight_stem_top");

        // L_Ender 's Cataclysm
        addOptional("cataclysm:chorus_stem", "_side", "cataclysm:block/chorus_stem");
        addOptional("cataclysm:chorus_stem", "_top", "cataclysm:block/chorus_stem");

        // PFW Aesthetic Gems
        addOptional("pfw_aesthetic_gems:ice_blue_topaz_log", "_side", "pfw_aesthetic_gems:block/ice_blue_topaz_log_round");
//        addOptional("pfw_aesthetic_gems:ice_blue_topaz_log", "_top", "pfw_aesthetic_gems:block/ice_blue_topaz_log_top");
        addOptional("pfw_aesthetic_gems:ice_blue_topaz_stripped_log", "_side", "pfw_aesthetic_gems:block/ice_blue_topaz_stripped_log_round");
//        addOptional("pfw_aesthetic_gems:ice_blue_topaz_stripped_log", "_top", "pfw_aesthetic_gems:block/ice_blue_topaz_stripped_log_top");

        addOptional("pfw_aesthetic_gems:pink_topaz_log", "_side", "pfw_aesthetic_gems:block/pink_topaz_log_round");
//        addOptional("pfw_aesthetic_gems:pink_topaz_log", "_top", "pfw_aesthetic_gems:block/pink_topaz_log_top");
        addOptional("pfw_aesthetic_gems:pink_topaz_stripped_log", "_side", "pfw_aesthetic_gems:block/pink_topaz_stripped_log_round");
//        addOptional("pfw_aesthetic_gems:pink_topaz_stripped_log", "_top", "pfw_aesthetic_gems:block/pink_topaz_stripped_log_top");

    }

    private static void addOptional(String blockId, String textureId, String texturePath) {
        BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(blockId))
                .ifPresent(b -> TextureCache.registerSpecialTextureForBlock(b, textureId, new ResourceLocation(texturePath)));
    }

}

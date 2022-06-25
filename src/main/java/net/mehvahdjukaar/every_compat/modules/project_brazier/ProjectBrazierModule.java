package net.mehvahdjukaar.every_compat.modules.project_brazier;

import net.dark_roleplay.marg.api.materials.MaterialRegistry;
import net.dark_roleplay.marg.client.generators.textures.generator.TextureGenerator;
import net.dark_roleplay.marg.client.listeners.TextureProcessorsReloadListener;
import net.dark_roleplay.marg.common.material.MargMaterial;
import net.dark_roleplay.marg.common.material.MaterialCondition;
import net.dark_roleplay.marg.common.material.MaterialProperties;
import net.dark_roleplay.projectbrazier.feature.blocks.templates.HAxisDecoBlock;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierBlocks;
import net.dark_roleplay.projectbrazier.feature.registrars.BrazierCreativeTabs;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.modules.quark.CompatChestBlock;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.block_set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;


public class ProjectBrazierModule extends SimpleModule {

    public static Map<ResourceLocation, TextureGenerator> generators;
    private static List<MargMaterial> margMaterials = new ArrayList<>();
    private boolean initialized = false;

    public final SimpleEntrySet<WoodType, Block> FIREWOODS;

    public ProjectBrazierModule(String modId) {
        super(modId, "pb");

        FIREWOODS = SimpleEntrySet.builder(WoodType.class, "firewood",
                        () -> this.getModBlock("oak_firewood"), () -> WoodType.OAK_WOOD_TYPE,
                        ifCond(() -> BrazierBlocks.FIREWOOD_CON, w ->
                                new HAxisDecoBlock(WoodGood.copySafe(w.planks)
                                        .strength(2.0F, 3.0F)
                                        .noOcclusion(),
                                        "full_block")))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setTab(BrazierCreativeTabs::decor)
                //.addModelTransform(w -> w.replaceString("everycompat:textures", "projectbrazier:textures"))
                .defaultRecipe()
                .build();


        this.addEntry(FIREWOODS);
    }

    public static void generateStuff(Map<ResourceLocation, TextureGenerator> gen, ResourceManager manager) {
        generators = gen;

        if(margMaterials.isEmpty()){
            WoodTypeRegistry.WOOD_TYPES.forEach((r,wood) -> {
                if (!wood.isVanilla() && !wood.getNamespace().equals("projectbrazier")) {
                    margMaterials.add(makeWoodMaterial(wood, manager));
                }
            });
        }
        try {
            generators.forEach((r,g)-> margMaterials.forEach(g::generate));
        }catch (Exception ignored){};
    }

    public <T extends BlockType, B extends Block> Function<T, @Nullable B> ifCond(Supplier<MaterialCondition> cond, Function<T, B> supplier) {
        return w -> {
            var t = cond.get().getTextures();
            for (var v : t) {
                if (w.getChild(v) == null) return null;
            }
            return supplier.apply(w);
        };
    }


    /*
    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);


        try (TextureImage mask = TextureImage.open(manager, modRes("generator/masks/log_inside"))
        ) {

            FIREWOODS.blocks.forEach((wood, block) -> {

                CompatChestBlock b = (CompatChestBlock) block;

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.log));
                     TextureImage strippedTexture = TextureImage.open(manager,
                             RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("stripped_log")))) {

                    List<Palette> targetPalette = Palette.fromAnimatedImage(plankTexture);


                    ResourceLocation res = modRes(b.getChestTexturePath() + "normal");
                    if (!handler.alreadyHasTextureAtLocation(manager, res)) {
                        ResourceLocation trappedRes = modRes(b.getChestTexturePath() + "trap");

                        var img = respriterNormal.recolorWithAnimation(targetPalette, plankTexture.getMetadata());
                        img.applyOverlayOnExisting(respriterNormalO.recolorWithAnimation(overlayPalette, plankTexture.getMetadata()));

                        var trapped = img.makeCopy();

                        trapped.applyOverlayOnExisting(normal_t.makeCopy());

                        handler.dynamicPack.addAndCloseTexture(res, img);
                        handler.dynamicPack.addAndCloseTexture(trappedRes, trapped);
                    }


                } catch (Exception ex) {
                    handler.getLogger().error("Failed to generate Chest block texture for for {} : {}", b, ex);
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Chest block texture : ", ex);
        }
    }
    */

    public static MargMaterial makeWoodMaterial(WoodType wood, ResourceManager manager) {

        Map<String, String> textures = new HashMap<>();
        if(manager != null){
        try {
            var stripped = RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("stripped_log"), SpriteUtils::looksLikeSideLogTexture);
            textures.put("stripped_log", stripped.toString());
        } catch (Exception ignored) {
        }
        try {
            var stripped = RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("stripped_log"), SpriteUtils::looksLikeTopLogTexture);
            textures.put("stripped_log_top", stripped.toString());
        } catch (Exception ignored) {
        }
        try {
            var stripped = RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("log"), SpriteUtils::looksLikeSideLogTexture);
            textures.put("log", stripped.toString());
        } catch (Exception ignored) {
        }
        try {
            var stripped = RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("log"), SpriteUtils::looksLikeTopLogTexture);
            textures.put("log_top", stripped.toString());
        } catch (Exception ignored) {
        }
        try {
            var stripped = RPUtils.findFirstBlockTextureLocation(manager, wood.getBlockOfThis("planks"));
            textures.put("planks", stripped.toString());
        } catch (Exception ignored) {
        }}

        var mat = wood.material;
        var base = wood.getBlockOfThis("planks");

        Map<String, String> items = new HashMap<>();
        Map<String, String> blocks = new HashMap<>();
        wood.getChildren().forEach(e -> {
            if (e.getValue() instanceof Block b) {
                blocks.put(e.getKey(), b.getRegistryName().toString());
            } else {
                items.put(e.getKey(), e.getValue().asItem().getRegistryName().toString());
            }
        });

        MaterialProperties prop = new MaterialProperties(1f, 1f, 0,
                base.getFriction(), 0, List.of("axe"), mat.getColor());
        return new MargMaterial(
                "wood",
                wood.getAppendableId(),
                WoodGood.MOD_ID,
                prop,
                textures,
                blocks, items);
    }
}

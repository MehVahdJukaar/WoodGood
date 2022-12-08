package net.mehvahdjukaar.every_compat.modules.mcaw;

import com.mcwlights.kikoz.MacawsLights;
import com.mcwlights.kikoz.init.BlockInit;
import com.mcwlights.kikoz.objects.TikiTorch;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.Utils;
import net.mehvahdjukaar.every_compat.modules.quark.CompatChestBlock;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Palette;
import net.mehvahdjukaar.selene.client.asset_generators.textures.Respriter;
import net.mehvahdjukaar.selene.client.asset_generators.textures.SpriteUtils;
import net.mehvahdjukaar.selene.client.asset_generators.textures.TextureImage;
import net.mehvahdjukaar.selene.math.colors.HCLColor;
import net.mehvahdjukaar.selene.resourcepack.RPUtils;
import net.mehvahdjukaar.selene.resourcepack.ResType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.crafting.conditions.ICondition;

import java.util.ArrayList;
import java.util.List;


public class MacawLightsModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> SOUL_TIKI_TORCHES;
    public final SimpleEntrySet<WoodType, Block> TIKI_TORCHES;

    public MacawLightsModule(String modId) {
        super(modId, "mcl");

        CreativeModeTab tab = null;

        SOUL_TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch", "soul",
                        BlockInit.SOUL_OAK_TIKI_TORCH, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TikiTorch(WoodGood.copySafe(w.planks).strength(0.2f, 2.5f), ParticleTypes.SOUL_FIRE_FLAME))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(() -> tab)
                .build();

        this.addEntry(SOUL_TIKI_TORCHES);

        TIKI_TORCHES = SimpleEntrySet.builder(WoodType.class, "tiki_torch",
                        BlockInit.OAK_TIKI_TORCH, () -> WoodType.OAK_WOOD_TYPE,
                        w -> new TikiTorch(WoodGood.copySafe(w.planks).strength(0.2f, 2.5f), ParticleTypes.FLAME))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .setTab(() -> tab)
                .build();

        this.addEntry(TIKI_TORCHES);
    }


    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);


        try (TextureImage mask = TextureImage.open(manager, WoodGood.res("item/tiki_torch_mask"));
             TextureImage overlay_soul = TextureImage.open(manager, WoodGood.res("item/tiki_torch_overlay"));
             TextureImage overlay = TextureImage.open(manager, WoodGood.res("item/tiki_torch_soul_overlay"))
        ) {


            TIKI_TORCHES.blocks.forEach((wood, block) -> {
                var id = block.getRegistryName();

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteUtils::looksLikeSideLogTexture))) {

                    var t = mask.makeCopy();
                    t.applyOverlayOnExisting(logTexture.makeCopy(),overlay.makeCopy());

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation(id.getNamespace(),
                            "item/"+ id.getPath().replace("_torch","")), t);

                } catch (Exception ignored) {
                }
            });
            SOUL_TIKI_TORCHES.blocks.forEach((wood, block) -> {
                var id = block.getRegistryName();

                try (TextureImage logTexture = TextureImage.open(manager,
                        RPUtils.findFirstBlockTextureLocation(manager, wood.log, SpriteUtils::looksLikeSideLogTexture))) {

                    var t = mask.makeCopy();
                    t.applyOverlayOnExisting(logTexture.makeCopy(),overlay_soul.makeCopy());

                    handler.dynamicPack.addAndCloseTexture(new ResourceLocation(id.getNamespace(),
                            "item/"+ id.getPath().replace("_torch","")), t);

                } catch (Exception ignored) {
                }
            });
        } catch (Exception ex) {
            handler.getLogger().error("Could not generate any Tiki torch item texture : ", ex);
        }
    }
}

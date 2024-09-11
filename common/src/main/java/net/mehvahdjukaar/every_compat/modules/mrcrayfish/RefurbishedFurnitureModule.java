package net.mehvahdjukaar.every_compat.modules.mrcrayfish;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mrcrayfish.furniture.refurbished.FurnitureMod;
import com.mrcrayfish.furniture.refurbished.block.*;
import com.mrcrayfish.furniture.refurbished.client.renderer.blockentity.CeilingFanBlockEntityRenderer;
import com.mrcrayfish.furniture.refurbished.crafting.StackedIngredient;
import com.mrcrayfish.furniture.refurbished.crafting.WorkbenchContructingRecipe;
import com.mrcrayfish.furniture.refurbished.item.MailboxItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.platform.ClientPlatformHelper;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesType;
import net.mehvahdjukaar.moonlight.api.set.leaves.LeavesTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.critereon.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mrcrayfish.furniture.refurbished.core.ModBlockEntities.*;

//SUPPORT: v1.0.6+
public class RefurbishedFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> darkFans;
    public final SimpleEntrySet<WoodType, Block> lightFans;
    public final SimpleEntrySet<WoodType, Block> toilets;
    public final SimpleEntrySet<WoodType, Block> crates;
    public final SimpleEntrySet<WoodType, Block> mailboxes;
    public final SimpleEntrySet<WoodType, Block> jars;
    public final SimpleEntrySet<WoodType, Block> kitchen_cabinetry;
    public final SimpleEntrySet<WoodType, Block> kitchen_drawer;
    public final SimpleEntrySet<WoodType, Block> kitchen_sink;
    public final SimpleEntrySet<WoodType, Block> kitchen_storage_cabinet;
    public final SimpleEntrySet<WoodType, Block> storage_cabinet;
    public final SimpleEntrySet<WoodType, Block> basin;
    public final SimpleEntrySet<WoodType, Block> bath;
    public final SimpleEntrySet<WoodType, Block> lattice_fence;
    public final SimpleEntrySet<WoodType, Block> lattice_fence_gate;
    public final SimpleEntrySet<WoodType, Block> desk;
    public final SimpleEntrySet<WoodType, Block> cutting_board;
    public final SimpleEntrySet<WoodType, Block> drawer;
    public final SimpleEntrySet<LeavesType, Block> hedges;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");

        TemplateRecipeManager.registerTemplate(modRes("workbench_constructing"), ConstructingTemplate::new);

        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_chair"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTexture(modRes("block/oak_chair"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("general"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(chairs);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_table"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_particle"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("tuckable"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("general"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(tables);

        darkFans = SimpleEntrySet.builder(WoodType.class, "dark_ceiling_fan",
                        () -> getModBlock("oak_dark_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanilla(),
                                MetalType.DARK,
                                Utils.copyPropertySafe(w.planks)
                                        .strength(0.8F).sound(SoundType.WOOD).lightLevel(CeilingFanBlock::light))
                )
                .defaultRecipe()
                .addRecipe(modRes("constructing/oak_dark_ceiling_fan"))
                .addTile(CEILING_FAN::get)
//                .setTab()
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("electronics"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bedroom"), Registry.ITEM_REGISTRY)
                .addTextureM(modRes("block/oak_dark_ceiling_fan"),
                        EveryCompat.res("block/rfm/oak_ceiling_fan_m"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(darkFans);

        lightFans = SimpleEntrySet.builder(WoodType.class, "light_ceiling_fan",
                        () -> getModBlock("oak_light_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanilla(),
                                MetalType.DARK,
                                Utils.copyPropertySafe(w.planks)
                                        .strength(0.8F).sound(SoundType.WOOD).lightLevel(CeilingFanBlock::light))
                )
                .addRecipe(modRes("constructing/oak_light_ceiling_fan"))
                .addTile(CEILING_FAN::get)
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("electronics"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bedroom"), Registry.ITEM_REGISTRY)
                .addTextureM(modRes("block/oak_light_ceiling_fan"),
                        EveryCompat.res("block/rfm/oak_ceiling_fan_m"))
                .setRenderType(() -> RenderType::translucent)
                .build();
        this.addEntry(lightFans);


        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        () -> getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_crate"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(CRATE::get)
                .addTexture(modRes("block/oak_crate"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .addTag(modRes("outdoors"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(crates);

        mailboxes = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        () -> getModBlock("oak_mail_box"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailboxBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_mail_box"))
                .addCustomItem((woodType, block, properties) -> new MailboxItem(block, properties))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(MAIL_BOX::get)
                .addTextureM(modRes("block/oak_mail_box"),
                        EveryCompat.res("block/rfm/oak_mail_box_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("outdoors"), Registry.ITEM_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(mailboxes);

        toilets = SimpleEntrySet.builder(WoodType.class, "toilet",
                        () -> getModBlock("oak_toilet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenToiletBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(3.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_toilet"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(TOILET::get)
                .addTextureM(modRes("block/oak_toilet"),
                        EveryCompat.res("block/rfm/oak_toilet_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_toilets"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bathroom"), Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(toilets);

        jars = SimpleEntrySet.builder(WoodType.class, "storage_jar",
                        () -> getModBlock("oak_storage_jar"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageJarBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(1.0F).sound(SoundType.GLASS))
                )
                .addRecipe(modRes("constructing/oak_storage_jar"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(STORAGE_JAR::get)
                .addTextureM(modRes("block/oak_storage_jar"),
                        EveryCompat.res("block/rfm/oak_storage_jar_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(jars);

        kitchen_cabinetry = SimpleEntrySet.builder(WoodType.class, "kitchen_cabinetry",
                        () -> getModBlock("oak_kitchen_cabinetry"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenKitchenCabinetryBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_kitchen_cabinetry"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
//                .addTile(ModBlockEntities.STORAGE_CABINET::get)
                .addTextureM(modRes("block/oak_kitchen_cabinetry"),
                        EveryCompat.res("block/rfm/oak_kitchen_cabinetry_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_kitchen_cabinetry"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(kitchen_cabinetry);

        kitchen_drawer = SimpleEntrySet.builder(WoodType.class, "kitchen_drawer",
                        () -> getModBlock("oak_kitchen_drawer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenKitchenDrawerBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_kitchen_drawer"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(KITCHEN_DRAWER::get)
                .addTextureM(modRes("block/oak_kitchen_drawer"),
                        EveryCompat.res("block/rfm/oak_kitchen_drawer_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .addTag(modRes("wooden_kitchen_drawers"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(kitchen_drawer);

        kitchen_sink = SimpleEntrySet.builder(WoodType.class, "kitchen_sink",
                        () -> getModBlock("oak_kitchen_sink"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenKitchenSinkBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_kitchen_sink"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(KITCHEN_SINK::get)
                .addTextureM(modRes("block/oak_kitchen_sink"),
                        EveryCompat.res("block/rfm/oak_kitchen_sink_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_kitchen_sinks"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(kitchen_sink);

        kitchen_storage_cabinet = SimpleEntrySet.builder(WoodType.class, "kitchen_storage_cabinet",
                        () -> getModBlock("oak_kitchen_storage_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenKitchenStorageCabinetBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_kitchen_storage_cabinet"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(STORAGE_CABINET::get)
                .addTextureM(modRes("block/oak_kitchen_storage_cabinet"),
                        EveryCompat.res("block/rfm/oak_kitchen_storage_cabinet_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_kitchen_storage_cabinets"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(kitchen_storage_cabinet);

        storage_cabinet = SimpleEntrySet.builder(WoodType.class, "storage_cabinet",
                        () -> getModBlock("oak_storage_cabinet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenStorageCabinetBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_storage_cabinet"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(STORAGE_CABINET::get)
                .addTextureM(modRes("block/oak_storage_cabinet"),
                        EveryCompat.res("block/rfm/oak_storage_cabinet_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("general"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bedroom"), Registry.ITEM_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(storage_cabinet);

        basin = SimpleEntrySet.builder(WoodType.class, "basin",
                        () -> getModBlock("oak_basin"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenBasinBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(3.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_basin"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(BASIN::get)
                .addTextureM(modRes("block/oak_basin"),
                        EveryCompat.res("block/rfm/oak_basin_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("wooden_basins"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bathroom"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(basin);

        bath = SimpleEntrySet.builder(WoodType.class, "bath",
                        () -> getModBlock("oak_bath"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new WoodenBathBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(3.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_bath"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(BATH::get)
                .addTextureM(modRes("block/oak_bath"),
                        EveryCompat.res("block/rfm/oak_bath_m"))
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("bathroom"), Registry.ITEM_REGISTRY)
                .addTag(modRes("wooden_baths"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(bath);

        lattice_fence = SimpleEntrySet.builder(WoodType.class, "lattice_fence",
                        () -> getModBlock("oak_lattice_fence"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LatticeFenceBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_lattice_fence"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(STORAGE_JAR::get)
                .addTexture(modRes("block/oak_lattice_fence"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.FENCES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.WOODEN_FENCES, Registry.BLOCK_REGISTRY)
                .addTag(modRes("outdoors"), Registry.ITEM_REGISTRY)
                .addTag(BlockTags.FENCES, Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(lattice_fence);

        lattice_fence_gate = SimpleEntrySet.builder(WoodType.class, "lattice_fence_gate",
                        () -> getModBlock("oak_lattice_fence_gate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new LatticeFenceGateBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))                )
                .addRecipe(modRes("constructing/oak_lattice_fence_gate"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(STORAGE_JAR::get)
                .addTextureM(modRes("block/oak_lattice_fence_gate"),
                        EveryCompat.res("block/rfm/oak_lattice_fence_gate_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.FENCE_GATES, Registry.BLOCK_REGISTRY)
                .addTag(BlockTags.UNSTABLE_BOTTOM_CENTER, Registry.BLOCK_REGISTRY)
                .addTag(modRes("outdoors"), Registry.ITEM_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(lattice_fence_gate);

        desk = SimpleEntrySet.builder(WoodType.class, "desk",
                        () -> getModBlock("oak_desk"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DeskBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.0f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_desk"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTexture(modRes("block/oak_desk"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("tuckable"), Registry.BLOCK_REGISTRY)
                .addTag(modRes("general"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bedroom"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(desk);

        cutting_board = SimpleEntrySet.builder(WoodType.class, "cutting_board",
                        () -> getModBlock("oak_cutting_board"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CuttingBoardBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(1.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_cutting_board"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(CUTTING_BOARD::get)
                .addTexture(modRes("block/oak_cutting_board"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("kitchen"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(cutting_board);

        drawer = SimpleEntrySet.builder(WoodType.class, "drawer",
                        () -> getModBlock("oak_drawer"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new DrawerBlock(w.toVanilla(),
                                Utils.copyPropertySafe(w.planks).strength(2.5f).sound(SoundType.WOOD))
                )
                .addRecipe(modRes("constructing/oak_drawer"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(DRAWER::get)
                .addTexture(modRes("block/oak_drawer"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .addTag(modRes("storage"), Registry.ITEM_REGISTRY)
                .addTag(modRes("bedroom"), Registry.ITEM_REGISTRY)
                .addTag(modRes("general"), Registry.ITEM_REGISTRY)
                .build();
        this.addEntry(drawer);

        hedges = SimpleEntrySet.builder(LeavesType.class, "hedge",
                        () -> getModBlock("oak_hedge"), () -> LeavesTypeRegistry.OAK_TYPE,
                        l -> new HedgeBlock(LeafType.OAK,
                                BlockBehaviour.Properties.of(Material.LEAVES, l.leaves.defaultMaterialColor())
                                        .strength(0.5f).sound(SoundType.AZALEA_LEAVES))
                )
                .requiresChildren("leaves") // Textures
                .addModelTransform(m -> m.replaceWithTextureFromChild("minecraft:block/oak_leaves",
                        "leaves", SpriteHelper.LOOKS_LIKE_LEAF_TEXTURE))
                .addRecipe(modRes("constructing/oak_hedge"))
//              .setTab() is not needed blc FORGE & FABRIC have different name for CreativeModeTab
                .addTile(DRAWER::get)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registry.BLOCK_REGISTRY)
                .setRenderType(() -> RenderType::cutout)
                .build();
        this.addEntry(hedges);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        // code copied from ResourceUtils.addStandardResources
        StaticResource darkBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                modRes("extra/oak_dark_ceiling_fan_blade")
        ));
        addFanModels(handler, manager, darkBlade, darkFans);
        StaticResource lightBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                modRes("extra/oak_light_ceiling_fan_blade")
        ));

        addFanModels(handler, manager, lightBlade, lightFans);

    }

    private void addFanModels(ClientDynamicResourcesHandler handler, ResourceManager manager, StaticResource darkBlade, SimpleEntrySet<WoodType, Block> darkFans) {
        darkFans.blocks.forEach((w, b) -> {
            try {
                handler.addSimilarJsonResource(manager, darkBlade, s ->
                        s.replace("oak", w.getAppendableId())
                                .replace("texture\": \"refurbished_furniture:block", "texture\": \"everycomp:block/rfm"));

            } catch (Exception exception) {
                EveryCompat.LOGGER.error("Failed to add {} model json file:", b, exception);
            }
        });
    }

    @Override
    public void onClientSetup() {
        super.onClientSetup();
        darkFans.blocks.forEach((key, value) -> {
            ResourceLocation res = EveryCompat.res("extra/" + key.getAppendableId() + "_dark_ceiling_fan_blade");
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> ClientPlatformHelper.getModel(Minecraft.getInstance().getModelManager(), res));
        });

        lightFans.blocks.forEach((key, value) -> {
            ResourceLocation res = EveryCompat.res("extra/" + key.getAppendableId() + "_light_ceiling_fan_blade");
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> ClientPlatformHelper.getModel(Minecraft.getInstance().getModelManager(), res));
        });
    }
    @Override
    public void onClientInit() {
        super.onClientInit();
        ClientPlatformHelper.addSpecialModelRegistration(event -> darkFans.blocks.keySet().forEach(w -> {
            event.register(EveryCompat.res("extra/" + w.getAppendableId() + "_dark_ceiling_fan_blade"));
            event.register(EveryCompat.res("extra/" + w.getAppendableId() + "_light_ceiling_fan_blade"));
        }));
    }

    public class ConstructingTemplate implements IRecipeTemplate<WorkbenchContructingRecipe.Result> {

        private final List<Object> conditions = new ArrayList<>();

        public final ItemStack result;
        public final NonNullList<StackedIngredient> materials;
        private final boolean notification;

        public ConstructingTemplate(JsonObject json) {

            JsonArray materialArray = GsonHelper.getAsJsonArray(json, "materials");
            this.materials = NonNullList.withSize(materialArray.size(), StackedIngredient.EMPTY);
            IntStream.range(0, materialArray.size()).forEach((i) -> materials.set(i, StackedIngredient.fromJson(materialArray.get(i))));
            String s1;
            int count;
            if (json.get("result").isJsonObject()) {
                s1 = GsonHelper.getAsJsonObject(json, "result").get("item").getAsString();
                count = GsonHelper.getAsJsonObject(json, "result").get("count").getAsInt();
            }
            else {
                s1 = GsonHelper.getAsString(json, "result");
                count = 1;
            }

            this.result = new ItemStack(Registry.ITEM.get(new ResourceLocation(s1)), count);
            this.notification = GsonHelper.getAsBoolean(json, "show_notification", true);
        }

        @Override
        public <T extends BlockType> WorkbenchContructingRecipe.Result createSimilar(
                T originalMat, T destinationMat, Item unlockItem, String id) {

            ItemLike newRes = BlockType.changeItemType(this.result.getItem(), originalMat, destinationMat);
            if (newRes == null) {
                throw new UnsupportedOperationException(String.format("Could not convert output item %s from type %s to %s",
                        this.result, originalMat, destinationMat));
            }
            ItemStack newResult = new ItemStack(newRes);
            if (this.result.hasTag()) newResult.setTag(this.result.getOrCreateTag().copy());
            if (id == null) id = Registry.ITEM.getKey(newRes.asItem()).toString();
            List<StackedIngredient> newMaterials = new ArrayList<>();
            for (StackedIngredient ing : this.materials) {
                Ingredient converted = ResourcesUtils.convertIngredient(ing.ingredient(), originalMat, destinationMat);
                newMaterials.add(new StackedIngredient(converted, ing.count()));
            }

            Advancement.Builder advancement = Advancement.Builder.advancement();

            List<String> requirements = new ArrayList<>();
            for (var m : newMaterials) {
                String name = "has_" + m.ingredient().getItems()[0].getItem();
                requirements.add(name);
                var items = Arrays.stream(m.ingredient().getItems()).map(ItemStack::getItem).collect(Collectors.toSet());
                advancement.addCriterion(name, InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));
            }

            requirements.add("has_the_recipe");

            var res = new ResourceLocation(id);

            advancement.requirements(new String[][]{requirements.toArray(new String[0])});
            advancement.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(res));

            advancement.rewards(AdvancementRewards.Builder.recipe(EveryCompat.res("recipes/" + res.getPath())));

            return new WorkbenchContructingRecipe.Result(res, newResult.getItem(), result.getCount(), newMaterials, advancement,
                    modRes("recipes/misc/constructing/" + res.getPath()), notification);
        }

        @Override
        public void addCondition(Object condition) {
            this.conditions.add(condition);
        }

        @Override
        public List<Object> getConditions() {
            return conditions;
        }
    }
}

package net.mehvahdjukaar.every_compat.modules.more_beautiful_torches;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.*;


//SUPPORT: v3.0.0+
public class MoreBeautifulTorches extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> log_wall_torch,
                                                 log_torch;
    public final SimpleEntrySet<WoodType, Block> log_soul_wall_torch,
                                                 log_soul_torch;
    public final SimpleEntrySet<WoodType, Block> log_redstone_wall_torch,
                                                 log_redstone_torch;

    public final SimpleEntrySet<WoodType, Block> planks_wall_torch,
                                                 planks_torch;
    public final SimpleEntrySet<WoodType, Block> planks_soul_wall_torch,
                                                 planks_soul_torch;
    public final SimpleEntrySet<WoodType, Block> planks_redstone_wall_torch,
                                                 planks_redstone_torch;

    public final SimpleEntrySet<WoodType, Block> stripped_wall_torch,
                                                 stripped_torch;
    public final SimpleEntrySet<WoodType, Block> stripped_soul_wall_torch,
                                                 stripped_soul_torch;
    public final SimpleEntrySet<WoodType, Block> stripped_redstone_wall_torch,
                                                 stripped_redstone_torch;


    public MoreBeautifulTorches(String modId) {
        super(modId, "mbt");
        ResourceLocation tab = (PlatHelper.Platform.FABRIC.isFabric())
                ? modRes("goldenfoods_tab")
                : modRes("morebeautifultorches_tab");

        log_wall_torch = SimpleEntrySet.builder(WoodType.class, "wall_torch",
                        getModBlock("stone_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        stoneType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME)
                )
                .addTextureM(modRes("block/oak_log_torch"), EveryCompat.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(log_wall_torch);

        log_torch = SimpleEntrySet.builder(WoodType.class, "log_torch",
                        getModBlock("oak_log_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH).noCollission().instabreak().lightLevel(l -> 14), ParticleTypes.FLAME)
                )
                //TEXTURES: wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, log_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(log_torch);

        log_soul_wall_torch = SimpleEntrySet.builder(WoodType.class, "log_soul_wall_torch",
                        getModBlock("oak_log_soul_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .setRenderType(RenderLayer.CUTOUT)
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .addTextureM(modRes("block/oak_log_soul_torch"), EveryCompat.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .build();
        this.addEntry(log_soul_wall_torch);

        log_soul_torch = SimpleEntrySet.builder(WoodType.class, "log_soul_torch",
                        getModBlock("oak_log_soul_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, log_soul_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(log_soul_torch);

        log_redstone_wall_torch = SimpleEntrySet.builder(WoodType.class, "log_redstone_wall_torch",
                        getModBlock("oak_log_redstone_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/oak_log_redstone_torch"), EveryCompat.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/oak_log_redstone_torch_off"), EveryCompat.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(log_redstone_wall_torch);

        log_redstone_torch = SimpleEntrySet.builder(WoodType.class, "log_redstone_torch",
                        getModBlock("oak_log_redstone_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, log_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(log_redstone_torch);

//!!--PLANKS--
        planks_wall_torch = SimpleEntrySet.builder(WoodType.class, "planks_wall_torch",
                        getModBlock("oak_planks_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH), ParticleTypes.FLAME)
                )
                .addTextureM(modRes("block/oak_planks_torch"), EveryCompat.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_wall_torch);

        planks_torch = SimpleEntrySet.builder(WoodType.class, "planks_torch",
                        getModBlock("oak_planks_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH), ParticleTypes.FLAME)
                )
                //TEXTURES: planks_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, planks_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_torch);

        planks_soul_wall_torch = SimpleEntrySet.builder(WoodType.class, "planks_soul_wall_torch",
                        getModBlock("oak_planks_soul_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .addTextureM(modRes("block/oak_planks_soul_torch"), EveryCompat.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_soul_wall_torch);

        planks_soul_torch = SimpleEntrySet.builder(WoodType.class, "planks_soul_torch",
                        getModBlock("oak_planks_soul_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: planks_soul_wall_torch
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, planks_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_soul_torch);

        planks_redstone_wall_torch = SimpleEntrySet.builder(WoodType.class, "planks_redstone_wall_torch",
                        getModBlock("oak_planks_redstone_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .addTextureM(modRes("block/oak_planks_redstone_torch"), EveryCompat.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/oak_planks_redstone_torch_off"), EveryCompat.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_redstone_wall_torch);

        planks_redstone_torch = SimpleEntrySet.builder(WoodType.class, "planks_redstone_torch",
                        getModBlock("oak_planks_redstone_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: planks_redstone_wall_torch
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, planks_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(planks_redstone_torch);

//!!--STRIPPED-LOG--
        stripped_wall_torch = SimpleEntrySet.builder(WoodType.class, "log_wall_torch", "stripped",
                        getModBlock("stripped_oak_log_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.WALL_TORCH), ParticleTypes.FLAME)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .createPaletteFromChild("stripped_log")
                .addTextureM(modRes("block/stripped_oak_log_torch"), EveryCompat.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_wall_torch);

        stripped_torch = SimpleEntrySet.builder(WoodType.class, "log_torch", "stripped",
                        getModBlock("stripped_oak_log_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.TORCH), ParticleTypes.FLAME)
                )
                //TEXTURES: stripped_wall_torch
                .requiresChildren("stripped_log") //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, stripped_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_torch);

        stripped_soul_wall_torch = SimpleEntrySet.builder(WoodType.class, "log_soul_wall_torch", "stripped",
                        getModBlock("stripped_oak_log_soul_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new WallTorchBlock(Utils.copyPropertySafe(Blocks.SOUL_WALL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                .requiresChildren("stripped_log") //REASON: textures
                .createPaletteFromChild("stripped_log")
                .addTextureM(modRes("block/stripped_oak_log_soul_torch"), EveryCompat.res("block/common_torch_m"))
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_soul_wall_torch);

        stripped_soul_torch = SimpleEntrySet.builder(WoodType.class, "log_soul_torch", "stripped",
                        getModBlock("stripped_oak_log_soul_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new TorchBlock(Utils.copyPropertySafe(Blocks.SOUL_TORCH), ParticleTypes.SOUL_FIRE_FLAME)
                )
                //TEXTURES: stripped_soul_wall_torch
                .requiresChildren("stripped_log") //REASON: recipes
                .addTag(new ResourceLocation("dangerclose:torch_burn_danger"), Registries.BLOCK)
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((s, b, p) -> new StandingAndWallBlockItem(b, stripped_soul_wall_torch.blocks.get(s), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_soul_torch);

        stripped_redstone_wall_torch = SimpleEntrySet.builder(WoodType.class, "log_redstone_wall_torch", "stripped",
                        getModBlock("stripped_oak_log_redstone_wall_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneWallTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_WALL_TORCH))
                )
                .requiresChildren("stripped_log") //REASON: textures
                .createPaletteFromChild("stripped_log")
                .addTextureM(modRes("block/stripped_oak_log_redstone_torch"), EveryCompat.res("block/common_redstone_torch_m"))
                .addTextureM(modRes("block/stripped_oak_log_redstone_torch_off"), EveryCompat.res("block/common_torch_m"))
                .noTab()
                .noItem()
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_redstone_wall_torch);

        stripped_redstone_torch = SimpleEntrySet.builder(WoodType.class, "log_redstone_torch", "stripped",
                        getModBlock("stripped_oak_log_redstone_torch"), () -> WoodTypeRegistry.OAK_TYPE,
                        woodType -> new RedstoneTorchBlock(Utils.copyPropertySafe(Blocks.REDSTONE_TORCH))
                )
                //TEXTURES: stripped_redstone_wall_torch
                .requiresChildren("stripped_log") //REASON: recipes
                .setTabKey(tab)
                .defaultRecipe()
                .addCustomItem((w, b, p) -> new StandingAndWallBlockItem(b, stripped_redstone_wall_torch.blocks.get(w), p, Direction.DOWN))
                .setRenderType(RenderLayer.CUTOUT)
                .build();
        this.addEntry(stripped_redstone_torch);

    }
}
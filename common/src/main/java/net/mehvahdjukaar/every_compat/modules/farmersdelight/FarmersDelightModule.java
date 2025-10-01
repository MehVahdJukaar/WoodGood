package net.mehvahdjukaar.every_compat.modules.farmersdelight;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.*;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceSink;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.leaves.VanillaLeavesTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.crafting.CuttingBoardRecipe;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.item.FuelBlockItem;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.api.PaletteStrategies.registerCached;
import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.*;

// SUPPORT: FABRIC-v2.2.7+ | NEOFORGE-v1.2.6+
public class FarmersDelightModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> cabinets;

    public FarmersDelightModule(String modId) {
        super(modId, "fd", EveryCompat.MOD_ID);

        cabinets = SimpleEntrySet.builder(WoodType.class, "cabinet",
                        getModBlock("oak_cabinet"), () -> VanillaWoodTypes.OAK,
                        w -> new CabinetBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresChildren(TRAPDOOR, SLAB) //REASON: recipes
                .addTile(getModTile("cabinet"))
                .addTextureM(modRes("block/oak_cabinet_front"),
                        EveryCompat.res("block/fd/oak_cabinet_front_m"),
                        customPalette)
                .addTexture(modRes("block/oak_cabinet_side"), customPalette)
                .addTexture(modRes("block/oak_cabinet_top"), customPalette)
                .addTexture(modRes("block/oak_cabinet_front_open"), customPalette)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(modRes("cabinets/wooden"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setTabKey(modRes("farmersdelight"))
                .setTabMode(TabAddMode.AFTER_SAME_TYPE)
                .defaultRecipe()
                .addCustomItem((w, block, p) -> new FuelBlockItem(block, ModItems.basicItem(), 300))
                .build();
        this.addEntry(cabinets);
    }

    public static final PaletteStrategy customPalette = registerCached((blockType, manager) -> PaletteStrategies.makePaletteFromChild(
            blockType, manager, PLANKS, null, p -> {
                p.reduceDown();
                if (p.size() < 9) {
                    while (p.size() <= 9) {
                        p.increaseInner();
                    }
                } else {
                    while (p.size() >= 9) {
                        p.reduce();
                    }
                }
            })
    );

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);

        executor.accept((manager, sink) -> {
            // Creating cutting_board recipes
            //why is it iterating over the cabinets??
            cabinets.items.forEach(((woodType, item) -> {
                //adding compat cutting board recipes for vanilla modded stuff i guess
                createCuttingRecipe("door", woodType.getBlockOfThis("door"),
                        woodType, sink, manager);
                createCuttingRecipe("hanging_sign", woodType.getBlockOfThis("hanging_sign"),
                        woodType, sink, manager);
                createCuttingRecipe("sign", woodType.getBlockOfThis("sign"),
                        woodType, sink, manager);
                createCuttingRecipe("trapdoor", woodType.getBlockOfThis("trapdoor"),
                        woodType, sink, manager);
                createCuttingRecipe("log", woodType.getBlockOfThis("stripped_log"),
                        woodType, sink, manager);
                createCuttingRecipe("wood", woodType.getBlockOfThis("wood"),
                        woodType, sink, manager);

            }));
        });
    }

    public void createCuttingRecipe(String recipeType, Block input,
                                    WoodType targetType, ResourceSink sink, ResourceManager manager) {

        if (input == null) return;

        var recipe = RPUtils.readRecipe(manager, "cutting/oak_" + recipeType);
        if (recipe instanceof CuttingBoardRecipe cr) {

            String path = this.shortenedId() + "/cutting/" + targetType.getAppendableId() + "_" + recipeType;

            NonNullList<ChanceResult> oldResult = cr.getRollableResults();
            NonNullList<ChanceResult> newResult = NonNullList.withSize(oldResult.size(), ChanceResult.EMPTY);
            for (int i = 0; i < oldResult.size(); i++) {
                ChanceResult r = oldResult.get(i);
                Item critem = r.stack().getItem();
                WoodType originalType = WoodTypeRegistry.INSTANCE.getBlockTypeOf(critem);
                if (originalType == VanillaWoodTypes.OAK) {
                    Item newItem = BlockSetAPI.changeItemType(critem, originalType, targetType);
                    if (newItem != null) {
                        newResult.set(i, new ChanceResult(r.stack().transmuteCopy(newItem), r.chance()));
                        continue;
                    }
                }
                newResult.set(i, r);
            }
            CuttingBoardRecipe newRec = new CuttingBoardRecipe(cr.getGroup(),
                    Ingredient.of(input), cr.getTool(), newResult, cr.getSoundEvent());

            ResourceLocation recipePath = EveryCompat.res(path);
            sink.addRecipe(new RecipeHolder<>(recipePath, newRec));
        }
    }
}

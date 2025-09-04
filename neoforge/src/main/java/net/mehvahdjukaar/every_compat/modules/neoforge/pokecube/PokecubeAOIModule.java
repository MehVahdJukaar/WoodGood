package net.mehvahdjukaar.every_compat.modules.neoforge.pokecube;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.resources.RecipeTemplate;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import pokecube.core.init.ItemGenerator;
import pokecube.legends.init.BlockInit;
import pokecube.legends.recipes.LegendsDistorticRecipeImpl;

import java.lang.reflect.Field;

//SUPPORT: 4.0.8+
public class PokecubeAOIModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> distortic_planks;
    public final SimpleEntrySet<WoodType, Block> distortic_stairs;
    public final SimpleEntrySet<WoodType, Block> distortic_slab;

    public PokecubeAOIModule(String modId) {
        super(modId, "pcl", EveryCompat.MOD_ID);
        ResourceLocation tab = modRes("building_blocks_tab");

        distortic_planks = SimpleEntrySet.builder(WoodType.class, "planks", "distortic",
                        BlockInit.DISTORTIC_OAK_PLANKS, () -> VanillaWoodTypes.OAK,
                        w -> new Block(Utils.copyPropertySafe(w.planks))
                )
                .addTexture(modRes("block/distortic_oak_planks"))
                .addTag(modRes("legends_planks"), Registries.BLOCK)
                .addTag(modRes("legends_planks"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.PLANKS, Registries.BLOCK)
                .addTag(ItemTags.PLANKS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_planks"))
                .build();
        this.addEntry(distortic_planks);

        distortic_stairs = SimpleEntrySet.builder(WoodType.class, "stairs", "distortic",
                        BlockInit.DISTORTIC_OAK_STAIRS, () -> VanillaWoodTypes.OAK,
                        w -> new ItemGenerator.GenericStairs(w.planks.defaultBlockState(), Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(distortic_planks.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_STAIRS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_STAIRS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_stairs"))
                .build();
        this.addEntry(distortic_stairs);

        distortic_slab = SimpleEntrySet.builder(WoodType.class, "slab", "distortic",
                        BlockInit.DISTORTIC_OAK_SLAB, () -> VanillaWoodTypes.OAK,
                        w -> new SlabBlock(Utils.copyPropertySafe(w.planks))
                )
                .requiresFromMap(distortic_planks.blocks) //REASON: textures, recipes
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.WOODEN_SLABS, Registries.BLOCK)
                .addTag(ItemTags.WOODEN_SLABS, Registries.ITEM)
                .setTabKey(tab)
                .addRecipe(modRes("dimensions/distorted_world/distortic_planks/distortic_oak_slab"))
                .build();
        this.addEntry(distortic_slab);
    }

    @Override
    public void onModSetup() {
        super.onModSetup();

        RecipeTemplate.register(LegendsDistorticRecipeImpl.class, (old, from, to) -> {

            LegendsDistorticRecipeImpl modifiedRecipe = null;
            ItemStack originalResult = old.getResultItem(RegistryAccess.EMPTY);
            ItemStack newResult = RecipeTemplate.convertItemStack(originalResult, from, to);
            ResourceLocation dimId = old.dimId.location();

            try {
                Ingredient inputObj = Ingredient.of(Items.OAK_PLANKS);
                ResourceLocation dummyblock = modRes("distortic_mirror");

                LegendsDistorticRecipeImpl instance = new LegendsDistorticRecipeImpl(inputObj, newResult, dummyblock, dimId);

                Field inputField = LegendsDistorticRecipeImpl.class.getDeclaredField("input");
                inputField.setAccessible(true);
                Ingredient input = (Ingredient) inputField.get(instance);

                ItemStack newIngredient = RecipeTemplate.convertItemStack(input.getItems()[0], from, to);
                Ingredient newInput = Ingredient.of(newIngredient);

                Field blockField = LegendsDistorticRecipeImpl.class.getDeclaredField("block");
                blockField.setAccessible(true);
                ResourceLocation blockId = Utils.getID((Block) blockField.get(instance));

                modifiedRecipe = new LegendsDistorticRecipeImpl(newInput, newResult, blockId,
                        dimId);

            } catch (NoSuchFieldException e) {
                EveryCompat.LOGGER.error("Failed to get Field: ", e);
            } catch (IllegalAccessException e) {
                EveryCompat.LOGGER.error("Failed to access the recipe class: ", e);
            }

            if (newResult == null || modifiedRecipe == null) {
                throw new UnsupportedOperationException("Failed to convert recipe result");
            } else {
                return modifiedRecipe;
            }

        });
    }

}

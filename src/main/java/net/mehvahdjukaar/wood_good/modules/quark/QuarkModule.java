package net.mehvahdjukaar.wood_good.modules.quark;

import lilypuree.decorative_blocks.core.DBItems;
import net.mehvahdjukaar.selene.block_set.leaves.LeavesType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.mehvahdjukaar.wood_good.WoodGood;
import net.mehvahdjukaar.wood_good.modules.CompatModule;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.IForgeRegistry;
import vazkii.quark.base.handler.ToolInteractionHandler;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.content.building.module.WoodenPostsModule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QuarkModule extends CompatModule {

    public QuarkModule(String modId) {
        super(modId);
    }

    private static final String POST_NAME = "post";
    public Map<WoodType, Block> POSTS = new HashMap<>();
    public Map<WoodType, Item> POST_ITEMS = new HashMap<>();

    private static final String STRIPPED_POST = "stripped_post";
    public Map<WoodType, Block> STRIPPED_POSTS = new HashMap<>();
    public Map<WoodType, Item> STRIPPED_POST_ITEMS = new HashMap<>();

    private static final String VERTICAL_SLAB_NAME = "vertical_slab";
    public Map<WoodType, Block> VERTICAL_SLABS = new HashMap<>();
    public Map<WoodType, Item> SUPPORTS_ITEMS = new HashMap<>();

    private static final String VERTICAL_PLANK_NAME = "vertical_plank";
    public Map<WoodType, Block> VERTICAL_PLANKS = new HashMap<>();
    public Map<WoodType, Item> VERTICAL_PLANK_ITEMS = new HashMap<>();

    @Override
    public void registerWoodBlocks(IForgeRegistry<Block> registry, Collection<WoodType> woodTypes) {

        //posts
        for (WoodType w : woodTypes) {

            Block fence = w.fence;
            if (fence != null) {
                boolean nether = !w.canBurn();
                String name = w.getVariantId(POST_NAME, false);
                String strippedName = w.getVariantId(POST_NAME, false);
                if (w.isVanilla() || !shouldRegisterEntry(name, registry)) continue;


                Block post = new PostBlock(fence, nether).setRegistryName(WoodGood.res(name));
                Block stripped = new PostBlock(fence, nether).setRegistryName(WoodGood.res(strippedName));
                ToolInteractionHandler.registerInteraction(ToolActions.AXE_STRIP, post, stripped);

                POSTS.put(w, post);
                STRIPPED_POSTS.put(w, stripped);
                registry.register(post);
                registry.register(stripped);
            }
        }
    }

    @Override
    public void registerLeavesBlocks(IForgeRegistry<Block> registry, Collection<LeavesType> leavesTypes) {

    }

    @Override
    public void registerItems(IForgeRegistry<Item> registry) {
        POSTS.forEach((key, value) -> {
            CreativeModeTab tab = ModuleLoader.INSTANCE.isModuleEnabled(WoodenPostsModule.class) ? CreativeModeTab.TAB_DECORATIONS : null;
            Item i = new BlockItem(value, new Item.Properties().tab(tab));
             POST_ITEMS.put(key, i);
             registry.register(i.setRegistryName(value.getRegistryName()));
        });
    }
}


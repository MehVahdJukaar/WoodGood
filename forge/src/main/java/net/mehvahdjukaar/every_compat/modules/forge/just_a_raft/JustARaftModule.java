package net.mehvahdjukaar.every_compat.modules.forge.just_a_raft;

import com.google.gson.JsonObject;
import com.mrbysco.justaraftmod.entities.RaftType;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.SimpleTagBuilder;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

//SUPPORT: v3.1.0+
public class JustARaftModule extends SimpleModule {

    public final ItemOnlyEntrySet<WoodType, Item> rafts;

    public JustARaftModule(String modId) {
        super(modId, "jar");

        rafts = ItemOnlyEntrySet.builder(WoodType.class, "raft",
                        getModItem("oak_raft"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> {
                            RaftType newRaft = getRaft(w);

                            RaftType.registerRaftType(newRaft);

                            return new RaftItem(newRaft, new Item.Properties());
                        }
                )
                .createPaletteFromChild(p -> {}, "log")
                .addTextureM(modRes("entity/raft/oak_raft"), EveryCompat.res("entity/raft/oak_raft_m"))
                .addTag(modRes("rafts"), Registries.ITEM)
                .setTabKey(RaftRegistry.RAFT_TAB.getId()) //TODO: Update the tab later
                .build();
        this.addEntry(rafts);
    }

    public RaftType getRaft(WoodType w) {
        String name = shortenedId() + "/" + w.getAppendableId();

        return new RaftType(
                w.planks,
                () -> rafts.items.get(w),
                name,
                EveryCompat.res("textures/entity/raft/" + name + "_raft.png")
        );
    }

    @Override
    // RECIPES & TAGS
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);

        rafts.items.forEach((wood, item ) -> {
            // Variables
            ResourceLocation recipeLoc = ResType.RECIPES.getPath(modRes("oak_raft"));
            ResourceLocation tagEC = EveryCompat.res(shortenedId() + "/" + wood.getAppendableId() + "_logs");

// TAGS ====================================================================================================

            // Check for logs having a tag with #<namespace>:<type>_logs
            ResourceLocation tagMOD = new ResourceLocation(wood.getNamespace(), wood.getTypeName() + "_logs");
            TagKey<Block> tagKey = TagKey.create(Registries.BLOCK, tagMOD);
            boolean hasTagWood = wood.log.defaultBlockState().is(tagKey);

            if (!hasTagWood) { // If don't have the tag mentioned above, below will generate a tag
                boolean isTagFull = false;

                SimpleTagBuilder tagBuilder = SimpleTagBuilder.of(tagEC);
                Block[] woods = {
                        wood.log,
                        wood.getBlockOfThis("stripped_log"),
                        wood.getBlockOfThis("wood"),
                        wood.getBlockOfThis("stripped_log")
                };

                // Adding to tag's list
                for (Block block : woods) {
                    if (block != null) {
                        tagBuilder.addEntry(block.asItem());
                        isTagFull = true;
                    }
                }

                // Adding to the resources
                if (isTagFull) {
                    handler.dynamicPack.addTag(tagBuilder, Registries.BLOCK);
                    handler.dynamicPack.addTag(tagBuilder, Registries.ITEM);
                }
            }

// RECIPES =================================================================================================

            try (InputStream recipeStrem = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStrem);

                // Deciding which tagID to use
                String tag = (hasTagWood) ? tagMOD.toString() : tagEC.toString();

                // Editing the recipe
                recipe.getAsJsonObject("key").getAsJsonObject("L")
                        .addProperty("tag", tag);

                recipe.getAsJsonObject("result").addProperty("item", Utils.getID(item).toString());

                // Adding to the resources
                String newRecipeLoc = shortenedId() +"/"+ wood.getAppendableId() + "_raft";

                handler.dynamicPack.addJson(EveryCompat.res(newRecipeLoc), recipe, ResType.RECIPES);

            } catch (IOException e) {
                handler.getLogger().error("Failed to generate recipes for {} : {}", item, e);
            }
        });
    }

}
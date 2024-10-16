package net.mehvahdjukaar.every_compat.modules.forge.just_a_raft;

import com.google.gson.JsonObject;
import com.mrbysco.justaraftmod.entities.RaftType;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.getATagOrCreateANew;

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
                .createPaletteFromChild("log", SpriteHelper.LOOKS_LIKE_SIDE_LOG_TEXTURE)
                .addTextureM(modRes("entity/raft/oak_raft"), EveryCompat.res("entity/raft/oak_raft_m"))
                .addTag(modRes("rafts"), Registries.ITEM)
                .setTabKey(RaftRegistry.RAFT_TAB.getId())
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
    // RECIPES
    public void addDynamicServerResources(ServerDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicServerResources(handler, manager);
        ResourceLocation recipeLoc = ResType.RECIPES.getPath(modRes("oak_raft"));

        rafts.items.forEach((wood, item ) -> {
            try (InputStream recipeStrem = manager.getResource(recipeLoc)
                    .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {
                JsonObject recipe = RPUtils.deserializeJson(recipeStrem);

                // Editing the recipe
                recipe.getAsJsonObject("key").getAsJsonObject("L")
                        .addProperty("tag", getATagOrCreateANew("logs", "caps", wood, handler, manager).toString());

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
package net.mehvahdjukaar.every_compat.modules.forge.just_a_raft;

import com.google.gson.JsonObject;
import com.mrbysco.justaraftmod.entities.RaftType;
import com.mrbysco.justaraftmod.init.RaftRegistry;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.misc.SpriteHelper;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.common_classes.TagUtility.getATagOrCreateANew;

//SUPPORT: v3.1.0+
public class JustARaftModule extends SimpleModule {

    public final ItemOnlyEntrySet<WoodType, Item> rafts;

    public JustARaftModule(String modId) {
        super(modId, "jar");

        rafts = ItemOnlyEntrySet.builder(WoodType.class, "raft",
                        getModItem("oak_raft"), () -> VanillaWoodTypes.OAK,
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
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        ResourceLocation recipeLoc = ResType.RECIPES.getPath(modRes("oak_raft"));

        executor.accept((manager, sink) -> {
            rafts.items.forEach((wood, item) -> {
                try (InputStream recipeStrem = manager.getResource(recipeLoc)
                        .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {
                    JsonObject recipe = RPUtils.deserializeJson(recipeStrem);

                    // Editing the recipe
                    recipe.getAsJsonObject("key").getAsJsonObject("L")
                            .addProperty("tag", getATagOrCreateANew("logs", "caps", wood, sink, manager).toString());

                    recipe.getAsJsonObject("result").addProperty("item", Utils.getID(item).toString());

                    // Adding to the resources
                    String newRecipeLoc = shortenedId() + "/" + wood.getAppendableId() + "_raft";

                    sink.addJson(EveryCompat.res(newRecipeLoc), recipe, ResType.RECIPES);

                } catch (IOException e) {
                    EveryCompat.LOGGER.error("Failed to generate recipes for {} : {}", item, e);
                }
            });

        });
    }

}
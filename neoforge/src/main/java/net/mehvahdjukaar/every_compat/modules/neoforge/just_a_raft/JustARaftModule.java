package net.mehvahdjukaar.every_compat.modules.neoforge.just_a_raft;

import com.google.gson.JsonObject;
import com.mrbysco.justaraftmod.entities.RaftType;
import com.mrbysco.justaraftmod.items.RaftItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.ItemOnlyEntrySet;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.resources.RPUtils;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static net.mehvahdjukaar.every_compat.misc.UtilityTag.getATagOrCreateANew;

//SUPPORT: v7.0.3+
public class JustARaftModule extends EveryCompatModule {

    public final ItemOnlyEntrySet<WoodType, Item> rafts;
    public final Map<WoodType, RaftType> raftTypes = new HashMap<>();

    public JustARaftModule(String modId) {
        super(modId, "jar");
        ResourceLocation tab = modRes("tab");

        rafts = ItemOnlyEntrySet.builder(WoodType.class, "raft",
                        getModItem("oak_raft"), () -> VanillaWoodTypes.OAK,
                        woodType -> new RaftItem(getRaftType(woodType), new Item.Properties())
                )
                .addTextureM(modRes("entity/raft/oak_raft"), EveryCompat.res("entity/raft/oak_raft_m"),
                        PaletteStrategies.LOG_SIDE_STANDARD)
                .addTag(modRes("rafts"), Registries.ITEM)
                .setTabKey(tab)
                .build();
        this.addEntry(rafts);
    }

    private RaftType getRaftType(WoodType w) {
        String name = shortenedId() + "/" + w.getAppendableId();

        return raftTypes.computeIfAbsent(w, woodType -> RaftType.registerRaftType(
                new RaftType(
                        w.planks,
                        DeferredHolder.create(Registries.ITEM, EveryCompat.res(name + "_raft")),
                        name,
                        EveryCompat.res("textures/entity/raft/" + name + "_raft.png")
                )
        ));
    }

    @Override
    // RECIPES
    public void addDynamicServerResources(Consumer<ResourceGenTask> executor) {
        super.addDynamicServerResources(executor);
        executor.accept((manager, sink) -> {
            ResourceLocation recipeLoc = ResType.RECIPES.getPath(modRes("oak_raft"));

            rafts.items.forEach((wood, item) -> {
                try (InputStream recipeStrem = manager.getResource(recipeLoc)
                        .orElseThrow(() -> new FileNotFoundException("Failed to open the recipe @ " + recipeLoc)).open()) {
                    JsonObject recipe = RPUtils.deserializeJson(recipeStrem);

                    // Editing the recipe
                    recipe.getAsJsonObject("key").getAsJsonObject("L")
                            .addProperty("tag", getATagOrCreateANew("logs", "caps", wood, sink, manager).toString());

                    recipe.getAsJsonObject("result").addProperty("id", Utils.getID(item).toString());

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
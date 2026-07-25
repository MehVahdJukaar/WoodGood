package net.mehvahdjukaar.every_compat.platform;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.mehvahdjukaar.every_compat.dynamicpack.ServerDynamicResourcesHandler;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.pack.ResourceGenTask;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class ECPlatStuffImpl extends ECPlatStuff {

    // neoforge strips with the neoforge:strippables data map, so we feed our entries to it through the dynamic data pack
    private static final ResourceLocation STRIPPABLES_DATA_MAP_ID = ResourceLocation.fromNamespaceAndPath("neoforge", "data_maps/block/strippables");
    private static final Map<Block, Block> STRIPPABLES = new LinkedHashMap<>();
    private static boolean wasNotInit = true;

    public static List<ItemStack> modifyLoot(ResourceLocation id, List<ItemStack> stacks, LootParams lootContext) {
        ItemStack[] array = stacks.toArray(ItemStack[]::new);
        return CommonHooks.modifyLoot(id, ObjectArrayList.wrap(array),
                (new LootContext.Builder(lootContext)).create(Optional.of(id)));
    }

    public static void registerStripping(Block block, Block stripped_block) {
        if (!STRIPPABLES.isEmpty() && wasNotInit) {
            //packs must know their namespaces beforehand
            ServerDynamicResourcesHandler.getInstance().addSupportedNamespaces(STRIPPABLES_DATA_MAP_ID.getNamespace());
            wasNotInit = false;
        }
        STRIPPABLES.put(block, stripped_block);
    }

    public static void addPlatformServerResources(Consumer<ResourceGenTask> executor) {
        if (STRIPPABLES.isEmpty()) return;
        executor.accept((resourceManager, sink) -> {
            JsonObject values = new JsonObject();
            STRIPPABLES.forEach((block, stripped) -> values.add(Utils.getID(block).toString(),
                    Strippable.CODEC.encodeStart(JsonOps.INSTANCE, new Strippable(stripped)).getOrThrow()));

            JsonObject json = new JsonObject();
            json.add("values", values);
            sink.addJson(STRIPPABLES_DATA_MAP_ID, json, ResType.JSON);
        });
    }

}

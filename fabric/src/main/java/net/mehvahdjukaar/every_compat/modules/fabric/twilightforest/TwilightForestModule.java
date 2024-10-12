package net.mehvahdjukaar.every_compat.modules.fabric.twilightforest;

import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.RenderLayer;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import twilightforest.block.BanisterBlock;
import twilightforest.block.HollowLogClimbable;
import twilightforest.block.HollowLogHorizontal;
import twilightforest.block.HollowLogVertical;
import twilightforest.init.TFBlocks;
import twilightforest.item.HollowLogItem;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Supplier;

//SUPPORT: v1.2+ | Currently, The Twilight Forest Unofficial
public class TwilightForestModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, BanisterBlock> banisters;
    public final SimpleEntrySet<WoodType, HollowLogVertical> hollowLogsVertical;
    public final SimpleEntrySet<WoodType, HollowLogHorizontal> hollowLogsHorizontal;
    public final SimpleEntrySet<WoodType, HollowLogClimbable> hollowLogsClimbable;

    public TwilightForestModule(String modId) {
        super(modId, "tf");
        var tab = modRes("blocks");

        //TODO: check face culling
        banisters = SimpleEntrySet.builder(WoodType.class, "banister",
                        TFBlocks.OAK_BANISTER, () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new BanisterBlock(Utils.copyPropertySafe(w.planks).noOcclusion())
                )
                .addTag(modRes("banisters"), Registries.BLOCK)
                .addTag(modRes("banisters"), Registries.ITEM)
                .addRecipe(modRes("wood/oak_banister"))
                .copyParentDrop()
                .setTabKey(tab)
                .build();
        this.addEntry(banisters);

        hollowLogsHorizontal = SimpleEntrySet.builder(WoodType.class, "log_horizontal", "hollow",
                        getModBlock("hollow_acacia_log_horizontal", HollowLogHorizontal.class),
                        () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new HollowLogHorizontal(Utils.copyPropertySafe(w.log))
                )
                .requiresChildren("stripped_log") //REASON: Textures
                //REASON: Excluded terrestria's 2 logs have non-standard 16x16 texture, take a look. you'll see why.
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .addTag(modRes("hollow_logs_horizontal"), Registries.BLOCK)
                .noItem().noTab() //REASON: it's using the hollowLogsVertical's tab/item as the main
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(hollowLogsHorizontal);

        hollowLogsVertical = SimpleEntrySet.builder(WoodType.class, "log_vertical", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_VERTICAL, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> {
                            var id = EveryCompat.res(this.shortenedId() + "/" + w.getVariantId("hollow", true) + "_log_climbable");
                            return new HollowLogVertical(Utils.copyPropertySafe(w.log), makeRegObj(id));
                        })
                .requiresChildren("stripped_log") //REASON: Textures
                //REASON: Excluded terrestria's 2 logs have non-standard 16x16 texture, take a look. you'll see why.
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .addTag(modRes("hollow_logs_vertical"), Registries.BLOCK)
                .noItem()
                .setTabKey(tab)
                .addRecipe(modRes("stonecutting/acacia_log/hollow_acacia_log"))
                .build();
        this.addEntry(hollowLogsVertical);

        hollowLogsClimbable = SimpleEntrySet.builder(WoodType.class, "log_climbable", "hollow",
                        TFBlocks.HOLLOW_ACACIA_LOG_CLIMBABLE, () -> WoodTypeRegistry.getValue(new ResourceLocation("acacia")),
                        w -> new HollowLogClimbable(Utils.copyPropertySafe(w.log),
                                makeRegObj(Utils.getID(hollowLogsVertical.blocks.get(w))))
                )
                .requiresChildren("stripped_log") //REASON: Textures
                //REASON: Excluded terrestria's 2 logs have non-standard 16x16 texture, take a look. you'll see why.
                .addCondition(w -> !w.getId().toString().matches("terrestria:(sakura|yucca_palm)"))
                .addTag(modRes("hollow_logs_climbable"), Registries.BLOCK)
                .noItem().noTab() //REASON: it's using the hollowLogsVertical's tab/item as the main
                .setRenderType(RenderLayer.CUTOUT_MIPPED)
                .build();
        this.addEntry(hollowLogsClimbable);


    }

   static Field portingLibBadAPI = Arrays.stream(RegistryObject.class.getDeclaredFields())
            .filter(f -> f.getType().equals(Supplier.class)).findFirst().get();

    @NotNull
    private static<T extends Block> RegistryObject<T> makeRegObj(ResourceLocation id) {
        RegistryObject<T> r = new RegistryObject<>(id, ResourceKey.create(Registries.BLOCK, id));
        portingLibBadAPI.setAccessible(true);
        try {
            portingLibBadAPI.set(r, (Supplier<Block>) () -> BuiltInRegistries.BLOCK.get(id));
        } catch (IllegalAccessException e) {
           throw new RuntimeException(e);
        }
        return r;
    }

    @Override
    public void registerItems(Registrator<Item> registry) {
        super.registerItems(registry);
        hollowLogsVertical.blocks.forEach((w, b) -> {
            String itemName = Utils.getID(b).getPath().replace("_vertical", "");
            String childKey = this.getModId() + ":hollow_log";
            Item i = new HollowLogItem(
                    makeRegObj(Utils.getID(hollowLogsHorizontal.blocks.get(w))),
                    makeRegObj(Utils.getID(b)),
                    makeRegObj(EveryCompat.res(itemName + "_climbable")),
                    new Item.Properties());
            hollowLogsVertical.items.put(w, i);
            w.addChild(childKey, i);
            registry.register(EveryCompat.res(itemName), i);
        });
    }

    @Override
    public void registerBlockColors(ClientHelper.BlockColorEvent event) {
        super.registerBlockColors(event);
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageFoliageColor(l, pos) : FoliageColor.getDefaultColor(),
                hollowLogsClimbable.blocks.values().toArray(Block[]::new));
        event.register(
                (s, l, pos, i) -> l != null && pos != null ?
                        BiomeColors.getAverageGrassColor(l, pos) : -1,
                hollowLogsHorizontal.blocks.values().toArray(Block[]::new));
    }

}

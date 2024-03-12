package net.mehvahdjukaar.every_compat.modules.crayfish;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mrcrayfish.furniture.refurbished.block.*;
import com.mrcrayfish.furniture.refurbished.client.renderer.blockentity.CeilingFanBlockEntityRenderer;
import com.mrcrayfish.furniture.refurbished.core.ModBlockEntities;
import com.mrcrayfish.furniture.refurbished.core.ModCreativeTabs;
import com.mrcrayfish.furniture.refurbished.crafting.StackedIngredient;
import com.mrcrayfish.furniture.refurbished.crafting.WorkbenchContructingRecipe;
import com.mrcrayfish.furniture.refurbished.item.MailboxItem;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.dynamicpack.ClientDynamicResourcesHandler;
import net.mehvahdjukaar.every_compat.misc.ResourcesUtils;
import net.mehvahdjukaar.every_compat.modules.quark.QuarkModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.resources.BlockTypeResTransformer;
import net.mehvahdjukaar.moonlight.api.resources.ResType;
import net.mehvahdjukaar.moonlight.api.resources.StaticResource;
import net.mehvahdjukaar.moonlight.api.resources.recipe.IRecipeTemplate;
import net.mehvahdjukaar.moonlight.api.resources.recipe.TemplateRecipeManager;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RefurbishedFurnitureModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> chairs;
    public final SimpleEntrySet<WoodType, Block> tables;
    public final SimpleEntrySet<WoodType, Block> darkFans;
    public final SimpleEntrySet<WoodType, Block> lightFans;
    public final SimpleEntrySet<WoodType, Block> toilets;
    public final SimpleEntrySet<WoodType, Block> crates;
    public final SimpleEntrySet<WoodType, Block> mailboxes;
    public final SimpleEntrySet<WoodType, Block> jars;

    public RefurbishedFurnitureModule(String modId) {
        super(modId, "rfm");

        TemplateRecipeManager.registerTemplate(modRes("workbench_constructing"), ConstructingTemplate::new);


        // somebody else pls finish this <3
        chairs = SimpleEntrySet.builder(WoodType.class, "chair",
                        () -> getModBlock("oak_chair"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ChairBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of().strength(2.0F))))
                .addRecipe(modRes("constructing/oak_chair"))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_chair"))
                .addTag(modRes("general"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .build();

        this.addEntry(chairs);

        tables = SimpleEntrySet.builder(WoodType.class, "table",
                        () -> getModBlock("oak_table"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new TableBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of()
                                .strength(2.0F))))
                .addRecipe(modRes("constructing/oak_table"))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTexture(modRes("block/oak_table"))
                .addTexture(modRes("block/oak_particle"))
                .addTag(modRes("general"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .build();

        this.addEntry(tables);

        darkFans = SimpleEntrySet.builder(WoodType.class, "dark_ceiling_fan",
                        () -> getModBlock("oak_dark_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .defaultRecipe()
                .addRecipe(modRes("constructing/oak_dark_ceiling_fan"))
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTag(modRes("electronics"), Registries.ITEM)
                .addTag(modRes("bedroom"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_dark_ceiling_fan"))
                .build();

        this.addEntry(darkFans);

        lightFans = SimpleEntrySet.builder(WoodType.class, "light_ceiling_fan",
                        () -> getModBlock("oak_light_ceiling_fan"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CeilingFanBlock(w.toVanillaOrOak(),
                                MetalType.DARK,
                                BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                        .strength(0.8F).sound(w.getSound()).lightLevel(CeilingFanBlock::light)))
                .addRecipe(modRes("constructing/oak_light_ceiling_fan"))
                .addTile(ModBlockEntities.CEILING_FAN::get)
                .setTab(ModCreativeTabs.MAIN::get)
                .addTag(modRes("electronics"), Registries.ITEM)
                .addTag(modRes("bedroom"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTexture(modRes("block/oak_light_ceiling_fan"))
                .build();

        this.addEntry(lightFans);



        crates = SimpleEntrySet.builder(WoodType.class, "crate",
                        () -> getModBlock("oak_crate"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new CrateBlock(w.toVanillaOrOak(), addWoodProp(w, BlockBehaviour.Properties.of()
                                .forceSolidOn().strength(2.5F))))
                .addRecipe(modRes("constructing/oak_crate"))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTile(ModBlockEntities.CRATE::get)
                .addTexture(modRes("block/oak_crate"))
                .addTag(modRes("storage"), Registries.ITEM)
                .addTag(modRes("outdoors"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .build();

        this.addEntry(crates);

        mailboxes = SimpleEntrySet.builder(WoodType.class, "mailbox",
                        () -> getModBlock("oak_mailbox"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailboxBlock(w.toVanillaOrOak(), addWoodPropNoFire(w, BlockBehaviour.Properties.of()
                                .strength(2.5F))))
                .addRecipe(modRes("constructing/oak_crate"))
                .addCustomItem((woodType, block, properties) -> new MailboxItem(block, properties))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTile(ModBlockEntities.MAIL_BOX::get)
                .addTexture(modRes("block/oak_mailbox"))
                .addTag(modRes("outdoor"), Registries.ITEM)
                .addTag(modRes("storage"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .build();

        this.addEntry(mailboxes);

        toilets = SimpleEntrySet.builder(WoodType.class, "toilet",
                        () -> getModBlock("oak_toilet"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new ToiletBlock(BlockBehaviour.Properties.of().mapColor(w.planks.defaultMapColor())
                                .strength(3.5f).sound(SoundType.STONE)))
                .addRecipe(modRes("constructing/oak_toilet"))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTile(ModBlockEntities.TOILET::get)
                .addTextureAutoM(modRes("block/oak_toilet"))
                .addTag(modRes("wooden_toilets"), Registries.ITEM)
                .addTag(modRes("bathroom"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();

        this.addEntry(toilets);

        jars = SimpleEntrySet.builder(WoodType.class, "storage_jar",
                        () -> getModBlock("storage_jar"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new StorageJarBlock(w.toVanillaOrOak(),  BlockBehaviour.Properties.of()
                                .mapColor(w.planks.defaultMapColor())
                                .instrument(NoteBlockInstrument.HAT).strength(1.0F).sound(SoundType.GLASS)))
                .addRecipe(modRes("constructing/oak_storage_jar"))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTile(ModBlockEntities.STORAGE_JAR::get)
                .addTexture(modRes("block/oak_storage_jar"))
                .addTag(modRes("storage"), Registries.ITEM)
                .addTag(modRes("kitchen"), Registries.ITEM)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE, Registries.BLOCK)
                .build();

        this.addEntry(jars);
    }

    @Override
    public void addDynamicClientResources(ClientDynamicResourcesHandler handler, ResourceManager manager) {
        super.addDynamicClientResources(handler, manager);

        // code copied from ResourceUtils.addStandardResources
        {
            //remove the ones from mc namespace
            StaticResource darkBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                    modRes("extra/oak_dark_ceiling_fan_blade")
            ));
            addFanModels(handler, manager, darkBlade, darkFans);
        }
        {
            //remove the ones from mc namespace
            StaticResource lightBlade = StaticResource.getOrLog(manager, ResType.MODELS.getPath(
                    modRes("extra/oak_light_ceiling_fan_blade")
            ));
            addFanModels(handler, manager, lightBlade, lightFans);
        }

    }

    private void addFanModels(ClientDynamicResourcesHandler handler, ResourceManager manager, StaticResource darkBlade, SimpleEntrySet<WoodType, Block> darkFans) {
        darkFans.blocks.forEach((w, b) -> {
            try {
                handler.addSimilarJsonResource(manager, darkBlade, s->
                    s.replace("oak", w.getAppendableId())
                            .replace("texture\": \"refurbished_furniture:block", "texture\": \"everycomp:block/rfm"));

            } catch (Exception exception) {
                EveryCompat.LOGGER.error("Failed to add {} model json file:", b, exception);
            }
        });
    }

    @Override
    public void onClientSetup() {
        super.onClientSetup();
        darkFans.blocks.forEach((key, value) -> {
            ResourceLocation res = EveryCompat.res("extra/" + key.getAppendableId() + "_dark_ceiling_fan_blade");
            ModelManager manager = Minecraft.getInstance().getModelManager();
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> ClientHelper.getModel(manager, res));
        });
        lightFans.blocks.forEach((key, value) -> {
            ModelManager manager = Minecraft.getInstance().getModelManager();
            ResourceLocation res = EveryCompat.res("extra/" + key.getAppendableId() + "_light_ceiling_fan_blade");
            CeilingFanBlockEntityRenderer.registerFanBlade(value, () -> ClientHelper.getModel(manager, res));
        });
    }

    @Override
    public void onClientInit() {
        super.onClientInit();
        ClientHelper.addSpecialModelRegistration(event -> {
            darkFans.blocks.keySet().forEach(w -> {
                event.register(EveryCompat.res("extra/" + w.getAppendableId() + "_dark_ceiling_fan_blade"));
                event.register(EveryCompat.res("extra/" + w.getAppendableId() + "_light_ceiling_fan_blade"));
            });
        });
    }

    public class ConstructingTemplate implements IRecipeTemplate<WorkbenchContructingRecipe.Result> {

        private final List<Object> conditions = new ArrayList<>();

        public final ItemStack result;
        public final NonNullList<StackedIngredient> materials;
        private final boolean notification;

        public ConstructingTemplate(JsonObject json) {

            JsonArray materialArray = GsonHelper.getAsJsonArray(json, "materials");
            this.materials = NonNullList.withSize(materialArray.size(), StackedIngredient.EMPTY);
            IntStream.range(0, materialArray.size()).forEach((i) -> {
                materials.set(i, StackedIngredient.fromJson(materialArray.get(i)));
            });
            String s1 = GsonHelper.getAsString(json, "result");
            int i = GsonHelper.getAsInt(json, "count");
            this.result = new ItemStack(BuiltInRegistries.ITEM.get(new ResourceLocation(s1)), i);
            this.notification = GsonHelper.getAsBoolean(json, "show_notification", true);
        }

        @Override
        public <T extends BlockType> WorkbenchContructingRecipe.Result createSimilar(
                T originalMat, T destinationMat, Item unlockItem, String id) {
            ItemLike newRes = BlockType.changeItemType(this.result.getItem(), originalMat, destinationMat);
            if (newRes == null) {
                throw new UnsupportedOperationException(String.format("Could not convert output item %s from type %s to %s",
                        this.result, originalMat, destinationMat));
            }
            ItemStack newResult = new ItemStack(newRes);
            if (this.result.hasTag()) newResult.setTag(this.result.getOrCreateTag().copy());
            if (id == null) id = BuiltInRegistries.ITEM.getKey(newRes.asItem()).toString();

            List<StackedIngredient> newMaterials = new ArrayList<>();
            for (StackedIngredient ing : this.materials) {
                Ingredient converted = ResourcesUtils.convertIngredient(ing.ingredient(), originalMat, destinationMat);
                newMaterials.add(new StackedIngredient(converted, ing.count()));
            }


            Advancement.Builder advancement = Advancement.Builder.advancement();

            advancement.addCriterion("has_planks", InventoryChangeTrigger.TriggerInstance.hasItems(unlockItem));
            var res = new ResourceLocation(id);
            return new WorkbenchContructingRecipe.Result(res, newResult.getItem(), newResult.getCount(), newMaterials, advancement,
                    modRes("recipes/" + "furnish" + "/" + res.getPath()), notification);
        }

        @Override
        public void addCondition(Object condition) {
            this.conditions.add(condition);
        }

        @Override
        public List<Object> getConditions() {
            return conditions;
        }
    }
}

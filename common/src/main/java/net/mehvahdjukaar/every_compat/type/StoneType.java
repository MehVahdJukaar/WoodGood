package net.mehvahdjukaar.every_compat.type;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class StoneType extends BlockType {

    public final Block stone;

    protected StoneType(ResourceLocation id, Block stone) {
        super(id);
        this.stone = stone;
    }

    @Override
    public String getTranslationKey() {
        return "stone_type." + this.getNamespace() + "." + this.getTypeName();
    }

    @Override
    protected void initializeChildrenBlocks() {
        this.addChild("stone", this.stone);
        this.addChild("stairs", this.findRelatedEntry("stairs", BuiltInRegistries.BLOCK));
        this.addChild("slab", this.findRelatedEntry("slab", BuiltInRegistries.BLOCK));
        this.addChild("wall", this.findRelatedEntry("wall", BuiltInRegistries.BLOCK));
        this.addChild("button", this.findRelatedEntry("button", BuiltInRegistries.BLOCK));
        this.addChild("pressure_plate", this.findRelatedEntry("pressure_plate", BuiltInRegistries.BLOCK));

        Block bricks = this.findRelatedEntry("bricks", BuiltInRegistries.BLOCK);
        this.addChild("bricks", bricks);
        if (bricks != null) {
            this.addChild("brick_stairs", findBrickEntry("stairs"));
            this.addChild("brick_slab", findBrickEntry("slab"));
            this.addChild("brick_wall", findBrickEntry("wall"));
            this.addChild("cracked_bricks", findBrickEntry("cracked","bricks"));
            this.addChild("brick_tiles", findBrickEntry("brick_tiles"));
        }
    }

    private @Nullable Block findBrickEntry(String name) {
        var first = this.findRelatedEntry("brick_" + name, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry("bricks_" + name, BuiltInRegistries.BLOCK);
    }

    private @Nullable Block findBrickEntry(String pre, String post) {
        var first = this.findRelatedEntry(pre,"brick_" + post, BuiltInRegistries.BLOCK);
        if (first != null) return first;
        return this.findRelatedEntry(pre,"bricks_" + post, BuiltInRegistries.BLOCK);
    }

    @Override
    protected void initializeChildrenItems() {
        this.addChild("brick", this.findRelatedEntry("brick", BuiltInRegistries.ITEM));
    }

    @Override
    public ItemLike mainChild() {
        return stone;
    }

    public static class Finder implements SetFinder<StoneType> {

        private final Map<String, ResourceLocation> childNames = new HashMap<>();
        private final Supplier<Block> planksFinder;
        private final ResourceLocation id;

        public Finder(ResourceLocation id, Supplier<Block> planks) {
            this.id = id;
            this.planksFinder = planks;
        }

        static StoneType.Finder vanilla(String stoneName){
            return simple("minecraft", stoneName, stoneName);
        }

        public static StoneType.Finder simple(String modId, String woodTypeName, String planksName) {
            return simple(ResourceLocation.fromNamespaceAndPath(modId, woodTypeName), ResourceLocation.fromNamespaceAndPath(modId, planksName));
        }

        public static StoneType.Finder simple(ResourceLocation woodTypeName, ResourceLocation planksName) {
            return new StoneType.Finder(woodTypeName,
                    () -> BuiltInRegistries.BLOCK.get(planksName));
        }

        public void addChild(String childType, String childName) {
            addChild(childType, id.withPath(childName));
        }

        public void addChild(String childType, ResourceLocation childName) {
            this.childNames.put(childType, childName);
        }

        @ApiStatus.Internal
        @Override
        public Optional<StoneType> get() {
            if (PlatHelper.isModLoaded(id.getNamespace())) {
                try {
                    Block plank = planksFinder.get();
                    var d = BuiltInRegistries.BLOCK.get(BuiltInRegistries.BLOCK.getDefaultKey());
                    if (plank != d && plank != null) {
                        var w = new StoneType(id, plank);
                        childNames.forEach((key, value) -> w.addChild(key, BuiltInRegistries.BLOCK.get(value)));
                        return Optional.of(w);
                    }
                } catch (Exception ignored) {
                }
                EveryCompat.LOGGER.warn("Failed to find custom stone type {}", id);
            }
            return Optional.empty();
        }
    }

}

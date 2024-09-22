package net.mehvahdjukaar.every_compat.type;

import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockTypeRegistry;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Optional;

public class StoneTypeRegistry extends BlockTypeRegistry<StoneType> {

    public static final StoneTypeRegistry INSTANCE = new StoneTypeRegistry();

    public static final StoneType STONE_TYPE = new StoneType(new ResourceLocation("stone"), Blocks.STONE);

    public static Collection<StoneType> getTypes() {
        return INSTANCE.getValues();
    }

    @Nullable
    public static StoneType getValue(ResourceLocation name) {
        return INSTANCE.get(name);
    }

    public StoneTypeRegistry() {
        super(StoneType.class, "stone_type");

        this.addFinder(StoneType.Finder.vanilla("andesite"));
        this.addFinder(StoneType.Finder.vanilla("diorite"));
        this.addFinder(StoneType.Finder.vanilla("granite"));
        this.addFinder(StoneType.Finder.vanilla("tuff"));
        this.addFinder(StoneType.Finder.vanilla("calcite"));
    }

    @Override
    public StoneType getDefaultType() {
        return STONE_TYPE;
    }

    @Override
    public Optional<StoneType> detectTypeFromBlock(Block block, ResourceLocation blockId) {
        String path = blockId.getPath();
        if (path.endsWith("_bricks")) {
            String stoneName = path.substring(0, path.length() - 7);
            var opt = BuiltInRegistries.BLOCK.getOptional(blockId.withPath(stoneName));
            if (opt.isPresent()) {
                return Optional.of(new StoneType(blockId.withPath(stoneName), opt.get()));
            }
        }
        return Optional.empty();
    }


    public static void init() {
        BlockSetAPI.registerBlockSetDefinition(INSTANCE);
    }
}

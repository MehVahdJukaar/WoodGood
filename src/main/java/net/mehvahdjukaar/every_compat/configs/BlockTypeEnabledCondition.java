package net.mehvahdjukaar.every_compat.configs;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.selene.block_set.BlockSetManager;
import net.mehvahdjukaar.selene.block_set.BlockType;
import net.mehvahdjukaar.selene.block_set.wood.WoodType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.Locale;

public record BlockTypeEnabledCondition(BlockType type) implements ICondition {

    private static final String CONDITION_NAME = "class";
    private static final String TYPE_NAME = "id";
    public static final ResourceLocation ID = WoodGood.res("type_enabled");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return EarlyConfigs.isTypeEnabled(type);
    }

    public boolean test() {
        return EarlyConfigs.isTypeEnabled(type);
    }

    public static class Serializer implements IConditionSerializer<BlockTypeEnabledCondition> {

        public Serializer() {
        }

        @Override
        public void write(JsonObject json, BlockTypeEnabledCondition value) {
            json.addProperty(CONDITION_NAME, value.type.getClass().getSimpleName().toLowerCase(Locale.ROOT));
            json.addProperty(TYPE_NAME, value.type.id.toString());
        }

        @Override
        public BlockTypeEnabledCondition read(JsonObject json) {
            var type = json.getAsJsonPrimitive(CONDITION_NAME).getAsString();
            var name = json.getAsJsonPrimitive(TYPE_NAME).getAsString();
            for (var r : BlockSetManager.getRegistries()) {
                if (r.getType().getSimpleName().toLowerCase(Locale.ROOT).equals(type)) {
                    return new BlockTypeEnabledCondition(r.get(new ResourceLocation(name)));
                }
            }
            WoodGood.LOGGER.error(new UnsupportedOperationException("Unrecognized block type " + type));
            return new BlockTypeEnabledCondition(WoodType.OAK_WOOD_TYPE);
        }

        @Override
        public ResourceLocation getID() {
            return ID;
        }
    }
}

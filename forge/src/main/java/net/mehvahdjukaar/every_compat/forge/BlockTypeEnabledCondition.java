package net.mehvahdjukaar.every_compat.forge;

import com.google.gson.JsonObject;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.configs.ModConfigs;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlockType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

import java.util.Locale;

//not even needed since we are not adding it if it's not enable
public record BlockTypeEnabledCondition(BlockType type) implements ICondition {

    private static final String CONDITION_NAME = "class";
    private static final String TYPE_NAME = "id";
    public static final ResourceLocation ID = EveryCompat.res("type_enabled");

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return ModConfigs.isTypeEnabled(type);
    }

    public boolean test() {
        return ModConfigs.isTypeEnabled(type);
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
            for (var r : BlockSetAPI.getRegistries()) {
                if (r.getType().getSimpleName().toLowerCase(Locale.ROOT).equals(type)) {
                    return new BlockTypeEnabledCondition(r.get(new ResourceLocation(name)));
                }
            }
            EveryCompat.LOGGER.error(new UnsupportedOperationException("Unrecognized block type " + type));
            return new BlockTypeEnabledCondition(WoodTypeRegistry.OAK_TYPE);
        }

        @Override
        public ResourceLocation getID() {
            return ID;
        }
    }
}

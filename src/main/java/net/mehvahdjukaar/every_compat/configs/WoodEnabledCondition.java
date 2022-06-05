package net.mehvahdjukaar.every_compat.configs;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.mehvahdjukaar.every_compat.WoodGood;

public record WoodEnabledCondition(String condition) implements ICondition {

    private static final String CONDITION_NAME = "wood_type";
    public static final ResourceLocation ID = WoodGood.res(CONDITION_NAME);

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return EarlyConfigs.isWoodEnabled(this.condition);
    }

    public boolean test() {
        return EarlyConfigs.isWoodEnabled(this.condition);
    }

    public static class Serializer implements IConditionSerializer<WoodEnabledCondition> {

        public Serializer() {
        }

        @Override
        public void write(JsonObject json, WoodEnabledCondition value) {
            json.addProperty(CONDITION_NAME, value.condition);
        }

        @Override
        public WoodEnabledCondition read(JsonObject json) {
            return new WoodEnabledCondition(json.getAsJsonPrimitive(CONDITION_NAME).getAsString());
        }

        @Override
        public ResourceLocation getID() {
            return ID;
        }
    }
}

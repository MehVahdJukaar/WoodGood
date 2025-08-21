package net.mehvahdjukaar.every_compat.forge;


import net.mehvahdjukaar.every_compat.EveryCompat;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.MissingMappingsEvent;
import net.minecraftforge.registries.RegisterEvent;

import java.util.Map;

public class LegacyRemapper {

    // Important. wherever a block name or most likely block entity changes (like gets removed in favour of a mod one), add the old names here to prevent backward compat with saves that have them
    private static final Map<ResourceLocation, String> OLD_TO_NEW_NAMES = Map.of(
            new ResourceLocation("everycomp:sd_full_drawers_1"), "storagedrawers:standard_drawers_1",
            new ResourceLocation("everycomp:sd_full_drawers_2"), "storagedrawers:standard_drawers_2",
            new ResourceLocation("everycomp:sd_full_drawers_4"), "storagedrawers:standard_drawers_4",
            new ResourceLocation("everycomp:sd_half_drawers_1"), "storagedrawers:standard_drawers_1",
            new ResourceLocation("everycomp:sd_half_drawers_2"), "storagedrawers:standard_drawers_2",
            new ResourceLocation("everycomp:sd_half_drawers_4"), "storagedrawers:standard_drawers_4"
    );

    //this can be slow. doesn't matter since it only happens once on boot
    @SubscribeEvent
    public static void remapBlocks(MissingMappingsEvent event) {
        remapInRegistry(event, BuiltInRegistries.BLOCK_ENTITY_TYPE);
        remapInRegistry(event, BuiltInRegistries.BLOCK);
        remapInRegistry(event, BuiltInRegistries.ITEM);
    }

    private static <T> void remapInRegistry(MissingMappingsEvent event, Registry<T> reg) {
        for (var mapping : event.getMappings(reg.key(), EveryCompat.MOD_ID)) {
            String newMap = OLD_TO_NEW_NAMES.get(mapping.getKey());
            if (newMap != null) {
                reg.getOptional(new ResourceLocation(newMap))
                        .ifPresent(mapping::remap);
            }
        }
    }

    //event above is cooked... this is the real mvp
    @SubscribeEvent
    public static void registerEvent(RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.BLOCK_ENTITY_TYPES.getRegistryKey())) {
            if (event.getForgeRegistry() instanceof ForgeRegistry<?> fr) {
                OLD_TO_NEW_NAMES.forEach((oldKey, newKey) -> {
                    fr.addAlias(oldKey, new ResourceLocation(newKey));
                });
            }
        }
    }


           /*
    private static <T extends IForgeRegistryEntry<T>> void remapEntries(RegistryEvent.MissingMappings<T> event, IForgeRegistry<T> blockReg) {
        if (!EarlyConfigs.REMAP_COMPAT.get()) return;
        for (var compatMod : EveryCompat.COMPAT_MODS) {
            if (ModList.get().isLoaded(compatMod.modId())) continue;
            String woodsFrom = compatMod.woodsFrom(); //ie bop
            label:
            for (var mapping : event.getMappings(compatMod.modId())) {
                //ie: bopcomp:willow_table

                for (String blockFrom : compatMod.blocksFrom()) {
                    CompatModule module = EveryCompat.ACTIVE_MODULES.get(blockFrom); //if we have target mod
                    // only works for simple modules
                    if (module instanceof SimpleModule simpleModule) {
                        //only works on simple entries
                        for (var entry : simpleModule.getEntries()) {
                            if (entry instanceof SimpleEntrySet se) {
                                //find wood types from that mod id

                                String s = se.getEquivalentBlock(module, mapping.key.getPath(), woodsFrom);
                                if (s != null) {
                                    if (blockReg.containsKey(EveryCompat.res(s))) {
                                        var b = blockReg.getValue(EveryCompat.res(s));
                                        mapping.remap(b);
                                        EveryCompat.LOGGER.info("Remapping block '{}' to '{}'", mapping.key, b);
                                        continue label;
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static <T extends IForgeRegistryEntry<T>> void clearRemoved(RegistryEvent.MissingMappings<T> event, IForgeRegistry<T> blockReg) {

        for (var mapping : event.getMappings(MOD_ID)) {
            mapping.remap(Blocks.AIR);

            String name = mapping.key.getPath();
            String[] s = name.split("/");
            if(s.length == 3){
                String moduleId = s[0];
                String namespace = s[1];
                String oldName = s[2];
                forAllModules(m->{
                    if(m instanceof SimpleModule sm) {
                        if (m.shortenedId().equals(moduleId)) {
                            for (var entry : sm.getEntries()){
                                if(entry instanceof SimpleEntrySet se){
                                    String wood = se.parseWoodType(oldName);
                                    if(wood != null){
                                        for(var b : se.get.)){
                                            ResourceLocation firstId = b.
                                            mapping.remap(blockReg);
                                            return;;
                                        }

                                    }
                                }
                            }
                        }
                    }

                });
            }

        }
    }*/

}

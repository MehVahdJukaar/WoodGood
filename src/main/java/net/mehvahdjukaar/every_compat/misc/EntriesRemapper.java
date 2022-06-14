package net.mehvahdjukaar.every_compat.misc;

import net.mehvahdjukaar.every_compat.WoodGood;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.every_compat.configs.EarlyConfigs;
import net.mehvahdjukaar.every_compat.modules.CompatModule;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class EntriesRemapper {

    //this can be slow. doesn't matter since it only happens once on boot
    @SubscribeEvent
    public static void remapBlocks(RegistryEvent.MissingMappings<Block> event) {
        remapEntries(event, ForgeRegistries.BLOCKS);
        if (EarlyConfigs.REMAP_OWN.get()) {
            for (var mapping : event.getMappings(WoodGood.MOD_ID)) {
                mapping.remap(Blocks.OAK_PLANKS);
            }
        }
    }

    //this can be slow. doesn't matter since it only happens once on boot
    @SubscribeEvent
    public static void remapItems(RegistryEvent.MissingMappings<Item> event) {
        remapEntries(event, ForgeRegistries.ITEMS);
        if (EarlyConfigs.REMAP_OWN.get()) {
            for (var mapping : event.getMappings(WoodGood.MOD_ID)) {
                mapping.remap(Items.AIR);
            }
        }
    }

    private static <T extends IForgeRegistryEntry<T>> void remapEntries(RegistryEvent.MissingMappings<T> event, IForgeRegistry<T> blockReg) {
        if (!EarlyConfigs.REMAP_COMPAT.get()) return;
        for (var compatMod : WoodGood. COMPAT_MODS) {
            String woodFrom = compatMod.woodFrom(); //ie bop
            label:
            for (var mapping : event.getMappings(compatMod.modId())) {
                //ie: bopcomp:willow_table

                for (String blockFrom : compatMod.blocksFrom()) {
                    CompatModule module = WoodGood.ACTIVE_MODULES.get(blockFrom); //if we have target mod
                    // only works for simple modules
                    if (module instanceof SimpleModule simpleModule) {
                        //only works on simple entries
                        for (var entry : simpleModule.getEntries()) {
                            if (entry instanceof SimpleEntrySet se) {
                                //find wood types from that mod id

                                String s = se.getEquivalentBlock(module, mapping.key.getPath(), woodFrom);
                                if (s != null) {
                                    if (blockReg.containsKey(WoodGood.res(s))) {
                                        var b = blockReg.getValue(WoodGood.res(s));
                                        mapping.remap(b);
                                        WoodGood.LOGGER.info("Remapping block '{}' to '{}'", mapping.key, b);
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
            /*
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

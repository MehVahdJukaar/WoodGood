package net.mehvahdjukaar.every_compat;

import com.mojang.datafixers.util.Pair;
import net.mehvahdjukaar.every_compat.api.CompatModule;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

//mega hack. I hate this. Only here to prevent classload shenanigans when loading EC classes from another mod
//needs to safely load without referencing anything
@ApiStatus.Internal
public class EcTempPluginStorage {

    public static List<CompatModule> COMPAT_MODULES = new ArrayList<>();
    public static List<Pair<String, Supplier<Class<? extends CompatModule>>>> OPT_MODULES = new ArrayList<>();
    public static List<EveryCompat.OtherCompatMod> COMPAT_MODS = new ArrayList<>();
    public static boolean EC_LOADED = false;

    public synchronized static void add(CompatModule add) {
        COMPAT_MODULES.add(add);
        if (EC_LOADED) flush();
    }

    public synchronized static void  addOptional(String modId, Supplier<Class<? extends CompatModule>> moduleClass) {
        OPT_MODULES.add(Pair.of(modId, moduleClass));
        if (EC_LOADED) flush();
    }

    public synchronized static void addOtherCompatMod(String compatModId, List<String> fromModId, List<String> supportedModId) {
        COMPAT_MODS.add(new EveryCompat.OtherCompatMod(compatModId, fromModId, supportedModId));
        if (EC_LOADED) flush();
    }

    public synchronized static void setEcLoaded() {
        EC_LOADED = true;
        flush();
    }

    private synchronized static void flush() {
        for (CompatModule m : COMPAT_MODULES) {
            EveryCompat.addModule(m);
        }
        for (var p : OPT_MODULES) {
            EveryCompat.maybeAddModule(p.getFirst(), p.getSecond());
        }
        for (var m : COMPAT_MODS) {
            EveryCompat.addOtherCompatMod(m);
        }
        COMPAT_MODULES = null;
        COMPAT_MODS = null;
    }
}

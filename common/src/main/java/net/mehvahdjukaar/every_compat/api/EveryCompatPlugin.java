package net.mehvahdjukaar.every_compat.api;


/**
 * Implement this to register new wood type blocks and module to Every Compat, or to disable some
 * <br>
 * NeoForge: annotate the class with {@link ECPlugin}
 * <br>
 * Fabric: add it to the "everycomp" entrypoint in your fabric.mod.json
 * <br><br>
 * Needs a no arg constructor. Called before Every Compat creates its modules
 * <p>
 * Take a look at {@link net.mehvahdjukaar.every_compat.api.example.WoodGoodAddonExample} for examples
 */
public interface EveryCompatPlugin {

    /**
     * id of the mod shipping this plugin
     */
    //TODO: make non default and mandatory
    default String getModId(){
        return null; //You must implement this. null is temporary
    };

    void register(PluginEvent registrar);


}

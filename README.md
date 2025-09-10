# Every Compat

Tired of incomplete wood sets? Well you came to the right place!

This Mod serves as an addon to many popular mods and is meant to fill in the gaps by adding all the blocks that such mods add in all wood types currently installed.

Every block type added is available in ALL wood types.

With this mod you won't need extra compatibility addons and instead only rely on a single universal one! It's all automatic!

Check out [CurseForge](https://www.curseforge.com/minecraft/mc-mods/every-compat) or [Modrinth](https://modrinth.com/mod/every-compat) for more details

## API to support your mod with Every Compat (Wood Good)

### KEY POINTS to keep in mind: 
1. If your mod is multi-platform with blocks' class being in COMMON, then you can put the module in COMMON to support your mod
2. If blocks' class is in FORGE and FABRIC, then you'll need to create 2 modules in both FABRIC & FORGE's folder

**NOTE:**

if your mod is already supported heavily consider reaching out to incorporate some EC integration code in your mod itself for future proofing/stability

### DEVELOPMENT: 


To import EveryCompat in your dev environment you can add the following gradle line

`modCompileOnly("every-compat/628539/[ec_version]")`

Where `ec_version` is the version number you can see in CurseForge after clicking on a particular file version. For example `6974680` is for 1.21.1-2.11.3-fabric
You can also click on "Curse Maven Snippet" in the files section of a particular file to get this same import line this way. Remember to use `modCompileOnly`



Once you have done that, to interact with EveryCompat, all will happen through the Every Compat API class.

`common/src/main/java/net/mehvahdjukaar/every_compat/api/EveryCompatAPI.java` 

it has 3 usages:
1) Using `EveryCompatAPI.registerModule(new WoodGoodModule(mod_Id))` to add the module to EveryCompat to support your mod
2) Using `EveryCompatAPI.addOtherCompatMod(String, List<String>, List<String>)` to add a compat Mod that your mod is already supported with Wood Mods
3) Add an undetected WoodType or LeavesType from your mod, an example is provided in `EveryCompatAPI`

Take a look at how the module is supporting blocks or items from a mod (detail level is general): 

`common/src/main/java/net/mehvahdjukaar/every_compat/api/example/WoodGoodModule.java`

If you want a **high detail level** example of how is this applied, then you can check below:
1. [GuitaWoodworks-Init](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L57) - Initization
2. [GuitaWoodworks-EveryCompatModule](https://github.com/macuguita/woodworks/blob/1.21.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L105) - Checking if EveryCompat is installed 
3. [ModCompat](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/ModCompat.java) - Using `EveryCompatAPI.registerModule(...)`
4. [WoodGoodModule](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/WoodGood.java)

### NOTIFICATION

If you added an module in your mod, then you can go to Every Compat's github to create a new issue using "Built-In Module Notification" 
to let DEV know. This way Your mod will be added to the table listing supported mods via Curseforge or Modrinth.

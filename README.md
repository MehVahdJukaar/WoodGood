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

Two Things are required:
- **Every Compat (Wood Good)**
- **Moonlight Lib** used to be known as **Selene**

To know how to support your mod with Every Compat, Please look at the below:

`common/src/main/java/net/mehvahdjukaar/every_compat/api/EveryCompatAPI.java`

If you want one more example of how is this applied, then you can check below:
1. [GuitaWoodworks-Init](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L57)
2. [GuitaWoodworks-EveryCompatModule](https://github.com/macuguita/woodworks/blob/1.21.1/common/src/main/java/com/macuguita/woodworks/GuitaWoodworks.java#L105)
3. [ModCompat](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/ModCompat.java)
4. [WoodGoodModule](https://github.com/macuguita/woodworks/blob/1.20.1/common/src/main/java/com/macuguita/woodworks/compat/WoodGood.java)


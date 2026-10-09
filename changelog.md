| **Legends**                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------|
| \- **(C)**: FORGE & FABRIC                                                                                                        |
| - **(FB)**: FABRIC                                                                                                                |
| - **(NF)**: NEOFORGE                                                                                                              |
| - **(IT)**: Included Texture - added the ResourceLocation of the missing textures required for blocks or generating a new texture |
| - **(TEX)**: hand-made textures to improve the way a block looks                                                                  |
| - **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't include the Supported Mod and the Wood Mod                |
| - **(INCLUDED)**: The block is not generated because a Wood Mod already has the same block as the supported mod will be generated |
| - **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason                                               |
| - **(UDBT)**: Undetected BlockTypes will be manually added                                                                        |
| - **(LIMITED)**: The mod will be only limited to specific version so it is still supported but newer version won't be supported   |
|                                                                                                                                   |

---

## v2.11.53

### CHANGES:
- **Every Compat** (C): Improved the outdated method in UtilityRecipe to generate recipes for **Gems Realm** with **Create**  
- **Luminous Nether** (IT): Removed `withered` & `mushroom`'s ResourceLocation. They no longer need (IT) - [#1335](https://github.com/MehVahdJukaar/WoodGood/issues/1335)
- **Antarchy** (EXCLUDED): blacklisted antarchy:truffalo with Quark:hollow_log because the texture is not a normal 16x16 - [#1332](https://github.com/MehVahdJukaar/WoodGood/issues/1332)

### FIXES:
- **More Chest Variant** (C): Corrected the chest's texture location - [#1327](https://github.com/MehVahdJukaar/WoodGood/issues/1327)
- **Resonant End** (IT): Added ResourceLocation for `Yellow Chorus Leaves` and `Blossom Chorus Leaves` to use `yellow_chorus_vines` and `blossom_chorus_vines` for texturing - [#1328](https://github.com/MehVahdJukaar/WoodGood/issues/1328)
- **Woodworks** (INCLUDED): leaf_pile with **Quark**'s leavesType - [#1333](https://github.com/MehVahdJukaar/WoodGood/issues/1333)

---

## v2.11.52

### FIXES: 
- **Woodworks** (C): Added missing LANG & Missing BlockItem for `closet` & `trapped_closet` - [#1326](https://github.com/MehVahdJukaar/WoodGood/issues/1326)
- **Every Compat** (C): 
  - Improved Chest texture generation so it will re-generate textures when "F3 + T" is used
  - Fixed some issues with bamboo like blocks causing missing models

---

## v2.11.51

### FIXED:
- **Twigs** (FB): Updated ID for Creative_mode_tab - [#1318](https://github.com/MehVahdJukaar/WoodGood/issues/1318)
  - NOTE: Fabric do not have the same ID regarding Creative_mode_tab as Neoforge's

### CHANGES:
- **Every Compat** (C):
  - en_us (LANG) for **Rechiseled**, **Macaw's Windows**' CURTAIN_ROD, **Twilight Forest**'s dry_racking, hollow_log for WoodType (StemType) - Backported from 1.21.1
  - Improved Texture Generation for Chests: Corrected Chests' texture from being too dark - [#1313](https://github.com/MehVahdJukaar/WoodGood/issues/1313)
- **Chipped** (C): Improved `POLISHED_OAK_PLANKS`' texture generation to have a better looks - [#1120](https://github.com/MehVahdJukaar/WoodGood/issues/1120)
- **Architect's Palette** (C): Added the tag, `#architects_palette:boards` to boards
- **Oh Biomes We've Gone** (TEX): Moved textures from `byg` to `biomeswevegone`

### ADDED:
- **Woodworks** (FG): Added `CLOSET` & `TRAPPED_CLOSET` for BambooLike WoodTypes.
  - NOTE: These WoodType that aren't BambooType will use `CHEST` & `TRAPPED_CHEST`

---

## v2.11.50

### CHANGES:
- **Timber Frame** (LIMITED): WIll be no longer supported from v3.0.0 onward because it has an internal code that handle all of WoodTypes
- **Every Compat** (C): 
  - Updated its code for new config screen
  - Added a new method for Chests' texture where it can copy textures that is already shipped via mod that add `CHESTS` - [#1312](https://github.com/MehVahdJukaar/WoodGood/issues/1312)
    - NOTE: this mean Every Compat will not generate a new texture for CHESTS if a texture for it already exist via **Quark**'s ASSET or **Woodworks**' ASSET 
- **Regions Unexplored** (TEX): Further improvement for mask texture via custom texture generation using `brimwood_plank` to fit with animated textures - [#1310](https://github.com/MehVahdJukaar/WoodGood/issues/1310)
- **Quark** (NF): Fixed where the config that disable blocks like `HOLLOW_LOG` but **Every Compat** still generate `HOLLOW_LOG`

---

## v2.11.49

### CHANGES:
- **Quark** (NF): Implemented AXE_STRIP for `POST` to be stripped into `STRIPPED_POST`
- **Every Compat (INCLUDED)**: **Architect's Palette**'s BOARDS with **Windswept** - Requested by @derp_gamer22
- **MrCrayFish's Refurbished Furniture** (C): Set some blocks to not have an animated texture - [#1304](https://github.com/MehVahdJukaar/WoodGood/issues/1304)
- **Regions Unexplored** (TEX): Improved the custom texture generation using `brimwood_planks` - this is related to above, please see **Refurbished Furniture**'s issue for more detail

### REMOVED:
- **Every Compat** (C): ENABLE_FRAMED_BLOCKS_BLACKLIST is no longer needed. Use `everycomp-hazardous.toml` as an alternative

### ADDED:
- **Macaw's Windows** (C): new block, `curtain_rod` - [#1307](https://github.com/MehVahdJukaar/WoodGood/issues/1307)
- **Bibliocraft Legacy** (Compat): Added exceptions for **bibliobiomes** & **bibliowoods**

### NEW:
- **Rechiseled** (C)

---

## v2.11.48

### CHANGES:
- **Every Compat** (C): Fixed a crash with **Quark** + **Environment** or Other Wood Mods - [#1300](https://github.com/MehVahdJukaar/WoodGood/issues/1300)
- **Lightman's Currency** (NF): Updated the outdated CreativeTab - [#1301](https://github.com/MehVahdJukaar/WoodGood/issues/1301)

---

## v2.11.47

### CHANGES:
- **Every Compat** (C): 
  - Moved USE_EXTERNAL_RESOURCE_PACK to `everycomp-client.toml` instead of `everycomp-common.toml`
  - Removed Screen Config class for FABRIC & NEOFORGE

### FIXES:
- **Woodworks** (NF): 
  - Corrected the chests' mask textures - [#1295](https://github.com/MehVahdJukaar/WoodGood/issues/1295)
  - Improved Sawmill Recipe generation because there was missing recipes with some Wood mods like **Quark** - Reported by @derp_gamer22
- **EveryCompat** (C): Fixed the loot_table generation not working correctly with **Quark**'s BOOKSHELF - [#1291](https://github.com/MehVahdJukaar/WoodGood/issues/1291)

### OTHERS:
- **Moonlight Lib v3.1.3** (C) - NOTE that issues from Every Compat are fixed below 
  - Fixed the Environmental's LeavesType for variant Wisteria's missing Associated WoodType - [#1294](https://github.com/MehVahdJukaar/WoodGood/issues/1294)
  - HardcodedBlockTypes:
    - Set the following WoodTypes to be treated as BambooType - [#1250](https://github.com/MehVahdJukaar/WoodGood/issues/1250) 
      - garden_of_the_dead:whistlecane
      - mynethersdelight:powdery
      - dungeonsdelight:wormwood
    - Added Shroomcraft's 4 undetected Mushroom - [#1286](https://github.com/MehVahdJukaar/WoodGood/issues/1286)
    - Added Associated WoodType to 5 LeavesType from No Man's Land - [#1243](https://github.com/MehVahdJukaar/WoodGood/issues/1243)

---

## v2.11.46

### ADDED:
- **Farmer's Delight** (COMPAT): **Windswept Delights**

### CHANGES:
- **Every Compat** (C): Added a new class to Collects resource-generation failures during a single generation pass - @MehVahdJukaar
- **More Crafting Tables -LieOnLion** (C): Re-enabled - [#1285](https://github.com/MehVahdJukaar/WoodGood/issues/1285)

### FIXES:
- **Copper Age Backport** (C): Fixed the crash on SERVER Side - [#1277](https://github.com/MehVahdJukaar/WoodGood/issues/1277)
- **Farmer's Delight** (C): 
  - Excluded **Abundant Atmosphere** from Recipe Generation for CUTTING_BOARDS' recipe with all WoodType's Children
  - Tweaked the recipe generation to use Bamboo Recipes only for WoodType that are Bamboo-like
- **Woodworks** (NF): Tweaked the recipe generation to use Bamboo Recipes only for WoodType that are Bamboo-like
- **Curiosities** (NF): Tweaked the recipe generation to use Bamboo Recipes only for WoodType that are Bamboo-like

---

## v2.11.45

### CHANGES:
- **Every Compat** (C): 
  - Add new class to prevent multiple missing texture due to texture generation failure & model generation failing to generate models/block or models/item files
  - Replaced a few classes from the deprecated, `isVanilla()` to `isKnownVanillaWood(WoodType)`
  - `CompatChestBlockEntity` no longer have `getDefaultName()` so this mean when you open Chest, you will see on left, top side where the name is "Chest". "\[WoodName] Chest" is no longer there. - [#1233](https://github.com/MehVahdJukaar/WoodGood/issues/1233)

### ADDED:
- **Boatload** (EXCLUDED): **Abundant Atmosphere** has its own support compat for **Boatload**
- **Abundant Atmosphere** (EXCLUDED): Blocks from **Farmer's Delight** will be no longer generated because **Abundant Atmosphere** has a built-in compat
- **Architects Palette** (INCLUDED): ensure BOARDS with **Darker Depths** is generated if **Woodworks** are installed
  - NOTE: **Darker Depths** has a built-in support for **Woodworks**'s BOARDS

### LANG:
- **JL_JP**: Correcting LANG for WoodTypes that are noun or adjective - Updated by Abbage230

---

## v2.11.44

### FIXES:
- **Every Compat** (NF): Auto-registering blocks to Neoforge Capabilities
  - Other supported Mods that has CHESTS that doesn't work with **Create**'s item handler and also other Mods' item handler with CHESTS 
    - [#1209](https://github.com/MehVahdJukaar/WoodGood/issues/1209)
    - [#1266](https://github.com/MehVahdJukaar/WoodGood/issues/1266)
    - [#1241](https://github.com/MehVahdJukaar/WoodGood/issues/1241)
- **The New Shutter** (NF): Corrected the ID for Creative Tab on NEOFORGE side with **Sinytra Connector**
  - What happened is with **Sinytra Connector**, it was using **The New Shutter (FABRIC)**'s ID for Creative Tab

### CHANGES: 
- <span style="color: yellow;">**Every Compat** (SEE NOTE below): Using new build script</span>
- **Corail Pillar** (NF): Moved to COMMON, the FABRIC side is now supported
- **Macaw's Paths & Pavings** (FB): Removed the temp fix for Creative-Tab's ID since **Macaw's Path (FABRIC)**'s ID for creative_tab is fixed in v1.1.2
- **Furnish** (FB): Updated to support `v29+`
- **BoatLoad** (NF): Improved the texture generation for boats

### NOTE: 
**Every Compat** is using a new build script that replace Architectury-Loom because it haven't received updates and is stuck
on 1.13

it's no longer using Architectury-Loom. COMMON is based on NEOFORGE (no longer based on FABRIC)
This meant the supported mods from FABRIC side cannot be supported with Sinytra Connector. Here's a list of affected mods that has to be moved back to FABRIC side: 
- **Blockus**
- **Stylish Stiles**
- **Excessive Building**
- **Furnish**

---

## v2.11.43

### CHANGES:
- **Every Compat** (C): Tweaked the code in duplication system to check first & ensure `blocks from a mod that are both Supported-Mod & Wood-Mod` to be excluded
  - Example: `everycomp:q/quark/azalea_ladder` and `quark:azalea_ladder` are exactly the same block, so we want to exclude EC's block, 
  - NOTE: `q` is the shortened_id for **Quark** as supported-mod

---

## v2.11.43

### CHANGES:
- **Every Compat** (C): Tweaked the code in duplication system to check first & ensure `blocks from a mod that are both Supported-Mod & Wood-Mod` to be excluded
  - Example: `everycomp:q/quark/azalea_ladder` and `quark:azalea_ladder` are exactly the same block, so we want to exclude EC's block, 
  - NOTE: `q` is the shortened_id for **Quark** as supported-mod

---

## v2.11.42

### CHANGES:
- **Regions Unexplored** (C): Updated to support v0.6+ and the older version won't be supported onward - [#1262](https://github.com/MehVahdJukaar/WoodGood/issues/1262)
- **Woodworks** (NF): Change the order of how items are placed in Creative Tab, Items are placed after the same WoodType instead of DecorativeType or FurnitureType - [#1259](https://github.com/MehVahdJukaar/WoodGood/issues/1259)
- **Farmer's Delight** (C): 
  - Updated the recipe generation for the new recipe system since FABRIC-v3.3.0+ or NEOFORGE-v1.3.0+ - [#1257](https://github.com/MehVahdJukaar/WoodGood/issues/1257)
  - Updated CABINETS' textures with **Quark** - @derp_gamer22 via Discord

### FIXES:
- **Every Compat** (FB):
  - Exclude **Lauch's Shutters aka The New Shutter** from being loaded when **Vanilla Shutters** is installed - [#1261](https://github.com/MehVahdJukaar/WoodGood/issues/1261)
  - Tweaked a code to avoid an extremely rare case leading to crash - [#1265](https://github.com/MehVahdJukaar/WoodGood/issues/1265)
- **Nature's Spirit** (IT): Using `joshua_bundle` and `stripped_joshua_bundle` instead of `joshua_log` and `stripped_joshua_log` for texture generation - [#1251](https://github.com/MehVahdJukaar/WoodGood/issues/1251)
- **Alex's Caves (Unofficial Port)** (IT): Fixed the missing texture for WoodType, thornwood using either log or stripped_log - [#1258](https://github.com/MehVahdJukaar/WoodGood/issues/1258)

### ADDED:
- **Valhelsia Structures** (INCLUDED): With **Quark** for POST & STRIPPED_POST - [#1260](https://github.com/MehVahdJukaar/WoodGood/issues/1260)

### LANG:
- **EN_US**: Corrected the LANG for HOLLOW_LOG from **The Twilight Forest** & **Quark** with **Enderscape** & **Infernal Expansion**

--- 

## v2.11.41

### FIXES: 
- **Curiosities** (NF): Missing recipe with using PLANKS to get FANCIED_PLANKS via Sawmill from **Woodworks** - [#1244](https://github.com/MehVahdJukaar/WoodGood/issues/1244) 
- **Farmer's Delight** (C): Updated Cabinets' tags to have `#farmersdelight:cabinets` and `#farmersdelight:cabinets/wooden` - [#1248](https://github.com/MehVahdJukaar/WoodGood/issues/1248)
- **Enderscape** (INCLUDED): Still missing a shelf from either **Copper Age Backport** or **Another Furniture** for MURUBLIGHT - [#1256](https://github.com/MehVahdJukaar/WoodGood/issues/1256), [#1199](https://github.com/MehVahdJukaar/WoodGood/issues/1199), [#970](https://github.com/MehVahdJukaar/WoodGood/issues/970)
  - NOTE: the solution to fix the issue above was not correct, MURUBLIGHT_SHELF will be added without any exception   

### ADDED:
- **Every Compat** (C): Added tag, `#c:ladders` to all of supported mods' LADDERS - [#1246](https://github.com/MehVahdJukaar/WoodGood/issues/1246)
- **Woodworks** (NF): added missing tags to blocks - related to [#1238](https://github.com/MehVahdJukaar/WoodGood/issues/1238)
- **Curiosities** (NF): Made an exception for built-in supported Wood Mods:  
  - atmospheric
  - autumnity
  - environmental
  - gardens_of_the_dead
  - mynethersdelight
  - upgrade_aquatic
- **Beautiful Campfires** (C): Added tags, `#toughasnails:heating_blocks` or `#toughasnails:cooling_blocks` to campfires or soul_campfires - [#1254](https://github.com/MehVahdJukaar/WoodGood/issues/1254)
- **The Twilight Forest** (NF): Added DRYING_RACK - [#1253](https://github.com/MehVahdJukaar/WoodGood/issues/1253)

---

## v2.11.40

### FIXES:
- **Every Compat** (C): 
  - Fixed a SERVER Crash when `onItemTooltip()` is executed - This should be only on CLIENT
  - Corrected the method that grab WoodType's READABLE LANG for Chests' LANG - [#1233](https://github.com/MehVahdJukaar/WoodGood/issues/1233)
  - Improved the code where items are still being added to Creative-Tab & REI/EMI/REI when an item is disabled in `everycomp-entries.toml` - [#1239](https://github.com/MehVahdJukaar/WoodGood/issues/1239)
- **More Crafting Table For Forge** (INCLUDED): Due to **Biomes O' Plenty**'s 3 WoodTypes that prevented **No Man's Land**'s 3 WoodType from being generated - [#1237](https://github.com/MehVahdJukaar/WoodGood/issues/1237)
  
---

## v2.11.39

### CHANGES:
- **Every Compat** (C): Tweaks in codes for nullable fix - @MehVahdJukaar

### FIXES: 
- **Beauitful Campfire** (C): Oudated recipe generation (using format from 1.20) & Updated for 1.21 - [#1236](https://github.com/MehVahdJukaar/WoodGood/issues/1236)
- **Every Comp** (c): Fixed an issue that would prevent block models gen for certain models

---

## v2.11.38

### CHANGS:
- **The Twilight Forest** (NF): Updated the Creative Tab's resourcelocation

### ADDS: 
- **Farmer's Delight**: Updated cabinet's textures with **Darker Depths**  - @Derp via Discord (Ported from 1.20)

### DISABLED:
- **Workshop For Handsome Adventurer** - At DEV's request & it's not 100% compatible with **EveryCompat**

---

## v2.11.37

### FIXES: 
- **The Twilight Forest** (NF): Fixed the tags not being loaded for all blocks. - [#1226](https://github.com/MehVahdJukaar/WoodGood/issues/1226)
  - MORE DETAIL: in TwilightForest Module, a code was trying to add an ITEM to a tag meant for BLOCKS and this caused the tags to be not loaded when loading into the world
- **Every Compat** (EXCLUDED): **The Twilight Forest's mangrove** with **The Twilight Forest's hollow_log** - Updated the code to prevent the duplicated block

---

## v2.11.36

### FIXES: 
- **Macaw's Doors** (FB): Fixed the outdated ResourceLocation for Creative Tab. 
  - NEOFORGE is fine as is.
- **The Twilight Forest** (NF): Added missing tags to all blocks - [#1223](https://github.com/MehVahdJukaar/WoodGood/issues/1223)
- **Abnormal's Woodworks** (NF): Fixed the missing LANG for `chest` & `trapped_chest` on SERVER side - [#1222](https://github.com/MehVahdJukaar/WoodGood/issues/1222) 

### ADDS
- **Every Compat** (C): Added a new config in `everycomp-hazardous.toml` with ENABLE_FRAMED_BLOCKS_BLACKLIST

---

## v2.11.35

### ADDS: 
- **More Sniffer Flowers** (IT): Corrected vivicus_log's texture - [#1219](https://github.com/MehVahdJukaar/WoodGood/issues/1219) 
  
### FIXES:
- **Every Compat** (C): Fixed the tab being null during the laucnhing with **The Twilight Forest** - [#1220](https://github.com/MehVahdJukaar/WoodGood/issues/1220) 

---

## v2.11.34

### CHANGES: 
- **Tech Reborn** (IT): Corrected the wrong texture used for `techreborn:rubber_log` - [#1217](https://github.com/MehVahdJukaar/WoodGood/issues/1217)
- **Regions Unexplored** (NF): Upated an outdated ResourceLocation key for Creative Tab via **Neoforge** (**Fabric** is fine)

### FIXES
- **Every Compat** (C): Fixed the crash in the `CYCLE_ITEM_RENDER` with "null check" when opening inventory (Either Creative Mode or with EMI++) in some certain cases - [#1215](https://github.com/MehVahdJukaar/WoodGood/issues/1215)
- **Macaw's Paths & Pavings** (FB): Temporarily fix the incorrect ResourceLocation for Creative Tab (this is an issue on **Macaw's Paths & Pavings**), This is already reported - [#1216](https://github.com/MehVahdJukaar/WoodGood/issues/1216)
  - Whenever the fix is applied in the said mod, then the module will automatically use the correct ResourceLocation.

---

## v2.11.33

### CHANGES: 
- **Every Compat** (C): 
  - Fixed **Chipped** or **Macaw** for `door` and `trapdoor` not being generated when **Framed Blocks** is installed - [#1206](https://github.com/MehVahdJukaar/WoodGood/issues/1206)
  - Added a tag, `#create:chest_mounted_storage` to all chests & Fixed transport item pipe or similar not connecting to EveryCompat's chests
  - Improved the `CYCLE_ITEM_RENDER` class where it is iterating every **Every Compat**'s children via **Every Compat**'s tab. If a WoodType or DecorativeType / FurnitureType is diabled, then it won't shown on the tab - [#1203](https://github.com/MehVahdJukaar/WoodGood/issues/1203)

### FIXES:
- **Macaw's Fences & Walls** (FB): Fixed outdated ResourceLocation for Creative Tab

### NEW SUPPORTED MODS:
- **Curiosities!** (NF)

---

## v2.11.32

### CHANGES: 
- **Every Compat** (C): Removed 2 configs due to a misunderstood request

---

## v2.11.31

### CHANGES: 
- **Every Compat** (C): Updated the logic to include Items into EC's tab and also mod's tab, too. 
  - NOTE: if you want to disable either EC's or Mod's. You can use `everycomp-common.toml` and find `creative_tab` for EC or `no_mod_creative_tab` for Mod
  - Updated a method for recipe generation to fix the missing recipe for **Gems Realm** with **Create** - [GemsRealm#54](https://github.com/Xelbayria/GemsRealm/issues/54)
  - Added 2 new configs: [#1203](https://github.com/MehVahdJukaar/WoodGood/issues/1203)
    - `DISABLE_CYCLE_ITEM_RENDERER` - disable creative-tab from showing the iteration of every item from Wood-Good
    - `CREATIVE_TAB_ICON` - Choose one item (can be from Wood-Good or Minecraft) to replace the icon instead of iterating every item from Wood-Good

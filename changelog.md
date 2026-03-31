<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<h2><div style="text-align: center; margin-bottom: 10px;">LEGENDS</div></h2>

<div style="text-align: left;">

* **(C)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(NF)**: NEOFORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

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

---

## v2.11.30

### CHANGES:
- **Feywild** (IT): Removed the entries for all of logs' ResourceLocation for textures as it's no longer needed
- **Every Compat** (C): 
  - Corrected the wrong logic for `everycomp-hazardous.toml`'s module-blacklist or entryset-blacklist  (it wasn't working properly)
  - Improved the logic for other blacklist config so blocks/items can get excluded.
  - Improved error logging for debugging with **Stone Zone** or **Gems Realm** making it easy to know what's the cause
- **Enderscape** (INCLUDED): WoodType-`murublight` with Supported_Mod-**Copper Age Backport** - [#1199](https://github.com/MehVahdJukaar/WoodGood/issues/1199)

---

## v2.11.29

### UPDATED: 
- **Every Compat** (C): Updated 2 Classes: CompatModule.java & EveryCompat.java to fix the crash with **Gems Realm**  
- **\[Let's Do] Meadow** (IT): Added `meadow:alpine_birch_leaves`'s ResourceLocation for the correct location
  - NOTE: This fixed the error log and wrong texture on **Chipped**'s leaves  
- **Macaw's Furniture** (C): Added the missing BlockEntity to `counter` and its base block is `oak_counter`
    - NOTE: this fix the crash when you place `counter` of any WoodType (this was reported via discord)

---

## v2.11.28

### UPDATED: 
- **Macaw's Furniture** (C): Added the missing BlockEntity to `strippedCounter` and its base block is `stripped_oak_counter`
  - NOTE: this fix the crash when you place `strippedCounter` of any WoodType (this was reported via discord)
- **Excessive Building** (FB): `v4.0.0` or newer will be no longer supported. only `v3.3.10` is still supported
  - ANOTHER NOTE: Moved to COMMON, so it can be used with Sinytra-Connector on Neoforge
- **EveryCompat** (C): Updated a deprecated method to use a new method related to Recipe Generation

---

## v2.11.27

### UPDATED: 
- **LANG**: Updated JA_JP by @HayaKoh-WeldyAlin - [#1179](https://github.com/MehVahdJukaar/WoodGood/pull/1179)
- **Quark** (NF): Fixed chests' texture not using the custom textures

---

## v2.11.26

### UPDATED: 
- **Beautify! aka Beautify Decorate** (NF): Fixed an error on startup with another mod: **Beautify Refoxed**
  - NOTE: **Beautify Refoxed** from v1.9.0 onward (according to DEV) will have a built-in modules for **Every Compat** for NEOFORGE & FABRIC
    - **Beautify Refabricated (BRF)** from v2.0.0 onward - **Every Compat** will use **BRF**'s module instead of its own module 

---

## v2.11.25

### UPDATED: 
- **Every Compat** (C): Improved classes & Made a few changes in API classes
- **Quark** (NF): Fixed the missing recipe for CHESTS using PLANKS (in quark's version for 1.21.1, the recipe was moved to a different location) - [#1164](https://github.com/MehVahdJukaar/WoodGood/issues/1164) 
- **MrCrayfish's Backpacked** (C): Updated to support v3.0.0+ - [#1165](https://github.com/MehVahdJukaar/WoodGood/issues/1165)
  - Older than v3.0.0 will be no longer supported. That included the BETA version, too.

### NEW:
- **Copper Age Backport** (C)

#### DEV:
if your mod has a built-in module for Every Compat, change the superparent class from `SimpleModule` to `EveryCompatModule`

---

## v2.11.24

### UPDATED: 
- **Chipped** (C): Fixed crash with **Productive Trees** (Ported from 1.20.1)
- **Every Compat** (C): 
  - Fixed an error log from latest.log with **F-ing Load My Tags** - [#1155](https://github.com/MehVahdJukaar/WoodGood/issues/1155)
  - Improved some error loggings to make the debugging a unique crash much easier and clearer
- **Regions Unexplored** (C): Updated to support v0.5.7 & Fixed the crash - [#1160](https://github.com/MehVahdJukaar/WoodGood/issues/1160)

---

## v2.11.23

### UPDATED: 
- **Every Compat** (C): Added a Christmas texture to All of Woodworks' chests from Dec 24th to 26th - [#1150](https://github.com/MehVahdJukaar/WoodGood/issues/1150) 
- **Chipped** (INCLUDED):
    - Vanilla WoodTypes except OAK - [#1136](https://github.com/MehVahdJukaar/WoodGood/issues/1136)
    - Supported 11 new Blocks for LeavesType
- **Quark** (NF): Fixed the missing hedge for **Oh The Biomes We've Gone**'s palo_verde

### ADDED:
- **Nature's Spirit** (IT): joshua_leaves' correct ResourceLocation

---

## v2.11.22

### UPDATED: 
- **Every Compat** (C): Fixed the `#c:chests/wooden`'s tag generation issue - [#1145](https://github.com/MehVahdJukaar/WoodGood/issues/1145) & also related to [#1143](https://github.com/MehVahdJukaar/WoodGood/issues/1143)

---

## v2.11.21

### UPDATED: 
- **Macaw's Furniture** (C): Fixed bookshelf & stripped_bookshelf not providing cnchantment to enchanting_table 
- **Ars Nouveau** (IT): Changed the texture of blue_archwood_log to use archwood_log textures
- **Every Compat** (C): 
  - Fixed the tag with namespace "c:" like `#c:chests` not loaded first time into the world but is loaded second time into the world - [#1143](https://github.com/MehVahdJukaar/WoodGood/issues/1143)
  - Fixed the tags via `GENERATE_BLOCKTYPE_TAGS` not being loaded first time into the world - Related to [#1119](https://github.com/MehVahdJukaar/WoodGood/issues/1119) and also above, too
  - Added new config, USE_AN_EXTERNAL_RESOURCE_PACK to allow EC base off the textures using external Resource Pack instead of Mod's
- **Chipped** (C): Cleanup & removed extra codes that aren't needed
- **Macaw's Stairs** (C): Updated the drop method for oak_balcony, so the error via latset.log about not finding the oak_balcony's loot_table file won't show up  

---

## v2.11.20

### UPDATED: 
- **Every Compat** (C): Fixed an crash related to the config, `GENERATE_BLOCKTYPE_TAGS` in `everycomp-common.toml` - [#1135](https://github.com/MehVahdJukaar/WoodGood/issues/1135)
    - ADDITIONAL-INFO: diagonal_fences won't be included in the tags

---

## v2.11.19

### UPDATED: 
- **Every Compat** (C): Added a new config, `GENERATE_BLOCKTYPE_TAGS` in `everycomp-common.toml`
  - NOTE: what it does is Generate a tag containing every blocktype with its children. It's Useful for datapack makers and Iris since 1.8 that can use tags for shaders
  - it's on by default 

---

## v2.11.18

### UPDATED: 
- **Macaw's Furniture** (C): Supporting 2 new blocks, `oak_kitchen_sink` & `stripped_oak_kitchen_sink`
- - **Every Compat** (C): Added a new config, `NO_MOD_CREATIVE_TAB` in `everycomp-common.toml` - [#1113](https://github.com/MehVahdJukaar/WoodGood/issues/1113)
- NOTE: what it does is not adding items to Mod's own tab in Creative Mode if NO_MOD_CREATIVE_TAB is set to `true`

---

## v2.11.17

### UPDATED: 
- **Create** (C): Corrected the tab where EC's items are being added into
- **Quark** (NF): Updated to support v4.1-467.3568 or newer
  - <span style="color: YELLOW;">NOTE: Quark is currently in BETA and is not out on public yet.</span>
- **Macaw's Furniture** (C): Added 6 new cabinet blocks 
  - <span style="color: RED;">NOTE: is no longer supporting the older version than v3.4.0</span>
- **Cobblemon's Legendary Monuments** (EXCLUDED): The WoodType, `legendarymonuments:distortion` is blacklisted because its texture is a 32x32

---

## v2.11.16

### UPDATED: 
- **Unusual Furniture** (NF): Forgot to mark one method to be `OnlyIn(Dist.CLIENT)` - FIXED issue with SERVER not working properly

---

## v2.11.15

### UPDATED: 
- **Unusual Furniture** (NF): 
  - Fixed Railing & Drawer's missing textures - [#1107](https://github.com/MehVahdJukaar/WoodGood/issues/1107)
  - Fixed the crash when placing table, coffee_table, and also other 3 blocks - [#1108](https://github.com/MehVahdJukaar/WoodGood/issues/1108)
- **Woodworks** (NF): Fixed missing saw recipes - [#1102](https://github.com/MehVahdJukaar/WoodGood/issues/1102)
- **Every Compat** (EXCLUDED): **Regions Unexplored**'s _ALPHA_ is blacklisted because it replaced **Minecraft**'s _OAK_
- **Regions Unexplored** (C): Added a missing recipe for _branch_, crafting 4 sticks from branch - [#1106](https://github.com/MehVahdJukaar/WoodGood/issues/1106)

### ADDED:
- **Unusual Furniture** (NF): Supporting new block: shelf - @axtrough

---

## v2.11.14

### UPDATED:
- **Farmer's Delight** (COMPAT): If one or more of **Farmer's Cutting** mods is installed, then the cutting_board recipe generation will be skipped 
- **Every Compat** (C): New feature in `everycomp-hazardous.toml` where you can blacklist a specific WoodType block or leavesType block
- **Chipped** (C): Fixed the missing textures for nailed_log & reinforced_log - [#1094](https://github.com/MehVahdJukaar/WoodGood/issues/1094)
- **Blockus** (FB): 
  - Added the missing properties to _grate_ - [#1090](https://github.com/MehVahdJukaar/WoodGood/issues/1090)
  - Updated hedges' LANG in EN_US - [#1093](https://github.com/MehVahdJukaar/WoodGood/issues/1093)
  - hedges' tag, `#blockus:small_hedges` is now `#blockus:hedges` - [#1093](https://github.com/MehVahdJukaar/WoodGood/issues/1093)

### ADDED:
- **Unusual Furniture** (NF): Supporting v1.1.1+ 
- **Every Compat** (C): Added tag, `#minecraft:non_flammable_wood` & `#minecraft:strider_warm_blocks` to all blocks for 4 WoodTypes - [#1091](https://github.com/MehVahdJukaar/WoodGood/issues/1091)
  - regions_unexplored:brimwood
  - regions_unexplored:cobalt
  - regions_unexplored:dead
  - regions_unexplored:yellow_bioshroom

---

## v2.11.13

### UPDATED: 
- **Blockus** (FB): Updated to support **v2.9.11+** - [#1085](https://github.com/MehVahdJukaar/WoodGood/issues/1085), [#1086](https://github.com/MehVahdJukaar/WoodGood/issues/1086)
  - NOTE: the **_older version_** than **_v2.9.11_** will be no longer supported. 
    - REASON: the ResourceLocation for some blocks' textures has changed
- **Chipped** (C): Fixed the crash due to texture generation - [#1089](https://github.com/MehVahdJukaar/WoodGood/issues/1089), [#1088](https://github.com/MehVahdJukaar/WoodGood/issues/1088)

---

## v2.11.12

### UPDATED: 
- **Chipped** (C): Added the missing textures for damaged_oak_log, nailed_oak_log, reinforced_oak_log (all top texture) - [#1083](https://github.com/MehVahdJukaar/WoodGood/issues/1083)

---

## v2.11.11

### UPDATED: 
- **Every Compat** (C): Updated to support Configured - [#1082](https://github.com/MehVahdJukaar/WoodGood/issues/1082) 
- **Decorative Blocks** (C): Updated to support **Decorative Blocks Reborn v6.0.1+** - [#1080](https://github.com/MehVahdJukaar/WoodGood/issues/1080)
- **Create** (NF): Changed the renderType of windows & window_panes to CUTOUT_MIPPED

### ADDED:
- **LANG** (en_us): missing LANG for configuration - [#1082](https://github.com/MehVahdJukaar/WoodGood/issues/1082)

---

## v2.11.10

### UPDATED:
- **Farmer's Delight** (C): Fixed the cutting recipe not being generated - [#1038](https://github.com/MehVahdJukaar/WoodGood/issues/1038)
- **Quark** (C): Ported the HedgeRecipe Update from 1.20.1 and it's no longer brittle code.
- **Chipped** (C): Improved textures & Corrected a few incorrect textures for logs

### DEV:
- **Every Compat** (C): 
  - Utility Classes have been moved to net.mehvahdjukaar.every_compat.misc
  - `TextureUtility` are renamed to `UtilityTexture` 
  - `TagUtility` are renamed to `UtilityTag` 
  - `RecipeUtility` are renamed to `UtilityRecipe` 
  - `Utility` are renamed to `UtilityMisc`

---

## v2.11.9

### UPDATED: 
- **Every Compat** (C): Fixed all Supported mods' missing model files in **Stone Zone** - [#158](https://github.com/MehVahdJukaar/StoneZone/issues/158)
- **LANG** (en_us): Corrected the LANG for sniffed_out's vessel to use "stem" instead of "log" with hollow from **Quark** or **The Twilight Forest**

---

## v2.11.8

### UPDATED:
- **Every Compat** (C): Fixed a rare bug where `everycomp-hazardous.toml` blacklist 2 modules in ENTRY_SET_BLACKLIST leads to a crash (Ported from 1.20.1)
    - **Variant Vanilla Blocks** & **Friends & Foes**
- **Farmer's Delight** (C): Fixed the missing recipe - [#1073](https://github.com/MehVahdJukaar/WoodGood/issues/1073)
- **Ecologics** (TEXTURES): Excluded **Chipped**'s item door from being overlayed with flowering_azalea

### ADDED:
- **TEXTURES** (C): Added the missing mask textures for **Chipped**'s log and stripped_log
  - **Valhelsia Structures** (NF): Updated the mask textures to use the new mask textures

---

## v2.11.7

### UPDATED:
- **Quark** (NF): Updated deprecated method to use new method, just texture stuff
- **Woodworks** (NF): Added, the missing tag, #minecraft:enchantment_power_provider to `bookshelf`
- **EveryCompat** (C): Updated EveryCompatAPI to fix some issues with mod's built-in modules not generating ITEM (experimental)
  - Related to: **Marioverse**
  - Updated again to improved some codes related to above

---

## v2.11.6

### UPDATED: 
- **Every Compat** (C): Updated a few API for **Stone Zone**

---

## v2.11.5

### UPDATED:
- **Friends & Foes** (C): Updated to use the new method from Moonlight for beehives' Point-Of-Interest
- **Variant Vanilla Blocks** (FB): Updated to use the new method from Moonlight for blocks' Point-Of-Interest  
- **Functional Storage** (NF): Fixed the missing item for all Drawers - [#1057](https://github.com/MehVahdJukaar/WoodGood/issues/1057) 
- **Farmer's Delight** (C): Fixed the cutting recipe - [#1066](https://github.com/MehVahdJukaar/WoodGood/issues/1066) - @MehVahdJukaar
- **Variant Vanilla Blocks** (C): Fixed its blocks not counting as Job Sites - [#900](https://github.com/MehVahdJukaar/WoodGood/issues/900)
- **EveryCompat** (C): Improved the error loggings for some rare cases
- **Dawn Of Time** (C): now support v1.6.3+ (no longer supporting the older version) - [#1060](https://github.com/MehVahdJukaar/WoodGood/issues/1060)
- **Chipped** (C):
    - one missed outdated method
    - Fixed the missing recipes
    - Fixed missing textures for `tile_windowed_>TYPE<_door`
    - Added mask textures for the remaining logs and stripped_logs. & also improved the textures

---

## v2.11.4

### UPDATED: 
- **Every Compat** (C): 
  - Improved the comments in configs, `everycomp-common.toml` and `everycomp-client.toml`
  - Added a new feature in `everycomp-hazardous.toml` to disable all of Wood Modules (same as what Library-Section can do)
  - Multiple Undocumented Changes
- **Refurbished Furniture (MrCrayFish)** (C): Fixed the incorrectly generated textures for **Region Unexplored**'s brimwood
- **Create** (C): Fixed a typo in "wallnut" to "walnut" for custom textures with **Ecologic**

---

## v2.11.3

### UPDATED:
- **Architect's Palette** (C): Updated for 1.21.1 - [#1040](https://github.com/MehVahdJukaar/WoodGood/issues/1040)
- **Valhelsia Structure** (NF): Fixed the missing recipes for all blocks except bundled_stripped_posts and bundled_posts
- **Storage Drawers** (C): Added the missing tag, `#storagedrawers:trim` to trim blocks - [#1029](https://github.com/MehVahdJukaar/WoodGood/issues/1029)
- **Macaw's Doors** (C): Added a new block: whispering_door
- **Macaw's Trapdoors** (C):
    - Added a new block: whispering_trapdoor
    - Added a mask texture for barrel_trapdoor
- **EveryCompat** (C): Corrected the wrong key in recipe generation that make the recipe not working - [#148](https://github.com/MehVahdJukaar/StoneZone/issues/148) & [#146](https://github.com/MehVahdJukaar/StoneZone/issues/146)

---

## v2.11.2

### UPDATED: 
- **EveryCompat** (C): Fixed the blocks from **StoneZone** not being dropped - [#143](https://github.com/MehVahdJukaar/StoneZone/issues/143)
- **Regions Unexplored** (C): Forgot to add the mask textures from 1.20.1 - [#1042](https://github.com/MehVahdJukaar/WoodGood/issues/1042)
- **Boatload (Abnormal)** (F): Added custom textures to improve the items' texture - @qwerty97475 (from Discord)

---

## v2.11.1

### UPDATED:
- Fixed an issue with config
- Added back a constructor that was recently modified hopefully restoring backward compat

---

## v2.11.0

### ADDED:
- Mod dynamic resources are now cached. Cache will regenerate once installed mod versions change, datapack changes or texture packs change
- Requires newest **Moonlight Lib v2.23.0** or above

### UPDATED: 
- **Woodster** (NF): Added missing RenderType to the ladders - [#992](https://github.com/MehVahdJukaar/WoodGood/issues/992)
- **Farmer's Delight** (COMPAT): Added **Undergarden Delight** 

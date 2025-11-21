<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<h2><div style="text-align: center; margin-bottom: 10px;">LEGENDS</div></h2>

<div style="text-align: left;">

* **(C)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(F)**: FORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat's blocks won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

### UPDATED: 
- **Macaw's Furniture** (C): Corrected the LANG for `kitchen_sink` & `stripped_kitchen_sink`
- **Every Compat** (C): Added a new config, NO_MOD_CREATIVE_TAB in `everycomp-common.toml` - [#1113](https://github.com/MehVahdJukaar/WoodGood/issues/1113)
  - NOTE: what it does is not adding items to Mod's own tab in Creative Mode if NO_MOD_CREATIVE_TAB is set to `true`

---

## v2.9.12

### UPDATED: 
- **Macaw's Furniture** (C): Added `oak_kitchen_sink`
- **Quark** (C): Fixed the missing recipe, `vertical_slab_revert` as it was a recipe generation failure that somehow caused missing slab's recipe - [#1110](https://github.com/MehVahdJukaar/WoodGood/issues/1110)
  - Possibly related issue: [#1103](https://github.com/MehVahdJukaar/WoodGood/issues/1103) - missing recipes from logs to planks

---

## v2.9.11

### UPDATED: 
- **Macaw's Door** (C): Fixed the whispering_door's missing item texture - [#1115](https://github.com/MehVahdJukaar/WoodGood/issues/1115)

---

## v2.9.10

### UPDATED: 
- **Macaw's Trapdoor** (C): Added new trapdoor, whispering_trapdoor
  - <span style="color: YELLOW;">NOTE: no longer supporting version older than **v1.1.4**</span>
- **Macaw's Door** (C): Added new door, whispering_door (Ported from 1.21.1)
  - <span style="color: YELLOW;">NOTE: no longer supporting version older than **v1.1.2**</span>
- **Create** (**FB**): Updated to support v6.0.0+ - [#1114](https://github.com/MehVahdJukaar/WoodGood/issues/1114)
- **Unusual Furniture** (NF): (Ported from 1.21.1)
    - Fixed Railing & Drawer's missing textures - [#1107](https://github.com/MehVahdJukaar/WoodGood/issues/1107)
    - Fixed the crash when placing table, coffee_table, and also other 3 blocks - [#1108](https://github.com/MehVahdJukaar/WoodGood/issues/1108) 
- **Every Compat** (C): 
  - Added a new config in `everycomp-hazardous.toml`, BLOCKS_BLACKLIST for blacklisting one or more EC's blocks
  - **mods.toml** (F): Updated to allow **V-Minus** between v1.0.0 and v3.1.2 to be installed
    - NOTE: v3.2.0 or newer have been tested and found to break recipe system 
- **Cobblemon's Legendary Monuments** (EXCLUDED): The WoodType, `legendarymonuments:distortion` is blacklisted because its texture is a 32x32 
- **Macaw's Furniture** (C): Added 6 new cabinet blocks
    - <span style="color: RED;">NOTE: is no longer supporting the older version than v3.4.0</span>
- **Building But Better** (C): Fixed the missing textures for some blocks with **Regions Unexplored** - [#1100](https://github.com/MehVahdJukaar/WoodGood/issues/1100)
- **LANG** (ja_jp): Updated by @Hayakoh-WeldyAlin - [PR#1096](https://github.com/MehVahdJukaar/WoodGood/pull/1096)

---

## v2.9.9

### UPDATED:
- **Boatload** (F): Fixed the missing textures for large_boat & furnace_boat
- **Building But Better** (C): Updated to support **2.0pre3** - [#1075](https://github.com/MehVahdJukaar/WoodGood/issues/1075)
  - NOTE: older than 2.0pre1 to 2.0pre2 won't be supported
  - NOTE: FORGE - You can still use v1.0.1 or v1.1.1 & FABRIC - still use v1.0.2
  - but it will be removed in the future when v2.0 is officially out of "Release Candidate"
- **Quark** (C): Improved hedge's condition to check if LeavesType has associated WoodType, then it will be generated
- **Builder's Delight** (F): Finished the unfinished recipes
- **Lightman's Currency** (F): Fixed the missing recipes for 3 of 4 blocks & Fixed bookshelf_traders' drops - [#1084](https://github.com/MehVahdJukaar/WoodGood/issues/1084)
- **Create** & **Chipped** (C): glasses/windows with The tags, `#forge:glass` or `#c:glass` have been removed and will be only added if **Botania** is installed - [#1076](https://github.com/MehVahdJukaar/WoodGood/issues/1076)
  - glass_pane/window_panes also have `#forge:glass_pane` or `#c:glass_pane` removed for the same reason

### ADDED:
- **Farmer's Delight** (COMPAT): **Windswept's Delight**

### DEV:
- **Every Compat** (C):
    - Utility Classes have been moved to net.mehvahdjukaar.every_compat.misc
    - `TextureUtility` are renamed to `UtilityTexture`
    - `TagUtility` are renamed to `UtilityTag`
    - `RecipeUtility` are renamed to `UtilityRecipe`
    - `Utility` are renamed to `UtilityMisc`

---

## vv2.9.8

### UPDATED: 
- **Quark** (F): Fixed the recipe not being generated due to LeavesType's missing Associated WoodType and that halt the rest of other recipes generation - Related to [#1078](https://github.com/MehVahdJukaar/WoodGood/issues/1078)

---

## v2.9.7

### UPDATED: 
- **Every Compat** (C): Fixed the incorrect logic in texture generation that caused crash with **Chipped** with any Wood Mods - [#1077](https://github.com/MehVahdJukaar/WoodGood/issues/1077)

---

## v2.9.6

### UPDATED: 
- **Every Compat** (C): Improved the generation where a recipe generation failed can cause other recipes that do not get a chance to be generated and get skipped 
- **Quark** (F): Fixed crash with Hedge's recipe in the SERVER
- **Chipped** (C): Improved textures & Corrected a few incorrect textures for logs

---

## v2.9.5

### UPDATED: 
- **Every Compat** (C): Fixed a rare bug where `everycomp-hazardous.toml` blacklist 2 modules in ENTRY_SET_BLACKLIST leads to a crash
  - **Variant Vanilla Blocks** & **Friends & Foes**

---

## v2.9.4

### UPDATED: 
- **Every Compat** (C): 
  - Corrected a mistake for texture to be used (before it was using planks' texture for LOG, not log's texture)
  - Improved the error loggings for some rare cases
- **Chipped** (C): 
  - one missed outdated method
  - Fixed the missing recipes
  - Fixed missing textures for `tile_windowed_>TYPE<_door`
  - Added mask textures for the remaining logs and stripped_logs. & also improved the textures
- **Storage Drawers** (C): Updated to support `v12.14.3` & Fixed the drawer's tooltip showing `8 stacks per drawer`, it's corrected to 32 like vanilla drawers
- **Create** (C): Updated to have one common class for better maintenance for both FABRIC & FORGE 

---

## v2.9.3

### UPDATED: 
- **Every Compat** (C):
  - Improved the logging in case of crash for some unique cases
  - Improved the error message with VMinus - it's still marked as incompatible - Please look at [VMinus#34](https://github.com/lixxir/VMinus/issues/34) for details
  - Added a new feature in `everycomp-hazardous.toml` to disable all of Wood Modules (same as what Library-Section can do)
  - Multiple Undocumented Changes
- **Create** (C): Fixed a typo in "wallnut" to "walnut" for custom textures with **Ecologic** - @22858
- **Refurbished Furniture (MrCrayFish)** (C): Added the missing tooltips to ceiling_fan
- **Builder's Crafts & Additions** (F): Fixed the crash with **Excessive Building** - [#1048](https://github.com/MehVahdJukaar/WoodGood/issues/1048)
- **Storage Drawers** (C): Added the missing tag, `#storagedrawers:trim` to trim blocks - [#1029](https://github.com/MehVahdJukaar/WoodGood/issues/1029)

---

## v2.9.2

### UPDATED: 
- **Every Compat** (C): 
  - Corrected the logic for texture not being generated with **Gems Realm** & **Macaw's Bridges** - [#31](https://github.com/Xelbayria/GemsRealm/issues/31)
  - Improved Recipe Generation for **Gems Realm** to correct the recipes with wrong ingredients
- **Boatload (Abnormal)** (F): Added custom textures to improve the items' texture - @qwerty97475 (from Discord)

---

## v2.9.1

### ADDED: 
- **Farmer's Delight** (COMPAT): **Undergarden's Delight** - [#1023](https://github.com/MehVahdJukaar/WoodGood/issues/1023)
- **Smidgeon O' Bliss** (F): Fixed the missing textures on counter - [#1028](https://github.com/MehVahdJukaar/WoodGood/issues/1028)
- **LANG**: en_us - Corrected "Counter" to "Countertop" for **Smidgeon O' Bliss**'s counters - [#1028](https://github.com/MehVahdJukaar/WoodGood/issues/1028)
- **Quark** (F): Updated the hedges' condition - required log from LeavesType
  - NOTE: this fixed the hedges not being generated for LeavesType with logs
- **\[Let's Do\] Meadow** (IT): PINE is not needed in (IT) anymore - [#1024](https://github.com/MehVahdJukaar/WoodGood/issues/1024)
  - NOTE: Fixed the missing texture for **Regions Unexplored**'s shrub with PINE
- **Chipped** (C): Fixed a texture not being texture for bared_glass

---

## v2.9.0

### UPDATED:
- **EveryCompat** (COMMON): 
  - Palette generation is now cached and a lot faster
  - Improved API
- **Quark** (INCLUDED): vertical_planks with **Garden Of The Dead**'s whistlecane - [#1021](https://github.com/MehVahdJukaar/WoodGood/issues/1021)

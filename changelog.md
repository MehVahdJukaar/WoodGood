<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: baseTexture;">

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

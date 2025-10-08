<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

*   (C) = FORGE & FABRIC
*   (FB): FABRIC
*   (NF): NEOFORGE
*   (IT): Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
*   (COMPAT): Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
*   (INCLUDED): The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
*   (EXCLUDED): The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

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
s
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

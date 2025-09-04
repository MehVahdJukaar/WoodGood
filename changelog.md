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
- **EveryCompat** (C): Fixed the blocks from **StoneZone** not being dropped - [#143](https://github.com/MehVahdJukaar/StoneZone/issues/143)
- **Regions Unexplored** (C): Forgot to add the mask textures from 1.20.1 - [#1042](https://github.com/MehVahdJukaar/WoodGood/issues/1042)
- **Boatload (Abnormal)** (F): Added custom textures to improve the items' texture - @qwerty97475 (from Discord)
- **Storage Drawers** (C): Added the missing tag, `#storagedrawers:trim` to trim blocks - [#1029](https://github.com/MehVahdJukaar/WoodGood/issues/1029)

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

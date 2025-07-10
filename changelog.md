<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

*   (COMMON) = FORGE & FABRIC
*   (FB): FABRIC
*   (NF): NEOFORGE
*   (IT): Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
*   (COMPAT): Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
*   (INCLUDED): The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
*   (EXCLUDED): The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

### UPDATED: 
- **EveryCompat** (COMMON): Corrected version to v2.10.13
- **Chipped** (COMMON): Added tags, #c:glass & #c:glass_panes to glasses & glass_panes blocks
- **Bibliocraft Legacy** (NF): Corrected the ResourceLocation of BlockEntityType for fancy_armor_stand

---

## v2.10.12

### UPDATED: 
- **Bibliocraft Legacy** (NF): Added the missing BlockEntityType to 3 blocks to fix the crash - [#987](https://github.com/MehVahdJukaar/WoodGood/issues/987)

---

## v2.10.11

### UPDATED: 
- **Every Compat** (INCLUDED): minecraft:pale_oak from **Perfect Parity: The Garden Awakens Edition** blc it's a vanilla WoodType
- **VariantVanillaBlocks** (COMMON): Ported the updated mask texture from 1.20.1 for crafting_table

### NEW SUPPORTED MOD:
- **Bilbiocraft Legacy** (NF) - Let me know if there are more Compat Mods - @xelbayria

---

## v2.10.10
### UPDATED: 
- **Re: Deco** (NF): Fixed 3 more blocks' incorrect droppings 
- **Variant Crafting Tables** (NF): Improved the mask texture for crafting_table
- **Variant Vanilla Blocks** (COMMON): Ported the updated mask texture from 1.20.1 for crafting_table

### ADDED:
- **Perfect Parity: The Garden Awakens Edition** (INCLUDED): pale_oak with any supported mods

---

## v2.10.9

### ADDED:
- **Another-Furniture** (INCLUDED): murublight_shelf with **Enderscape** - [#970](https://github.com/MehVahdJukaar/WoodGood/issues/970)

### UPDATED: 
- **LANG**: us_en - Corrected the name for full storage drawer
- **Macaw's Stairs** (COMMON): Fixed the balcony's duplicated dropping
- **Handcrafted** (COMMON): Updated the outdated code to account for the updated code in **Moonlight Lib** since v2.19.2
- **More Variant Chests (LieOnLion)**: Ported the updated code from 1.20.1
- **MOVED TO COMMON**:
  - **More Variant Chests** (LieOnLion)
  - **More Crafting Tables** (LieOnLion)
- **Variant Vanilla Blocks** (COMMON): 
  - Fixed the chests' incorrect texture as an item in inventory
  - Corrected chests' mask texture
- **Chipped** (COMMON): 
  - [#968](https://github.com/MehVahdJukaar/WoodGood/issues/968) - is fixed by **Moonlight Lib v2.19.4**
  - Fixed the stripped_log's texture with "Palette size can't be 0" in the latest.log for some Wood Mods (ex: **Productive Trees**)
- **Corail Pillar** (FG): Corrected blocks' incorrect recipe and removed an code that manually added recipes for 2 blocks
- **Every Compat**: Fixed an concurrency issue (multi-thread stuff) with `addOtherCompatMod()` method - @MehVahdJukaar
- **Re: Deco** (NF): Fixed 3 blocks' incorrect droppings

---

## v2.10.8

### UPDATED: 
- **Woodster** (NF): 
  - Fixed the chiseled_bookshelf's missing texture - [#955](https://github.com/MehVahdJukaar/WoodGood/issues/955)
  - ladders' properties
  - chiseled_bookshelf's properties

### ADDED: 
- **Twilight Delight** (COMPAT): **Twilight Forest** & **Farmer's Delight** - @MehVahdJukaar

### NEW SUPPORTED MOD: 
- **More Chest Variants (LieonLion)** (FB) - @Dustine Camacho*

---

## v2.10.7

### UPDATED:
- **Every Compat** (FB): Fixed the crash when either creating a world or loading into a world with **MrCrayFish's Refurbished Furniture** - [#927](https://github.com/MehVahdJukaar/WoodGood/issues/927) 

---

## v2.10.6

### UPDATED: 
- **LANG**: zh_cn - @ChuijkYahus
- **Valhelsia Structures** (NF): 
  - Fixed the crash when posts or cutPosts are being stripped - [#924](https://github.com/MehVahdJukaar/WoodGood/issues/924)
  - Improved & Corrected the side of bundled_posts' texture

### ADDED: 
-  Frightful Winter **(IT)**: snowy_pine_leaves - Fixed [#923](https://github.com/MehVahdJukaar/WoodGood/issues/923) 

---

## v2.10.5

### UPDATED:
- **Variant Vanilla Blocks** (COMMON): Disabled addBlocksToPOI() and restored the old code for Beehives' POI (Point of interest) to act as bee's home
- **Regions Unexplored** (FABRIC): Fixed the shrub & branches' RenderType not being transparent - [#913](https://github.com/MehVahdJukaar/WoodGood/issues/913)

### ADDED: 
- **Create** (COMMON): Custom Textures for windows & window_panes with **Ecologics** & **Biomes O' Plenty** - @leftchaotix (from discord)

---

## v2.10.4

### UPDATED:
- **Chipped** (COMMON): Updated an outdated recipe system & Fixed [#909](https://github.com/MehVahdJukaar/WoodGood/issues/909)

---

## v2.10.3

### UPDATED:
- **Create** (NF): Changed RenderType to TRANSLUCENT for windows & window_panes
- **Dramatic Doors Macaw** (COMMON): Fixed the outdated tab that caused the crash
  - NOTE: Dramatic Doors + Macaw's Doors


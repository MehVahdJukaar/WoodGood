<div style="text-align: center; border: 1px solid yellow; padding: 10px;">

<div style="text-align: center; margin-bottom: 10px;">

### LEGEND

</div>

<div style="text-align: left;">

* **(COMMON)**: FORGE & FABRIC
* **(FB)**: FABRIC
* **(F)**: FORGE
* **(IT)**: Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture
* **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't included for the Supported Mod and the Wood Mod
* **(INCLUDED)**: The block is not generated because a Wood Mod already have the same block as the supported mod will be generated
* **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason

</div>

</div>

### UPDATED: 
- **EveryCompat** (COMMON): Simplified many codes and tags to make it more readable, Improved other codes.
- **Create** (COMMON): Removed unneeded Tags: `#forge:glass/silica` & `#forge:glass/colorless` or similar for glass and glass_pane - [#1007](https://github.com/MehVahdJukaar/WoodGood/issues/1007)
- **Dawn Of Time** (FB): now supporting v1.5.16, BUT no longer will support older version - [#1019](https://github.com/MehVahdJukaar/WoodGood/issues/1019)
- **Luminous Nether** (IT): Added goldenstem & its stripped_log, wood - [#1017](https://github.com/MehVahdJukaar/WoodGood/issues/1017)

---

## vv2.8.15

### UPDATED: 
- **Every Compat** (COMMON): Fixed the SERVER crash related to `everycomp-hazardous.properties` config - [#1006](https://github.com/MehVahdJukaar/WoodGood/issues/1006)

---

## v2.8.14

### UPDATED: 
- **Every Compat** (COMMON): 
  - Improved the _everycomp-hazardous_ config
  - Improved code check for Vanilla Wood - @MehVahdJukaar
- **XercaMod** (FORGE): Ported the finished recipe system from 1.21.1 - [#1002](https://github.com/MehVahdJukaar/WoodGood/issues/1002)
- **Macaw's Holiday** (IT): Added snowy_oak_leaves & snowy_spruce_leaves - [#980](https://github.com/MehVahdJukaar/WoodGood/issues/980)

### LANG 
- **EN_US**: Corrected LeavesType from **Quark** - [#1005](https://github.com/MehVahdJukaar/WoodGood/issues/1005)

### OTHERS
<span style="color: YELLOW;">FOR MODPACK DEVS:</span> The file, `everycomp-hazardous.properties` have been changed to `everycomp-hazardous.toml` with improved configs
<br>If you've used it to disable a module, then you can use the mod-ID of the disabled module and place it under "module" via the new file. 

---

## v2.8.13

### UPDATED:
- **EveryCompat** (COMMON): Updated SpriteHelper so it can be used by StoneZone & GemRealms to correct the incorrect texture related to (IT)
- **TAGS** (COMMON): Added tags to glasses & glass_panes with either #forge:glass & #forge:glass_panes but if it's fabric, then c:glass_block & c:glass_panes
  - **Chipped**
  - **Create**
- **LANG**: zh_cn - @libu2333

### NEW SUPPORTED MOD:
- **Unusual Furniture** (F)
- **Blockus** (FB) - Can be used with Sinytra Connector for FORGE

---

## v2.8.12

### UPDATED: 
- **EveryCompat** (COMMON): 
  - Excluded some blocks from Supported Mods due to FramedBlocks having a similar blocks - "torch"
  - Corrected the parameter in the wrong place to correct parameter - Case: **valhelsia_structures** + **Nature's Spirit**:joshua
    - In other words, The blocks from **Valhelsia Structures** will be included with joshua from **Nature's Spirit**
- **Dawn Of Time** (COMMON): Split into FABRIC & FORGE folder to fix the issue with Minecraft not launching properly

---

## v2.8.11

### UPDATED: 
- **Every Compat** (COMPAT): 
  - **Abnormal's Delight** - Fixed a typo in modId for **Caverns & Chasms**
  - **Ascended Quark** (COMPAT): **Quark** with **The Aether** & **Deep Aether**
- **Woodworks** (FG): Corrected `#blueprint:wooden_chiseled_bookshelf` to `#blueprint:wooden_chiseled_bookshelves` - [#977](https://github.com/MehVahdJukaar/WoodGood/issues/977)
- **More Chest Variant** (COMMON): Corrected OAK_CHEST & OAK_TRAPPED_CHEST to lower cases & fixed the crash blc of it

### NEW SUPPORTED MOD:
- **Smidgeon O' Bliss** (FG)
- **Storage Delight** (COMMON)
- **Nosiphus Furniture Mod** (FG)
- **Ultimate Car Mod** (FG)
- **Gensokyo Delight - Youkai's Homecoming** (FG)

---

## v2.8.10

### UPDATED: 
- **MOVED TO COMMON**:
  - **Variant Vanilla Blocks**
- **Re:Deco** (FG): Fixed the loot_table of pedestal, display_case, sword_mount not dropping fabric - [#969](https://github.com/MehVahdJukaar/WoodGood/issues/969)

---

## v2.8.9

### ADDED:
- **TEXTURE** (TEX): Applying the chest's texture for AP's twisted from **Quark** with **Architect's Palette (AP)** to **Woodworks** with **Architect's Palette**
- **Terrestria** (IT): stripped_yucca_palm's top texture
  
### UPDATED
- **Woodworks** (FG): Multiple Blocks' tags - [#965](https://github.com/MehVahdJukaar/WoodGood/issues/965) , #963, #
- **MOre Chest Variants** (FG): Fixed the chests' incorrect texture in inventory - [#966](https://github.com/MehVahdJukaar/WoodGood/issues/966)
- **Handcrafted** (COMMON): Updated for **Moonlight Lib**'s v2.14.9+ 
- **Regions Unexplored** (COMMON): The outdated codes to generate shrub's texture due to multi-thread code from **Moonlight lib**
- **MOVED TO COMMON**:
  - **More Chest Variants** (LieOnLion) - This also mean FABRIC version is supported 
  - **More Crafting Tables** (LieOnLion) 
- **Chipped** (COMMON): 
  - [#968](https://github.com/MehVahdJukaar/WoodGood/issues/968) - is fixed by **Moonlight Lib v2.14.11**
  - Fixed the stripped_log's texture with "Palette size can't be 0" in the latest.log for some Wood Mods (ex: **Productive Trees**)
- **Re:Deco** (FG): Fixed the loot_table of chairs, benches, stools not dropping fabric - [#969](https://github.com/MehVahdJukaar/WoodGood/issues/969)
- **Corail Pillar** (FG): Corrected blocks' incorrect recipe and removed an code that manually added recipes for 2 blocks
- **Every Compat**: Fixed an concurrency issue (multi-thread stuff) with `addOtherCompatMod()` method - @MehVahdJukaar

---

## v2.8.8

### UPDATED: 
- **LANG**: us_en - Corrected the name for full storage drawer - [#949](https://github.com/MehVahdJukaar/WoodGood/issues/949)
- **Quark** (COMMON): Added the missing tags to leaf_carpet - [#947](https://github.com/MehVahdJukaar/WoodGood/issues/947)
- **Macaw's Stairs** (COMMON): Fixed the balcony's duplicated dropping - [#950](https://github.com/MehVahdJukaar/WoodGood/issues/950)
- **Variant Vanilla Blocks** (COMMON): Fixed the chests' incorrect texture as an item in inventory - [#954](https://github.com/MehVahdJukaar/WoodGood/issues/954)

### ADDED: 
- **Abnormal's Boatload** with **Vanilla Backport** (TEX): boat & boat_with_chests - @qwerty9745 (via Discord)

---

## v2.8.7

### UPDATED: 
- **Quark** (FORGE): 
  - Fixed the Bookshelf's not dropping books wtih a normal axe - [#933](https://github.com/MehVahdJukaar/WoodGood/issues/933)
  - Added the missing `#minecraft:mineable/axe` to ladder - [#945](https://github.com/MehVahdJukaar/WoodGood/issues/945)
- **TEXTURE**: Improved the mask texture for crafting_table from **Variant Vanilla Blocks** & **Variant Crafting Tables** - [#942](https://github.com/MehVahdJukaar/WoodGood/issues/942)
- **Every Compat** (COMMON): 
  - More Updates for **GemsRealm** (It's currently 75% done) 
  - Improved some codes 
  - Improved tooltip via EN_US, "Mod: ???" to "Supported Mod: ???"

---

## v2.8.6

### UPDATE: 
- **LANG**: es_cl - @Ganbare-Lucifer

### ADDED: 
- **TEXTURES** (IT): **Luminous Nether**'s withered - [#929](https://github.com/MehVahdJukaar/WoodGood/issues/929)
- **Every Compat**: New CLASS for supporting the new addon, Gems-Realm
- **Dramatic Doors** (COMMON): **Arts And Crafts** as AlreadySupportedMods

---

## v2.8.5

### UPDATED: 
- **LANG**: zh_cn - @ChuijkYahus
- **Valhelsia Structures** (FORGE): Improved & Corrected the side of bundled_posts' texture

---

## v2.8.4

### UPDATED: 
- **FriendsAndFoes** (COMMON): Fixed the crash with **Variant Vanilla Blocks** -  [#921](https://github.com/MehVahdJukaar/WoodGood/issues/921)

---

## v2.8.3

### UPDATED: 
- **Lightman's Currency** (FORGE): Supporting v2.2.5.2+ 
  - <span style="color: RED;">NOTE: Older version of Lightman's Currency will be no longer supported</span>
- **MODULE** (FORGE): Re-enable Blocks Plus (Blocks+)

--- 

## v2.8.2

- **Every Compat**: Restored the old method used by an older FORGE version 

---

## v2.8.1

### UPDATED: 
- **Every Compat** (COMMON): Forgot to update the recipe system for **Stone Zone** to use the new Resource Generator


### INCOMPATIBLE:
- **VMinus Mod** (FORGE): From v2.7.19 onward, it is marked as INCOMPATIBLE
- **Very Many Players** (FORGE|FABRIC): it is marked as INCOMPATIBLE


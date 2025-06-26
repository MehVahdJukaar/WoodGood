<div style="text-align: center; border: 1px solid yellow; padding: 10px;">
  <div style="text-align: center; margin-bottom: 10px;">
    <h3>LEGEND</h3>
  </div>
  <div style="text-align: left;">
    <ul style="list-style-type: disc; padding-left: 20px;">
      <li>(COMMON): FORGE & FABRIC</li>
      <li>(FB): FABRIC</li>
      <li>(FG): FORGE</li>
      <li>(IT): Included Texture: Added the ResourceLocation of the missing textures required for blocks or generating a new texture</li>
      <li>(TEX): New hand-made texture for specific block or item</li>
    </ul>
  </div>
</div>

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
- **Re:Deco** (FG): Fixed the loot_table of chairs, benches, stools not dropping cushions - [#969](https://github.com/MehVahdJukaar/WoodGood/issues/969)
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


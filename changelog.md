| **Legends**                                                                                                                       |
|-----------------------------------------------------------------------------------------------------------------------------------|
| - **(C)**: FORGE & FABRIC                                                                                                         |
| - **(FB)**: FABRIC                                                                                                                |
| - **(FG)**: FORGE                                                                                                                 |
| - **(IT)**: Included Texture — added the ResourceLocation of the missing textures required for blocks or generating a new texture |
| - **(TEX)**: hand-made textures to improve the way a block looks                                                                  |
| - **(COMPAT)**: Create an exception for a compat mod. EveryCompat won't include the Supported Mod and the Wood Mod                |
| - **(INCLUDED)**: The block is not generated because a Wood Mod already has the same block as the supported mod will be generated |
| - **(EXCLUDED)**: The block is generated BUT it shouldn't be generated for a reason                                               |
| - **(UDBT)**: Undetected BlockTypes will be manually added                                                                        |
|                                                                                                                                   |

---
## v2.9.24

### FIXES:
- **Every Compat** (C): Improved/Tweaked the texture generation for **Chipped** (Backported from 1.21) - [#1270](https://github.com/MehVahdJukaar/WoodGood/issues/1270)
  
### CHANGES:
- **Woodworks** (NF): Tweaked the recipe generation to use Bamboo Recipes only for WoodType that are Bamboo-like

---

## v2.9.23

### CHANGES:
- **Macaw's Paths & Pavings** (FB): Removed the temp fix for Creative-Tab's ID since **Macaw's Path (FABRIC)**'s ID for creative_tab is fixed in v1.1.2 

### FIXES:
- **Boatload** (NF): Fixed the placed LARGE_BOAT's missing textures - [#1269](https://github.com/MehVahdJukaar/WoodGood/issues/1269)

---

## v2.9.22

### CHANGES:
- **Every Compat** (C): Clean out some old codes that are no longer needed - Due to Boatloads' new texture generation from `v2.9.21`
- **Farmer's Delight** (C): Updated the recipe generation for the new recipe system since FABRIC-v3.3.0+ or NEOFORGE-v1.3.0+ - [#1257](https://github.com/MehVahdJukaar/WoodGood/issues/1257)

---

## v2.9.21

### CHANGES:
- **Farmer's Delight** 
  - (TEX): Updated CABINETS' textures with **Quark** - @derp_gamer22 via Discord
- **Every Compat** (C): 
  - Improved the Duplication System & Added a few exception for blocks that should be included or excluded (Backported from 1.21.1) 
  - Improved Logging in latest.log for how many Only-Items are registered along with how many blocks are registered 
- **BoatLoad** (NF): Improved the texture generation for boats
- **Woodworks** (NF): Added a missing tag to CHISELED_BOOKSHELVES

### ADDED:
- **Every Compat** - Backported from 1.21.1 
  - (C): Added tag, `#c:ladders` to all of supported mods' LADDERS - [#1246](https://github.com/MehVahdJukaar/WoodGood/issues/1246)
  - (IT): Corrected the ResourceLocation for the following WoodTypes from:
    - **Alex's Caves** - thornwood_log & stripped_thornwood_log
    - **More Sniffer Flowers** - vivicus_log
    - **Tech Reborn** - rubber_log
    - **\[Let's Do] Meadow** - alpine_birch_leaves
    - **Nature's Spirit** - joshua_log & stripped_joshua_log

---

## v2.9.20

### CHANGES: 
- **Every Compat** (EXCLUDED): **The Twilight Forest's mangrove** with **The Twilight Forest's hollow_log** - Updated the code to prevent the duplicated block
- **Building But Better** (C): Updated to support `v2.0pre4` - [BBB#76](https://github.com/starfish-studios/Building-But-Better/issues/78)

### DISABLED:
- **Workshop For Handsome Adventurer** - At DEV's request & it's not 100% compatible with **EveryCompat**

---

## v2.9.19

### FIXES:
- **Macaw's Doors** (FB): Fixed the outdated ResourceLocation for Creative Tab.
  - NEOFORGE is fine as is.

### ADDS: 
- **Every Compat** (C): Added a new config in `everycomp-hazardous.toml` with ENABLE_FRAMED_BLOCKS_BLACKLIST
- **The Twilight Forest** (F): Added the missing tags to all blocks

---

## v2.9.18

### CHANGES: 
- **Feywild** (IT): Removed the entries for all of logs' ResourceLocation for textures as it's no longer needed
- **Every Compat**
  - (EXCLUDED): Excluded some blocks from supported mods if **Framed Blocks** is installed
  - (OTHERS): Backported some codes from 1.21.1

### FIXES
- **Chipped** (C): Fixed the manual texture generation from failing to generate textures for blocks with **Productive Trees** - [#1995](https://github.com/MehVahdJukaar/WoodGood/issues/1995)

---

## v2.9.17

### UPDATED: 
- **Every Compat** (C): Backported a feature from 1.21 that ensure the Blockstate files get modified and pointing at the correct model files for the blocks - [#1177](https://github.com/MehVahdJukaar/WoodGood/issues/1177)
  - Related to **Copper Age Backport**

---

## v2.9.16

### UPDATED:
- **Farmer's Delight**: Updated cabinet's textures with **Darker Depths**  - @Derp via Discord
- **LANG**: Updated JA_JP by @HayaKoh-WeldyAlin - [#1169](https://github.com/MehVahdJukaar/WoodGood/pull/1169)
- **Every Compat** (C): Improved error loggings further
  - _previous changelog (v2.9.15):_ Improved some error loggings to make the debugging a unique crash much easier and clearer
  - Fixed a bug in recipe generation that broke planks' recipe (from log to planks), this is related to **Building But Better** - [#1103](https://github.com/MehVahdJukaar/WoodGood/issues/1103)

### NEW:
- **Copper Age Backport** (C)

---

## v2.9.15

### UPDATED: 
- **Macaw's Stairs** (C): Remove an error log in latest.log where loot_table for oak_balcony cannot be found (Backported from 1.21.1)
- **Chipped** (C): Fixed crash with **Productive Trees**
- **Every Compat** (C):
  - Fixed an error log from latest.log with **F-ing Load My Tags** (Backported fixes from 1.21.1)  
  - Improved some error loggings to make the debugging a unique crash much easier and clearer

---

## v2.9.14

### UPDATED: 
- **Every Compat** (C): Improved the logic for Item Tags & Fixed an error log in latest.log with **F-ing Loading My Tags** - [#1155](https://github.com/MehVahdJukaar/WoodGood/issues/1155) 
- **Mosaic Carpentry** (F): Updated to support v1.3+

---

## v2.9.13

Merry Christmas! - Xel'Bayria

### UPDATED: 
- **Every Compat** (C): Added a new config, GENERATE_BLOCKTYPE_TAGS in `everycomp-common.toml`
    - NOTE: what it does is Generate a tag containing every blocktype with its children. It's Useful for datapack makers and Iris since 1.8 that can use tags for shaders
    - it's on by default
    - Added a Christmas texture to All of Woodworks' chests from Dec 24th to 26th - [#1150](https://github.com/MehVahdJukaar/WoodGood/issues/1150)
- **LANG** (ja_jp): 
  - Implemented the latest en_us's update, organized the content, & Corrected incorrect translation for handcrafted.pillar_trim - @HayaKoh-WeldyAlin & [PR#1134](https://github.com/MehVahdJukaar/WoodGood/pull/1134)
  - Added support for Chipped -  @HayaKoh-WeldyAlin - [PR#1138](https://github.com/MehVahdJukaar/WoodGood/issues/1138)
- **Quark** (F): Fixed the missing hedge for **Oh The Biomes We've Gone**'s palo_verde 
- **Building But Better 2.0pre3** (C): Fixed the missing textures for other blocks when `bbb:balustrade` is blacklisted - [#1144](https://github.com/MehVahdJukaar/WoodGood/issues/1144)
- **Macaw's Furniture** (C): Updated glass_table (require end_table) & covered_desk (require stripped_log) - [#1142](https://github.com/MehVahdJukaar/WoodGood/issues/1142)
  - NOTE: **Moonlight lib v2.16.17** - Added `powdery`'s missing stripped_log from **My Nether's Delight**
- **Chipped** (INCLUDED): 
  - Vanilla WoodTypes except OAK - [#1136](https://github.com/MehVahdJukaar/WoodGood/issues/1136)
  - Supported 11 new Blocks for LeavesType

---

## v2.9.12

### UPDATED: 
- **Macaw's Furniture** (C): Corrected the LANG for `kitchen_sink` & `stripped_kitchen_sink`
- **Every Compat** (C): Added a new config, NO_MOD_CREATIVE_TAB in `everycomp-common.toml` - [#1113](https://github.com/MehVahdJukaar/WoodGood/issues/1113)
  - NOTE: what it does is not adding items to Mod's own tab in Creative Mode if NO_MOD_CREATIVE_TAB is set to `true`
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

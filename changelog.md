### ADDED: 
- **Mofu's Better End** (IT): frost_root & weepingstar for log, stripped_log, leaves, and planks
- **Friends & Foes** (COMMON): Disable the `addBlocksToPOI()` for now until the solution is applied

## v2.7.29

### UPDATED:
- **Variant Vanilla Blocks** (COMMON): Disable the `addBlocksToPOI()` for now until the solution is applied

## v2.7.28

### UPDATED:
- **Every Compat**: Updated the ECRegistry.addBlocksToPOI to use the code from Moonlight lib - Fixed [Moonlight#339](https://github.com/MehVahdJukaar/Moonlight/issues/339)
  - **Friends And Foes** (COMMON): beehives to act as beehome for bees
  - **Variant Vanilla Blocks** (COMMON): Blocks that has job_acquireable for villagers to be an employee in

<span style="color: YELLOW;">NOTE: **Moonlight Lib v2.13.83** is now required</span>

## v2.7.27

### UPDATED:
- **Every Compat** (COMMON): 
  - Added tag, #minecraft:soul_fire_base_block to **Soulful Nether**'s fright (only applied to PLANKS)
  - Fixed the blockstate generation not applying to "block/oak_planks" for other wood mods - Related to Quark & [#907](https://github.com/MehVahdJukaar/WoodGood/issues/907)
- **Quark** (FORGE): Fixed vertical_slabs not dropping 2 slabs when broken as double-slab - [#907](https://github.com/MehVahdJukaar/WoodGood/issues/907)

---

### ADDED: 
- **LANG**: en_us - corrected name for _hollow_log_ with "stem" for **Soulful Nether**'s fright
- **LANG**: zh_cn - Blocks Plus @libu2333

---

#### NEW SUPPORTED MOD:
- **Blocks Plus** (FORGE)

---

### INCOMPATIBLE:
- **VMinus Mod** (FORGE): From v2.7.19 onward, it is marked as INCOMPATIBLE
- **Very Many Players** (FORGE|FABRIC): it is marked as INCOMPATIBLE

---

**LEGEND**:
- (COMMON) = FORGE & FABRIC
- (IT) - Included Texture: Added the ResourceLocation of tfhe missing textures required for blocks or generating a new texture
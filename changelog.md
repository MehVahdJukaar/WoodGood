- **Every Compat**: Restored the old method used by an older FORGE version 

## v2.8.1

### UPDATED: 
- **Every Compat** (COMMON): Forgot to update the recipe system for **Stone Zone** to use the new Resource Generator

## 2.8.0

### UPDATED:
- **EveryCompat** (COMMON):
  - **SERVER-side**: Fixed 
  - Now using multi-thread for Resource Generation. This should reduce the time for launching and loading the world
      - <span style="color: RED;">REQUIRED: Moonlight LIb v2.14.0+</span>
  - WITH **Chipped** & **Biomes O' Plenty**
    - **Old Asset Generator** took about 6 seconds 
    - **New Asset Generator** took about 1 second
- **Handcrafted** (COMMON): Updated the system for modifying counter's model files with the correct texture

### ADDED:
- **EveryCompat** (COMMON): more exceptions for Macaw Compat Mods - @22858

## v2.7.30

### ADDED: 
- **Mofu's Better End** (IT): frost_root & weepingstar for log, stripped_log, leaves, and planks
- **Friends & Foes** (COMMON): Disable the `addBlocksToPOI()` for now until the solution is applied

---

### INCOMPATIBLE:
- **VMinus Mod** (FORGE): From v2.7.19 onward, it is marked as INCOMPATIBLE
- **Very Many Players** (FORGE|FABRIC): it is marked as INCOMPATIBLE

---

# **LEGEND**:
- (COMMON) = FORGE & FABRIC
- (IT) - Included Texture: Added the ResourceLocation of tfhe missing textures required for blocks or generating a new texture
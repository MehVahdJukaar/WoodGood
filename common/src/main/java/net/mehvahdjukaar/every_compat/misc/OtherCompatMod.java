package net.mehvahdjukaar.every_compat.misc;

import java.util.List;

//TODO: rename modId to compatModId, woodsFrom to blockTypeFromModId, blocksFrom to supportedModId
/**
 * @param modId Id of COMPAT-MOD that support the SUPPORTED-MOD & mods that add new BlockTypes
 * @param woodsFrom Id of mods that add new BlockTypes
 * @param blocksFrom id of SUPPORTED-MOD
 */
public record OtherCompatMod(String modId, List<String> woodsFrom, List<String> blocksFrom) {
}
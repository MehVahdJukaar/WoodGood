package net.mehvahdjukaar.every_compat.modules.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockCompDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.phys.AABB;
import org.apache.commons.io.IOUtils;
import org.joml.Vector3f;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class ModDrawersGeometry {
    private static boolean geometryDataLoaded = false;

    public static void loadGeometryData(StorageDrawersModule module, ResourceManager manager) {
        if (geometryDataLoaded)
            return;

        geometryDataLoaded = true;

        populateGeometryData(modLoc("models/block/geometry/full_drawers_icon_area_1.json"),
                modLoc("models/block/geometry/full_drawers_count_area_1.json"),
                modLoc("models/block/geometry/full_drawers_ind_area_1.json"),
                modLoc("models/block/geometry/full_drawers_indbase_area_1.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 1, false).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/full_drawers_icon_area_2.json"),
                modLoc("models/block/geometry/full_drawers_count_area_2.json"),
                modLoc("models/block/geometry/full_drawers_ind_area_2.json"),
                modLoc("models/block/geometry/full_drawers_indbase_area_2.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 2, false).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/full_drawers_icon_area_4.json"),
                modLoc("models/block/geometry/full_drawers_count_area_4.json"),
                modLoc("models/block/geometry/full_drawers_ind_area_4.json"),
                modLoc("models/block/geometry/full_drawers_indbase_area_4.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 4, false).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/half_drawers_icon_area_1.json"),
                modLoc("models/block/geometry/half_drawers_count_area_1.json"),
                modLoc("models/block/geometry/half_drawers_ind_area_1.json"),
                modLoc("models/block/geometry/half_drawers_indbase_area_1.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 1, true).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/half_drawers_icon_area_2.json"),
                modLoc("models/block/geometry/half_drawers_count_area_2.json"),
                modLoc("models/block/geometry/half_drawers_ind_area_2.json"),
                modLoc("models/block/geometry/half_drawers_indbase_area_2.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 2, true).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/half_drawers_icon_area_4.json"),
                modLoc("models/block/geometry/half_drawers_count_area_4.json"),
                modLoc("models/block/geometry/half_drawers_ind_area_4.json"),
                modLoc("models/block/geometry/half_drawers_indbase_area_4.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockDrawers.class, 4, true).toArray(BlockDrawers[]::new)
        );

        populateGeometryData(modLoc("models/block/geometry/full_comp_drawers_icon_area_2.json"),
                modLoc("models/block/geometry/full_comp_drawers_count_area_2.json"),
                modLoc("models/block/geometry/full_comp_drawers_ind_area_2.json"),
                modLoc("models/block/geometry/full_comp_drawers_indbase_area_2.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockCompDrawers.class, 2, false).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/full_comp_drawers_icon_area_3.json"),
                modLoc("models/block/geometry/full_comp_drawers_count_area_3.json"),
                modLoc("models/block/geometry/full_comp_drawers_ind_area_3.json"),
                modLoc("models/block/geometry/full_comp_drawers_indbase_area_3.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockCompDrawers.class, 3, false).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/half_comp_drawers_icon_area_2.json"),
                modLoc("models/block/geometry/half_comp_drawers_count_area_2.json"),
                modLoc("models/block/geometry/half_comp_drawers_ind_area_2.json"),
                modLoc("models/block/geometry/half_comp_drawers_indbase_area_2.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockCompDrawers.class, 2, true).toArray(BlockDrawers[]::new)
        );
        populateGeometryData(modLoc("models/block/geometry/half_comp_drawers_icon_area_3.json"),
                modLoc("models/block/geometry/half_comp_drawers_count_area_3.json"),
                modLoc("models/block/geometry/half_comp_drawers_ind_area_3.json"),
                modLoc("models/block/geometry/half_comp_drawers_indbase_area_3.json"), manager,
                module.getDrawersOfTypeAndSizeAndDepth(BlockCompDrawers.class, 3, true).toArray(BlockDrawers[]::new)
        );
    }

    //FORGE use StorageDrawers.rl() and FABRIC use ModConstants.loc()
    private static ResourceLocation modLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath("storagedrawers", path);
    }

    private static void populateGeometryData(ResourceLocation locationIcon,
                                             ResourceLocation locationCount,
                                             ResourceLocation locationInd,
                                             ResourceLocation locationIndBase, ResourceManager manager,
                                             BlockDrawers... blocks) {
        BlockModel slotInfo = getBlockModel(locationIcon, manager);
        BlockModel countInfo = getBlockModel(locationCount, manager);
        BlockModel indInfo = getBlockModel(locationInd, manager);
        BlockModel indBaseInfo = getBlockModel(locationIndBase, manager);
        for (BlockDrawers block : blocks) {
            if (block == null)
                continue;

            for (int i = 0; i < block.getDrawerCount(); i++) {
                Vector3f from = slotInfo.getElements().get(i).from;
                Vector3f to = slotInfo.getElements().get(i).to;
                block.labelGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                Vector3f from = countInfo.getElements().get(i).from;
                Vector3f to = countInfo.getElements().get(i).to;
                block.countGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                Vector3f from = indInfo.getElements().get(i).from;
                Vector3f to = indInfo.getElements().get(i).to;
                block.indGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                Vector3f from = indBaseInfo.getElements().get(i).from;
                Vector3f to = indBaseInfo.getElements().get(i).to;
                block.indBaseGeometry[i] = new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
            }
        }
    }

    private static BlockModel getBlockModel(ResourceLocation location, ResourceManager manager) {
        Resource iresource;
        Reader reader = null;
        try {
            iresource =  manager.getResourceOrThrow(location);
            reader = new InputStreamReader(iresource.open(), StandardCharsets.UTF_8);
            return BlockModel.fromStream(reader);
        } catch (IOException e) {
            return null;
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

}
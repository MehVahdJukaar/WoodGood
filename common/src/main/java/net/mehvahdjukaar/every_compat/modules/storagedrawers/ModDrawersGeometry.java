package net.mehvahdjukaar.every_compat.modules.storagedrawers;

import com.jaquadro.minecraft.storagedrawers.block.BlockCompDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import net.minecraft.client.resources.model.cuboid.CuboidModel;
import net.minecraft.client.resources.model.cuboid.CuboidModelElement;
import net.minecraft.client.resources.model.cuboid.UnbakedCuboidGeometry;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.phys.AABB;
import org.apache.commons.io.IOUtils;
import org.joml.Vector3fc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ModDrawersGeometry {
    private static boolean geometryDataLoaded = false;

    public static void loadGeometryData(StorageDrawersModule module, ResourceManager manager) {
        if (geometryDataLoaded)
            return;

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
        //set last so a failed load gets retried on next reload
        geometryDataLoaded = true;
    }

    //FORGE use StorageDrawers.rl() and FABRIC use ModConstants.loc()
    private static Identifier modLoc(String path) {
        return Identifier.fromNamespaceAndPath("storagedrawers", path);
    }

    private static void populateGeometryData(Identifier locationIcon,
                                             Identifier locationCount,
                                             Identifier locationInd,
                                             Identifier locationIndBase, ResourceManager manager,
                                             BlockDrawers... blocks) {
        List<CuboidModelElement> slotInfo = getElements(getBlockModel(locationIcon, manager));
        List<CuboidModelElement> countInfo = getElements(getBlockModel(locationCount, manager));
        List<CuboidModelElement> indInfo = getElements(getBlockModel(locationInd, manager));
        List<CuboidModelElement> indBaseInfo = getElements(getBlockModel(locationIndBase, manager));
        for (BlockDrawers block : blocks) {
            if (block == null)
                continue;

            for (int i = 0; i < block.getDrawerCount(); i++) {
                block.labelGeometry[i] = toAABB(slotInfo.get(i));
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                block.countGeometry[i] = toAABB(countInfo.get(i));
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                block.indGeometry[i] = toAABB(indInfo.get(i));
            }
            for (int i = 0; i < block.getDrawerCount(); i++) {
                block.indBaseGeometry[i] = toAABB(indBaseInfo.get(i));
            }
        }
    }

    private static AABB toAABB(CuboidModelElement element) {
        Vector3fc from = element.from();
        Vector3fc to = element.to();
        return new AABB(from.x(), from.y(), from.z(), to.x(), to.y(), to.z());
    }

    private static List<CuboidModelElement> getElements(CuboidModel model) {
        if (model == null) return new ArrayList<>();
        return model.geometry() instanceof UnbakedCuboidGeometry geo ? geo.elements() : new ArrayList<>();
    }

    private static CuboidModel getBlockModel(Identifier location, ResourceManager manager) {
        Resource iresource;
        Reader reader = null;
        try {
            iresource =  manager.getResourceOrThrow(location);
            reader = new InputStreamReader(iresource.open(), StandardCharsets.UTF_8);
            return CuboidModel.fromStream(reader);
        } catch (IOException e) {
            return null;
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

}
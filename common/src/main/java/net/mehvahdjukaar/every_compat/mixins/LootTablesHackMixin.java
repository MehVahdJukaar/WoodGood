package net.mehvahdjukaar.every_compat.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.mehvahdjukaar.every_compat.ECPlatStuff;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BlockBehaviour.class)
public abstract class LootTablesHackMixin {

    @Shadow
    public abstract Item asItem();

    @Inject(method = "getDrops", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"
            ))
    public void everyComp$addFastDrops(BlockState state, LootParams.Builder params, CallbackInfoReturnable<List<ItemStack>> cir,
                                       @Local LootTable lootTable, @Local LootParams lootParams,
                                       @Local ResourceKey<LootTable> resId) {
        if (lootTable == LootTable.EMPTY && Utils.getID(state.getBlock()).getNamespace().equals(EveryCompat.MOD_ID)) {
            if (SimpleEntrySet.isSimpleDrop(state.getBlock())) {
                cir.setReturnValue(ECPlatStuff.modifyLoot(resId.location(), List.of(this.asItem().getDefaultInstance()), lootParams));
            }
        }
    }
}

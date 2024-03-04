package net.mehvahdjukaar.every_compat.mixins;

import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
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
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BlockBehaviour.class)
public abstract class LootTablesHackMixin {

    @Shadow public abstract Item asItem();

    @Inject(method = "getDrops", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;)Lit/unimi/dsi/fastutil/objects/ObjectArrayList;"
            ), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void addSimpleFastECdrops(BlockState state, LootParams.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir, ResourceLocation resourceLocation, LootParams lootParams, ServerLevel serverLevel, LootTable lootTable) {
        if(lootTable == LootTable.EMPTY && Utils.getID(state.getBlock()).getNamespace().equals(EveryCompat.MOD_ID)){
            if(SimpleEntrySet.isSimpleDrop(state.getBlock())){
                cir.setReturnValue(List.of(this.asItem().getDefaultInstance()));
            }
        }
    }
}

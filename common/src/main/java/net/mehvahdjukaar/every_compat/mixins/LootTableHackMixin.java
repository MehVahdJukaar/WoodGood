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
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BlockBehaviour.class)
public abstract class LootTableHackMixin {

    @Shadow public abstract Item asItem();

    @Inject(method = "getDrops", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTables;get(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/world/level/storage/loot/LootTable;",
            shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    public void everyCompat$addSimpleFastDrops(BlockState state, LootContext.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir,
                                     ResourceLocation resourceLocation, LootContext lootContext,
                                     ServerLevel serverLevel, LootTable lootTable) {
        if(lootTable == LootTable.EMPTY && Utils.getID(state.getBlock()).getNamespace().equals(EveryCompat.MOD_ID)){
            if(SimpleEntrySet.isSimpleDrop(state.getBlock())){
                cir.setReturnValue(List.of(this.asItem().getDefaultInstance()));
            }
        }
    }
}

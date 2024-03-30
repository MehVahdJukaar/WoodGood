package net.mehvahdjukaar.every_compat.modules.fabric.crayfish;

import com.mrcrayfish.mightymail.block.MailboxBlock;
import com.mrcrayfish.mightymail.core.ModBlockEntities;
import com.mrcrayfish.mightymail.core.ModCreativeTabs;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodTypeRegistry;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

//SUPPORT: v1.0.14+
public class MightyMailModule extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> mailboxes;

    public MightyMailModule(String modId) {
        super(modId, "mm");

        mailboxes = SimpleEntrySet.builder(WoodType.class, "mail_box",
                        getModBlock("oak_mail_box"), () -> WoodTypeRegistry.OAK_TYPE,
                        w -> new MailboxBlock(Utils.copyPropertySafe(w.planks)
                                .strength(3.5F).sound(SoundType.WOOD).ignitedByLava()))
                .setTab(ModCreativeTabs.MAIN::get)
                .addTile(ModBlockEntities.MAIL_BOX::get)
                .addTextureM(modRes("block/oak_mail_box"),
                        EveryCompat.res("block/mm/oak_mail_box_m"))
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .setRenderType(() -> RenderType::cutout)
                .defaultRecipe()
                .build();
        this.addEntry(mailboxes);
    }
}

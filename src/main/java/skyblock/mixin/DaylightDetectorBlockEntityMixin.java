package skyblock.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Tickable;
import org.spongepowered.asm.mixin.Mixin;
import quickcarpet.helper.NBTHelper;
import skyblock.IDaylightDetectorBlockEntity;
import skyblock.SkyBlockSettings;

@Mixin(DaylightDetectorBlockEntity.class)
public abstract class DaylightDetectorBlockEntityMixin extends BlockEntity implements Tickable, IDaylightDetectorBlockEntity {
    public boolean detectsBlockLight = false;

    public DaylightDetectorBlockEntityMixin(BlockEntityType<?> blockEntityType_1) {
        super(blockEntityType_1);
    }

    public boolean getBlockLightDetection() {
        return detectsBlockLight;
    }

    public void toggleBlockLightDetection() {
        this.detectsBlockLight = !this.detectsBlockLight;
        float float_1 = detectsBlockLight ? 0.75F : 0.7F;
        world.playSound(null, this.pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, float_1);
    }

    public void fromTag(BlockState state, CompoundTag compoundTag_1) {
        super.fromTag(state, compoundTag_1);

        if (SkyBlockSettings.blockLightDetector && compoundTag_1.contains("blockLightMode", NBTHelper.TAG_INT)) {
            this.detectsBlockLight = compoundTag_1.getInt("blockLightMode") > 0;
        }
    }

    public CompoundTag toTag(CompoundTag compoundTag_1) {
        compoundTag_1 = super.toTag(compoundTag_1);
        if (SkyBlockSettings.blockLightDetector) {
            compoundTag_1.putInt("blockLightMode", this.detectsBlockLight ? 1 : 0);
        }
        return compoundTag_1;
    }
}

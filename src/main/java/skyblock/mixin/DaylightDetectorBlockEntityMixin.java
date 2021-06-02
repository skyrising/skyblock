package skyblock.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import skyblock.IDaylightDetectorBlockEntity;
import skyblock.SkyBlockSettings;

@Mixin(DaylightDetectorBlockEntity.class)
public abstract class DaylightDetectorBlockEntityMixin extends BlockEntity implements IDaylightDetectorBlockEntity {
    public boolean detectsBlockLight = false;

    public DaylightDetectorBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public boolean getBlockLightDetection() {
        return detectsBlockLight;
    }

    public void toggleBlockLightDetection() {
        this.detectsBlockLight = !this.detectsBlockLight;
        float float_1 = detectsBlockLight ? 0.75F : 0.7F;
        world.playSound(null, this.pos, SoundEvents.BLOCK_COMPARATOR_CLICK, SoundCategory.BLOCKS, 0.3F, float_1);
    }

    @Override
    public void readNbt(NbtCompound compoundTag_1) {
        super.readNbt(compoundTag_1);

        if (SkyBlockSettings.blockLightDetector && compoundTag_1.contains("blockLightMode", 99 /* NUMBER */)) {
            this.detectsBlockLight = compoundTag_1.getInt("blockLightMode") > 0;
        }
    }

    @Override
    public NbtCompound writeNbt(NbtCompound compoundTag_1) {
        compoundTag_1 = super.writeNbt(compoundTag_1);
        if (SkyBlockSettings.blockLightDetector) {
            compoundTag_1.putInt("blockLightMode", this.detectsBlockLight ? 1 : 0);
        }
        return compoundTag_1;
    }
}

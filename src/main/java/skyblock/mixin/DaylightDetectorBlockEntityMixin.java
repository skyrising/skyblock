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
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        if (SkyBlockSettings.blockLightDetector && nbt.contains("blockLightMode", 99 /* NUMBER */)) {
            this.detectsBlockLight = nbt.getInt("blockLightMode") > 0;
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (SkyBlockSettings.blockLightDetector) {
            nbt.putInt("blockLightMode", this.detectsBlockLight ? 1 : 0);
        }
    }
}

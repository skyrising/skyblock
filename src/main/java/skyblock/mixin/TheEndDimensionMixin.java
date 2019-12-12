package skyblock.mixin;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.TheEndDimension;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import skyblock.SkyBlockUtils;

@Mixin(TheEndDimension.class)
public abstract class TheEndDimensionMixin extends Dimension {

    public TheEndDimensionMixin(World world, DimensionType type) {
        super(world, type, 0);
        throw new AbstractMethodError();
    }

    @Inject(method = "createChunkGenerator", at = @At("HEAD"), cancellable = true)
    private void createSkyBlockGenerator(CallbackInfoReturnable<ChunkGenerator<? extends ChunkGeneratorConfig>> cir) {
        LevelGeneratorType type = this.world.getLevelProperties().getGeneratorType();
        if (type == SkyBlockUtils.LEVEL_GENERATOR_TYPE) {
            cir.setReturnValue(SkyBlockUtils.createEndChunkGenerator(this.world));
            cir.cancel();
        }
    }
}

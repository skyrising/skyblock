package skyblock.mixin;

import net.minecraft.world.level.LevelGeneratorType;
import org.spongepowered.asm.mixin.Mixin;
import skyblock.SkyBlockUtils;

@Mixin(LevelGeneratorType.class)
public class LevelGeneratorTypeMixin {
    private LevelGeneratorTypeMixin(int id, String name) {}

    static {
        SkyBlockUtils.LEVEL_GENERATOR_TYPE = (LevelGeneratorType) (Object) new LevelGeneratorTypeMixin(15, "skyblock");
    }
}

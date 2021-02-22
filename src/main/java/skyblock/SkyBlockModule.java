package skyblock;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import quickcarpet.api.module.QuickCarpetModule;

public class SkyBlockModule implements QuickCarpetModule {
    @Override
    public String getName() {
        return Build.NAME;
    }

    @Override
    public String getVersion() {
        return Build.VERSION;
    }

    @Override
    public String getId() {
        return "skyblock";
    }

    @Override
    public Class<?> getSettingsClass() {
        return SkyBlockSettings.class;
    }

    @Override
    public boolean isIgnoredForRegistrySync(Identifier registry, Identifier entry) {
        if (!Registry.POTION_KEY.getValue().equals(registry)) return false;
        return entry.toString().startsWith("minecraft:super_");
    }
}
